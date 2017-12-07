package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
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

    public void checkDefaultDate() {
        String reportDateFromUi = getReportDate();
        if (statsHelper.dateFromServerLessThenEightAm()) {
            defaultDate = addDaysToDate(new Date(), -2);
            checkDate(defaultDate, reportDateFromUi);
        } else {
            defaultDate = addDaysToDate(new Date(), -1);
            checkDate(defaultDate, reportDateFromUi);
        }
    }

    public void checkDate(Date expectedDate, String reportDateFromUi) {
        String expectedDateString = getStringFromDate(expectedDate, "dd.MM.yyyy");
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


    public String getReportDate() {
        return reportDateField.getAttribute("value");
    }



//    public void checkThatDatesDisabledFrom(Date date, int countDaysToCheck) {
//        checkThatDatesDisabledFrom(date, countDaysToCheck, reportDateCalendarLink);
//    }
}