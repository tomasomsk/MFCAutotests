package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.config.forpages.ElementToFind;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.IntStream;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Page
public class DailyReportPage extends BasePage {

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

    @FindBy(css = ".day")
    public List<WebElement> daysInCalendar;

    public void checkDailyReportPageElements() {
        String[] expectedHeaders = new String[]{"№\n" + "п/п", "Наименование показателя", "На отчетную дату", "С начала\n" + "года",
                "За год", "Размерность", "Тип\n" + "заполнения", "Источник данных"};
        Set<String> expectedTableColumnsNames = new HashSet<>(Arrays.asList(expectedHeaders));
        Set<String> actualTableColumnsNames = new HashSet<>();
        for (WebElement columnName : actualHeaders) {
            actualTableColumnsNames.add(columnName.getText());
        }
        boolean firstCondition = isDisplayed(dailyTypeOfReportLabel, linkToStatsAdminArm, reportDateLable, reportDateField, dataSourceLabel,
                dataSourceDropDown, dashboardItemsLabel, saveButton);
        boolean secondCondition = false;
        boolean thirdCondition = false;
        boolean fourthCondition = false;
        boolean fifthCondition = false;

        if (firstCondition) {
            secondCondition = isNotClickable(reportDateField, dashboardItemsCheckBox, saveButton, goBackButton);
        }
        if (secondCondition) {
            thirdCondition = isClickable(reportDateCalendarLink, createReportButton, goBackButton);
        }
        if (thirdCondition) {
            fourthCondition = actualTableColumnsNames.equals(expectedTableColumnsNames);
        }
        if (fourthCondition) {
            ClickableConfig selectConfig = new ClickableConfig("select2-container-disabled");
            fifthCondition = isNotClickable(dataSourceDropDown, selectConfig);
        }

        assertTrue(fifthCondition);
    }

    public void checkDateSelection() {
        reportDateCalendarLink.click();
        Date currentDate = addDaysToDate(new Date(), 0);
        ClickableConfig calendarDaysClickableConfig = new ClickableConfig("disabled");
        assertTrue(isDatesDisabled(currentDate, daysInCalendar, daysInCalendar.size(), calendarDaysClickableConfig));

//        List<Integer> daysInCalendar = new ArrayList<>();
//
//        for (int i = 0; i < daysInCalendar.size(); i++) {
//            daysInCalendar.add(Integer.valueOf(daysInCalendar.get(i).getText()));
//        }
//
//        Date currentDate = new Date();
//        //The day of current date
//        int currentDay = Integer.parseInt(getStringFromDate(currentDate, "dd"));
//        //Indexes of day in List of days from UI calendar
//        ElementToFind dayToFind = new ElementToFind(currentDay);
//        Integer[] currentDayPositionsInCalendar = (Integer[]) getPositionsOfElementInList(dayToFind, daysInCalendar);
//        //Day from max index
//        int maxDayPosition = Collections.max(Arrays.asList(currentDayPositionsInCalendar));
//        //Check that current date and dates after current is disabled
//        ClickableConfig clickableConfig = new ClickableConfig("disabled");
//        for (int i = maxDayPosition; i < daysInCalendar.size(); i++) {
//            assertTrue(isNotClickable(daysInCalendar.get(i), clickableConfig));
//        }
    }

    public boolean isDatesDisabled(Date fromDate, List<WebElement> calendar, int countDaysToCheck, ClickableConfig clickableConfig) {
        //Create list of days from UI calendar
        List<Integer> daysInCalendar = new ArrayList<>();
        for (int i = 0; i < calendar.size(); i++) {
            daysInCalendar.add(Integer.valueOf(calendar.get(i).getText()));
        }
        //The day of parameter 'fromDate'
        int desiredDay = Integer.parseInt(getStringFromDate(fromDate, "dd"));
        //Indexes of desired day in List of days from UI calendar
        ElementToFind dayToFind = new ElementToFind(desiredDay);
        Integer[] currentDayPositionsInCalendar = (Integer[]) getPositionsOfElementInList(dayToFind, daysInCalendar);
        //Day with max index
        int dayMaxPosition = Collections.max(Arrays.asList(currentDayPositionsInCalendar));
        //Check that current date and dates after/before current is disabled
//        ClickableConfig clickableConfig = new ClickableConfig();
        for (int i = dayMaxPosition; i < countDaysToCheck; i++) {
            if (!isNotClickable(calendar.get(i), clickableConfig)) {
                return false;
            }
        }
        return true;
    }

    public Object getPositionsOfElementInList(ElementToFind element, List list) {
        if (element.getType().equalsIgnoreCase("int")) {
            return getPositionsOfIntElementInList(element, list);
        } else {
            throw new RuntimeException("Unknown type of element to find");
        }
    }

    private Integer[] getPositionsOfIntElementInList(ElementToFind element, List<Integer> list) {
        return IntStream.range(0, list.size())
                .filter(i -> list.get(i) == element.getIntValue()).boxed().toArray(Integer[]::new);
    }
}