package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.stats.DailyReportPage;
import com.luxoft.mfcautotests.pages.stats.StatsAdminPage;
import org.springframework.beans.factory.annotation.Autowired;
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
    DailyReportPage dailyReportPage;
    @Autowired
    LoginPage loginPage;
    @Autowired
    StatsHelper statsHelper;

    @BeforeMethod
    public void setup() {
        if (!driverUtils.getDriver().getCurrentUrl().contains("mmcstatsadmin")) {
            navHelper.openArmStatsAdmin()
                    .loginWithRole(Role.STATS_ADMIN);
        }
        if (!driverUtils.getDriver().getCurrentUrl().contains("mmcstatsadmin/daily_stats.htm")) {
            statsAdminPage.openDailyReport();
        }
    }


    @Test
    @Title("Внешний вид экрана Ежелневного отчета")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void dailyReportScreenSpecificationTest() {
        dailyReportPage.checkDailyReportPageElements();
    }

    @Test
    @Title("Проверка отчетной даты по умолчанию")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void defaultReportDateTest() {
        statsHelper.checkDefaultDateForDailyStats();
    }

    @Test
    @Title("Проверка выбора даты")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void dateSelectionTest() {
        dailyReportPage.checkDateSelection();
    }


}
