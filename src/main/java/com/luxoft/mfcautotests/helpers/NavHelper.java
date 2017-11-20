package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.emuls.QMSPage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Helper
public class NavHelper extends BaseHelper {

    @Autowired
    LoginPage loginPage;
    @Autowired
    LoginHelper loginHelper;
    @Autowired
    QMSPage qmsPage;

    private void gotoUrl(String url){
        log.info("Going to url: " + url);
        driverUtils.getDriver().get(url);
    }

    public LoginHelper openArmOoo() {
        String windowId = env.windowId;
        gotoUrl(env.baseUrl + env.mmcRegUrl + windowId);
        return loginHelper;
    }

    public LoginHelper openArmStatsAdmin() {
        gotoUrl(env.baseUrl + env.statsAdminUrl);
        return loginHelper;
    }

    public LoginHelper openArmStatsUser() {
        gotoUrl(env.baseUrlWs + env.statsUserUrl);
        return loginHelper;
    }

    public LoginHelper openArmInsuranceAdmin() {
        gotoUrl(env.baseUrl + env.insuranceAdminUrl);
        return loginHelper;
    }

    public void openInsuranceCompaniesCatalog() {
        gotoUrl(env.insuranceCompaniesCatalogUrl);
        driverUtils.getDriver().findElement(By.id("os_username")).sendKeys("stomaschuk");
        driverUtils.getDriver().findElement(By.id("os_password")).sendKeys("Luxoft2025");
        driverUtils.getDriver().findElement(By.cssSelector(".panelButton")).click();
    }

    public QMSPage openQmsEmulator(){
        gotoUrl(env.baseUrl + env.qmsUrl);
        return qmsPage;
    }


}
