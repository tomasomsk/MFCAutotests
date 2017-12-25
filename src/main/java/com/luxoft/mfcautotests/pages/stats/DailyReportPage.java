package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Page
public class DailyReportPage extends BasePage {

    @Autowired
    StatsHelper statsHelper;

    Date defaultDate;

    @FindBy(xpath = "//li[text()='Ежедневный отчет']")
    public WebElement dailyTypeOfReportLabel;

    @FindBy(linkText = "АРМ Администратора отчетов/")
    public WebElement linkToStatsAdminArm;

    @FindBy(xpath = "//label[text()='Отчетная дата']")
    public WebElement reportDateLable;

    @FindBy(id = "statsDate")
    public WebElement reportDateField;

    @FindBy(css = ".calendar-link")
    public WebElement reportDateCalendarLink;

    @FindBy(xpath = "//button[normalize-space(text())='Сформировать отчет']")
    public WebElement createReportButton;

    @FindBy(xpath = "//label[normalize-space(text())='Источник данных']")
    public WebElement dataSourceLabel;

    @FindBy(id = "statsSource")
    public WebElement dataSourceDropDown;

    @FindBy(xpath = "//label[text()='Отображать раздел с показателями Дашборда']")
    public WebElement dashboardItemsLabel;

    @FindBy(id = "viewDashBoardItem")
    public WebElement dashboardItemsCheckBox;

    @FindBy(css = "thead th")
    public List<WebElement> actualHeaders;

    @FindBy(css = ".btn.btn-success")
    public WebElement saveButton;

    @FindBy(xpath = "//button[text()='Назад']")
    public WebElement goBackButton;

    @FindBy(css = ".ajax-loader")
    public WebElement loadingIndicator;

    @FindBy(css = "[ng-show='stats.isVisible()']")
    public WebElement tableLabel;

    @FindBy(xpath = "//button[normalize-space(text())='Выгрузить в Exсel']")
    public WebElement uploadToExcelButton;

    @FindBy(xpath = "//button[normalize-space(text())='Выгрузить в PDF']")
    public WebElement uploadToPdfButton;

//    public By tableRowsSelector = By.cssSelector("tr");

    List<MfcStatsGroup> mfcDailyStatsFromUi;


    public void checkTableColumnNames() {
        String[] expectedHeaders = new String[]{"№\n" + "п/п", "Наименование показателя", "На отчетную дату", "С начала\n" + "года",
                "За год", "Размерность", "Тип\n" + "заполнения", "Источник данных"};
        Set<String> expectedTableColumnsNames = new HashSet<>(Arrays.asList(expectedHeaders));
        Set<String> actualTableColumnsNames = new HashSet<>();
        for (WebElement columnName : actualHeaders) {
            actualTableColumnsNames.add(columnName.getText());
        }
        actualTableColumnsNames.equals(expectedTableColumnsNames);
    }

    public void checkDateSelection() {
        String date = reportDateField.getAttribute("value");
        //Default date to set it in, because after clicking on date field date in it is disappearing
        Date defaultDate = getDateFromString(date, "dd.MM.yyyy");
        if (statsHelper.currentTimeLessThanEightAm()) {
            Date yesterdayDate = addDaysToDate(new Date(), -1);
            checkThatDatesDisabledFrom(yesterdayDate, reportDateCalendarLink);
        } else {
            Date currentDate = new Date();
            checkThatDatesDisabledFrom(currentDate, reportDateCalendarLink);
        }
        setDateInCalendar(defaultDate, reportDateCalendarLink);
    }

//    public void checkThatDatesDisabledFrom(Date date, int countDaysToCheck) {
//        checkThatDatesDisabledFrom(date, countDaysToCheck, reportDateCalendarLink);
//    }

    public DailyReportPage generateRerport() {
        log.info("Generating the daily stats report");
        createReportButton.click();
        sleep(3000);
        return this;
    }

    public DailyReportPage checkTableLabel(LocalDateTime periodStart) {
        log.info("Checking table label");
        String actualLabelBeforeTable = this.tableLabel.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String expectedDate = periodStart.format(formatter);
        String expectedLabelBeforeTable = "Ежедневный отчет о работе Миграционного центра за " + expectedDate;
        assertEquals(expectedLabelBeforeTable, actualLabelBeforeTable);
        return this;
    }

    public List<MfcStatsGroup> getDailyStatsFromUi() {
        if(!dashboardItemsCheckBox.isSelected()) {
            dashboardItemsCheckBox.click();
        }
        if (mfcDailyStatsFromUi != null) {
            return mfcDailyStatsFromUi;
        }
        log.info("Getting statistic from UI");
        List<WebElement> tableRows = findElements(By.cssSelector("tr"));
        mfcDailyStatsFromUi = new ArrayList<>();
        MfcStatsGroup mfcStatsGroup = null;

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement tableRow = tableRows.get(i);
            List<WebElement> tdsInRow = tableRow.findElements(By.tagName("td"));
            boolean isTdInRow = !tdsInRow.isEmpty();

            if (isTdInRow && !tdsInRow.get(0).getText().equalsIgnoreCase("1. Ключевые показатели") &&
                    !tdsInRow.get(0).getText().equalsIgnoreCase("2. Работа ММЦ") &&
                    !tdsInRow.get(0).getText().equalsIgnoreCase("3. Показатели Дашборда, не входящие в отчет")) {
                if (tdsInRow.get(0).getAttribute("class").equalsIgnoreCase("header ng-binding")) {
                    if (mfcStatsGroup != null) {
                        log.info("Adding group with name " + mfcStatsGroup.getName() + " into mfcDailyStatsFromUi");
                        mfcDailyStatsFromUi.add(mfcStatsGroup);
                    }
                    log.info("Creating group with name " + tdsInRow.get(0).getText());
                    mfcStatsGroup = new MfcStatsGroup();
                    mfcStatsGroup.setName(tdsInRow.get(0).getText().trim());
                    continue;
                }
                log.info("Creating item " + tdsInRow.get(0).getText());
                MfcStatsItem mfcStatsItem = new MfcStatsItem();
                mfcStatsItem.setNumber(Integer.parseInt(tdsInRow.get(0).getText()));
                mfcStatsItem.setName(tdsInRow.get(1).getText());
                mfcStatsItem.setValue(tdsInRow.get(2).findElement(By.cssSelector("input")).getAttribute("value"));
                mfcStatsItem.setValueYear(tdsInRow.get(3).findElement(By.cssSelector("input")).getAttribute("value"));
                mfcStatsItem.setValuePrevYear(tdsInRow.get(4).findElement(By.cssSelector("input")).getAttribute("value"));
                mfcStatsItem.setDimension(tdsInRow.get(5).getText());
                mfcStatsItem.setFillingType("Авт.".equalsIgnoreCase(tdsInRow.get(6).getText()) ? 1 : 2);
                mfcStatsItem.setDataSource(statsHelper.switchDataSourceValue(tdsInRow.get(7).getText()));
                log.info("Adding item " + tdsInRow.get(0).getText() + " into group " + mfcStatsGroup.getName());
                mfcStatsGroup.addMfcStatsItem(mfcStatsItem);
            }
            if (i == (tableRows.size() - 1)) {
                log.info("Adding group with name " + mfcStatsGroup.getName() + " into mfcDailyStatsFromUi");
                mfcDailyStatsFromUi.add(mfcStatsGroup);
            }
        }
        return mfcDailyStatsFromUi;
    }

    public boolean isValuesForItemRed(String handItemName) {
        log.info("Checking that values for manual stats items are red on UI");
        boolean result = false;
        List<WebElement> rowsWithItem = driverUtils.getDriver().findElements(By.xpath("//td[normalize-space(text())='" + handItemName + "']/.."));
        for (WebElement rowWithItem : rowsWithItem) {
            List<WebElement> tdsFromRow = rowWithItem.findElements(By.cssSelector("td"));

            List<WebElement> redValues = new ArrayList<>();
            redValues.add(tdsFromRow.get(2).findElement(By.cssSelector("input")));
            System.out.println("first td value " + tdsFromRow.get(2).getText());
            System.out.println(" css value " + tdsFromRow.get(2).findElement(By.cssSelector("input")).getCssValue("color") );
            redValues.add(tdsFromRow.get(3).findElement(By.cssSelector("input")));
            System.out.println("second td value " + tdsFromRow.get(3).getText());
            System.out.println(" css value " + tdsFromRow.get(3).findElement(By.cssSelector("input")).getCssValue("color") );
            redValues.add(tdsFromRow.get(4).findElement(By.cssSelector("input")));
            System.out.println("third td value " + tdsFromRow.get(4).getText());
            System.out.println(" css value " + tdsFromRow.get(4).findElement(By.cssSelector("input")).getCssValue("color") );
            redValues.add(tdsFromRow.get(7));
            System.out.println("last td value " + tdsFromRow.get(7).getText());
            System.out.println(" css value " + tdsFromRow.get(7).getCssValue("color") );

            result = checkCssValue(redValues, "color", "rgba(255, 0, 0, 1)");
            if (!result) {
                break;
            }
        }
        return result;
    }

    public boolean checkCssValue(List<WebElement> webElementsList, String cssValue, String value){
        boolean result = false;
        for (WebElement webElement : webElementsList) {
            String res = webElement.getCssValue(cssValue);
            System.out.println(res);
            result = res.equalsIgnoreCase(value);
            if (!result) {
                break;
            }
        }
        return result;
    }
}