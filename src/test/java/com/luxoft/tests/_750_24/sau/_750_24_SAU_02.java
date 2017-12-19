package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.stats.DailyReportPage;
import com.luxoft.mfcautotests.pages.stats.StatsAdminPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    DaoPostgres daoPostgres;

    LocalDateTime periodStart;


    @BeforeClass
    public void beforeClassSetup() {
        periodStart = statsHelper.dateFromServerLessThenEightAm() ?
                LocalDateTime.now().minusDays(2).withHour(8).withMinute(0).withSecond(0).withNano(0) :
                LocalDateTime.now().minusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);

//        statsHelper.deleteDailyStatsFromDb(periodStart)
//                .insertDailyStatsItemsToDb(periodStart);
    }

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


    @Test (priority = 1)
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
        dailyReportPage.checkDefaultDate(periodStart);
    }

    @Test
    @Title("Проверка выбора даты")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void dateSelectionTest() {
        dailyReportPage.checkDateSelection();
    }

    @Test
    @NonDriver
    @Title("Формирование отчета")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void creatingReport() {
        dailyReportPage.generateRerport();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dailyReportPage.dashboardItemsCheckBox.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MfcStatsGroup> dailyStatsFromUi = dailyReportPage.getDailyStatsValuesFromUi();

//        dailyStatsFromUi.stream()
//                .peek(group -> System.out.println("group " + group.getName()))
//                .map(MfcStatsGroup::getMfcStatsItems)
//                .flatMap(Collection::stream)
//                .peek(item -> System.out.println("item " + item.getName()))
//                .collect(Collectors.toList());

    }
}

