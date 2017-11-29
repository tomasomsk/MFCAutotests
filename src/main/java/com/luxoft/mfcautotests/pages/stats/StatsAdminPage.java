package com.luxoft.mfcautotests.pages.stats;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Page
public class StatsAdminPage extends BasePage {

    @Autowired
    DailyReportPage dailyReportPage;

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

    public boolean isElementsInConditionForStatsAdmin() {
        return isDisplayed(mmcLink, userName, logoutLink, screenName, version, staticTextSelectTypeOfReport,staticTextTypeOfReport) &&
        isClickable(logoutLink, dailyReportLink, monthlyReportLink, reloadStatsPermissionLink);
    }

    public DailyReportPage openDailyReport() {
        waitUntilClickable(dailyReportLink).click();
        return dailyReportPage;
    }
}
