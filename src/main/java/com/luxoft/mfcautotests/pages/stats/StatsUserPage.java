package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class StatsUserPage extends BasePage {

    @FindBy(linkText = "Многофункциональный центр трудовой миграции")
    public WebElement mmcLink;

    @FindBy(css = ".user")
    public WebElement userName;

    @FindBy(css = ".logout-link")
    public WebElement logoutLink;

    @FindBy(linkText = "АРМ Пользователя отчетов")
    public WebElement screenName;

    @FindBy(css = ".version")
    public WebElement version;

    @FindBy(xpath = "//h2[text()='Выберите тип отчетности для просмотра']")
    public WebElement staticTextSelectTypeOfReport;

    @FindBy(xpath = "//h2[text()='Тип отчетности']")
    public WebElement staticTextTypeOfReport;

    @FindBy(linkText = "Ежедневный отчет")
    public WebElement dailyReportLink;

    @FindBy(linkText = "Ежемесячный отчет")
    public WebElement monthlyReportLink;

    @FindBy(css = ".statist-bottom [href=\"#\"]")
    public WebElement unloadYesterdayAsPdfLink;

    @FindBy(css = "[href='/mmcstatsuser/dashboard.htm']")
    public WebElement monthlyDataLink;

    public boolean isElementsInConditionForStatsUser() {
        return isDisplayed(mmcLink, userName, logoutLink, screenName, version, staticTextSelectTypeOfReport,staticTextTypeOfReport) &&
                isClickable(logoutLink, dailyReportLink, monthlyReportLink, unloadYesterdayAsPdfLink) &&
                unloadYesterdayAsPdfLink.getText().equalsIgnoreCase("Выгрузить отчет за вчерашний день в PDF");
    }

    public boolean isElementsInConditionForDashboardManager() {
        return isDisplayed(mmcLink, userName, logoutLink, screenName, version, staticTextSelectTypeOfReport,staticTextTypeOfReport) &&
                isClickable(monthlyDataLink)&&
                monthlyDataLink.getText().equalsIgnoreCase("Дашборд: ежемесячные данные");
    }

    public boolean isElementsInConditionForDashboardUser() {
        return isElementsInConditionForDashboardManager();
    }
}
