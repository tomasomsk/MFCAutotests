package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.config.annotations.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Page
public class StatsAdminPage extends BasePage {

    @FindBy(linkText = "Многофункциональный центр трудовой миграции")
    public WebElement mmcLink;

    @FindBy(css = ".user")
    public WebElement userName;

    @FindBy(linkText = "АРМ Администратора отчетов")
    public WebElement screenName;

    @FindBy(css = ".version")
    public WebElement version;

    @FindBy(xpath = "//h2[text()='Выберите тип отчетности']")
    public WebElement staticTextSelectTypeOfReport;

    @FindBy(xpath = "//h2[text()='Тип отчетности']")
    public WebElement staticTextTypeOfReport;

    @FindBy(css = ".logout-link")
    public WebElement logoutLink;

    @FindBy(linkText = "Ежедневный отчет")
    public WebElement dailyReportLink;

    @FindBy(linkText = "Ежемесячный отчет")
    public WebElement monthlyReportLink;

    @FindBy(linkText = "Повторная загрузка данных от внешних систем")
    public WebElement reloadStatsPermissionLink;

    @FindBy(xpath = "//li[text()='Ежедневный отчет']")
    public WebElement dailyReportLabel;

    @FindBy(css = "#statsDate")
    public WebElement reportDateField;

    @FindBy(css = ".calendar-link")
    public WebElement calendarLink;

    @FindBy(css = "[ng-click='createReportBtn.click()']")
    public WebElement createReportButton;

    @FindBy(name= "statsSource")
    public WebElement statsSourceDropDown;

    @FindBy (css = ".table.reports-table th")
    public List<WebElement> tableHeader;

    @FindBy (css = "[ng-click='goBackBtn.click()']")
    public WebElement goBackButton;

    @FindBy (css = "#viewDashBoardItem")
    public WebElement viewDashBoardItemCheckBox;

    @FindBy (xpath = "//label[text()='Отчетная дата']")
    public WebElement reportDateLabel;



    public boolean isElementsInConditionForStatsAdmin() {
        return isDisplayed(mmcLink, userName, logoutLink, screenName, version, staticTextSelectTypeOfReport,staticTextTypeOfReport) &&
        isClickable(logoutLink, dailyReportLink, monthlyReportLink, reloadStatsPermissionLink);
    }

    public void openDailyReport() {
        waitUntilClickable(dailyReportLink).click();
    }
}
