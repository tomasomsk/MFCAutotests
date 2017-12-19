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
import org.testng.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.testng.AssertJUnit.assertTrue;

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

    public By tableRowsSelector = By.cssSelector("tr");


    public void checkDailyReportPageElements() {
        String[] expectedHeaders = new String[]{"№\n" + "п/п", "Наименование показателя", "На отчетную дату", "С начала\n" + "года",
                "За год", "Размерность", "Тип\n" + "заполнения", "Источник данных"};
        Set<String> expectedTableColumnsNames = new HashSet<>(Arrays.asList(expectedHeaders));
        Set<String> actualTableColumnsNames = new HashSet<>();
        for (WebElement columnName : actualHeaders) {
            actualTableColumnsNames.add(columnName.getText());
        }
        boolean firstCondition;
        boolean secondCondition;
        boolean thirdCondition;
        boolean fourthCondition;
        boolean fifthCondition;
        ClickableConfig selectConfig = new ClickableConfig("select2-container-disabled");

        firstCondition = isDisplayed(dailyTypeOfReportLabel, linkToStatsAdminArm, reportDateLable, reportDateField, dataSourceLabel,
                dataSourceDropDown, dashboardItemsLabel, saveButton);
        secondCondition = isNotClickable(reportDateField, dashboardItemsCheckBox, saveButton);
        thirdCondition = isClickable(reportDateCalendarLink, createReportButton, goBackButton);
        fourthCondition = actualTableColumnsNames.equals(expectedTableColumnsNames);
        fifthCondition = isNotClickable(dataSourceDropDown, selectConfig);

        assertTrue(firstCondition && secondCondition && thirdCondition && fourthCondition && fifthCondition);
    }

    public void checkDefaultDate(LocalDateTime periodStart) {
        String reportDateFromUi = reportDateField.getAttribute("value");
//        if (statsHelper.dateFromServerLessThenEightAm()) {
//            defaultDate = addDaysToDate(new Date(), -2);
        checkDate(periodStart, reportDateFromUi);
//        } else {
//            defaultDate = addDaysToDate(new Date(), -1);
//            checkDate(defaultDate, reportDateFromUi);
//        }
    }

    public void checkDate(LocalDateTime expectedDate, String reportDateFromUi) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String expectedDateString = formatter.format(expectedDate);
        Assert.assertEquals(reportDateFromUi, expectedDateString);
    }

    public void checkDateSelection() {
        String date = reportDateField.getAttribute("value");
        Date defaultDate = getDateFromString(date, "dd.MM.yyyy");
        if (statsHelper.dateFromServerLessThenEightAm()) {
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

    public void generateRerport() {
    }

    public List<MfcStatsGroup> getDailyStatsValuesFromUi() {
        log.info("Getting statistic from UI to MfcStatsItem objects");
        List<WebElement> tableRows = findElements(tableRowsSelector);
        List<MfcStatsGroup> mfcDailyStatsFromUi = new ArrayList<>();
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
                mfcStatsItem.setValue(tdsInRow.get(2).getText());
                mfcStatsItem.setValueYear(tdsInRow.get(3).getText());
                mfcStatsItem.setValuePrevYear(tdsInRow.get(4).getText());
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


}