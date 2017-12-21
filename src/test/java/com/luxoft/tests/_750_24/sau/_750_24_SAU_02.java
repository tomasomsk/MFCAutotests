package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
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
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        periodStart = statsHelper.currentTimeLessThanEightAm() ?
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


    @Test
    @Title("Вид экрана Ежелневного отчета")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void dailyReportScreenSpecificationTest() {
        dailyReportPage.checkTableColumnNames();

        assertTrue(dailyReportPage.isDisplayed(
                dailyReportPage.dailyTypeOfReportLabel,
                dailyReportPage.linkToStatsAdminArm,
                dailyReportPage.reportDateLable,
                dailyReportPage.reportDateField,
                dailyReportPage.dataSourceLabel,
                dailyReportPage.dataSourceDropDown,
                dailyReportPage.dashboardItemsLabel,
                dailyReportPage.saveButton)
                &&
                dailyReportPage.isNotClickable(
                        dailyReportPage.reportDateField,
                        dailyReportPage.dashboardItemsCheckBox,
                        dailyReportPage.saveButton)
                &&
                dailyReportPage.isNotClickable(
                        dailyReportPage.dataSourceDropDown, new ClickableConfig("select2-container-disabled"))
                &&
                dailyReportPage.isClickable(
                        dailyReportPage.reportDateCalendarLink,
                        dailyReportPage.createReportButton,
                        dailyReportPage.goBackButton));
    }

    @Test
    @Title("Отчетная дата по умолчанию")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void defaultReportDateTest() {
        String reportDateFromUi = dailyReportPage.reportDateField.getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String expectedDate = statsHelper.currentTimeLessThanEightAm() ?
                LocalDateTime.now().minusDays(2).format(formatter) :
                LocalDateTime.now().minusDays(1).format(formatter);

        assertEquals(reportDateFromUi, expectedDate);
    }

    @Test
    @Title("Недоступность дат")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void dateSelectionTest() {
        dailyReportPage.checkDateSelection();
    }

    @Test
    @Title("Вид экрана после формирование отчета")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void creatingReport() {
        dailyReportPage.generateRerport()
                .checkTableLabel(periodStart);

        assertTrue(dailyReportPage.isClickable(
                dailyReportPage.uploadToExcelButton,
                dailyReportPage.uploadToPdfButton,
                dailyReportPage.saveButton,
                dailyReportPage.goBackButton,
                dailyReportPage.dataSourceDropDown,
                dailyReportPage.dashboardItemsCheckBox));
    }

    @Test
    @Title("Сравнение значений в БД и на UI")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void matchingValuesDbUi() {
        dailyReportPage.generateRerport();

        List<MfcStatsGroup> mfcDailyStatsFromDb = statsHelper.getMfcDailyStatsFromExcel();
        List<MfcStatsGroup> mfcDailyStatsFromUi = dailyReportPage.getDailyStatsFromUi();

        List<MfcStatsItem> mfcDailyStatsFromDbSorted = statsHelper.createSortedListOfMfcStatsItems(mfcDailyStatsFromDb);
        List<MfcStatsItem> mfcDailyStatsFromUiSorted = statsHelper.createSortedListOfMfcStatsItems(mfcDailyStatsFromUi);

        assertEquals(mfcDailyStatsFromDbSorted.size(), mfcDailyStatsFromUiSorted.size());

        IntStream.range(0, mfcDailyStatsFromDbSorted.size())
                .forEach(i -> mfcDailyStatsFromDbSorted.get(i).equalsAsUiObject(mfcDailyStatsFromUiSorted.get(i)));
    }

    @Test
    @Title("Отображение красным не полученных от внешних систем значений")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void redValuesNotFromWs() {
        dailyReportPage.generateRerport();
    }




//        List<MfcStatsGroup> dailyStatsFromExcel = statsHelper.getMfcDailyStatsFromExcel();
//        List<MfcStatsGroup> dailyStatsFromUi = dailyReportPage.getDailyStatsFromUi();

//        dailyStatsFromExcel.stream()
//                .peek(group -> System.out.print("excel " + group.getName() + " "))
//                .collect(Collectors.toList());
//
//        System.out.println();
//
//        dailyStatsFromUi.stream()
//                .peek(group -> System.out.print("ui " + group.getName() + " "))
//                .map(MfcStatsGroup::getMfcStatsItems)
//                .flatMap(Collection::stream)
//                .peek(item -> System.out.println("item " + item.getName()))
//                .collect(Collectors.toList());

}

