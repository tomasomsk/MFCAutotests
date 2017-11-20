package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.StatsAdminPage;
import com.luxoft.mfcautotests.pages.StatsUserPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.AssertJUnit.assertTrue;

public class _750_24_SAU_02 extends BaseTest {

    @Autowired
    User user;
    @Autowired
    StatsAdminPage statsAdminPage;
    @Autowired
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        if (! driverUtils.getDriver().getCurrentUrl().contains("mmcstatsadmin/daily_stats.htm")) {
            navHelper.openArmStatsAdmin()
                    .loginWithRole(Role.STATS_ADMIN);
            statsAdminPage.openDailyReport();
        }
    }


    @Test
    @Title("Внешний вид экрана Ежелневного отчета")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void  dailyReportScreenSpecificationTest() {

    }
}
