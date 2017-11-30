package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

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

//    @FindBy(css = )

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

    }
}
