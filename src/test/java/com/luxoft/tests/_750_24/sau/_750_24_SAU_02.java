package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.model.*;
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
import java.util.List;
import java.util.stream.Collectors;

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
    public void creatingReportTest() {
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
    public void matchingValuesDbUiTest() {
        dailyReportPage.generateRerport();

        List<MfcStatsGroup> mfcDailyStatsFromDb = statsHelper.getMfcDailyStatsFromExcel();
        List<MfcStatsGroup> mfcDailyStatsFromUi = dailyReportPage.getDailyStatsFromUi();
        //Remove key indicators
        mfcDailyStatsFromUi.remove(0);

        List<MfcStatsItem> mfcDailyItemsFromDbSorted = statsHelper.createSortedListOfMfcStatsItems(mfcDailyStatsFromDb);
        List<MfcStatsItem> mfcDailyItemsFromUiSorted = statsHelper.createSortedListOfMfcStatsItems(mfcDailyStatsFromUi);

        List<MfcStatsItemForEquals> statsItemsFromDbForEquals = statsHelper.transformForEquals(mfcDailyItemsFromDbSorted);
        List<MfcStatsItemForEquals> statsItemsFromUiForEquals = statsHelper.transformForEquals(mfcDailyItemsFromUiSorted);

        assertTrue(statsItemsFromDbForEquals.size() == statsItemsFromUiForEquals.size());

        for (int i = 0; i < statsItemsFromDbForEquals.size(); i++) {
            statsHelper.log.info("Asserting item " + statsItemsFromDbForEquals.get(i).getName() + " with " + statsItemsFromUiForEquals.get(i).getName());

            assertEquals(statsItemsFromDbForEquals.get(i).getName(),
                    statsItemsFromUiForEquals.get(i).getName());
//            assertEquals(statsItemsFromDbForEquals.get(i).getDimension(),
//                    statsItemsFromUiForEquals.get(i).getDimension());
            assertEquals(statsItemsFromDbForEquals.get(i).getFillingType(),
                    statsItemsFromUiForEquals.get(i).getFillingType());
            assertEquals(statsItemsFromDbForEquals.get(i).getDataSource(),
                    statsItemsFromUiForEquals.get(i).getDataSource());
            assertEquals(statsItemsFromDbForEquals.get(i).getValue(),
                    statsItemsFromUiForEquals.get(i).getValue());
            assertEquals(statsItemsFromDbForEquals.get(i).getValueYear(),
                    statsItemsFromUiForEquals.get(i).getValueYear());
            assertEquals(statsItemsFromDbForEquals.get(i).getValuePrevYear(),
                    statsItemsFromUiForEquals.get(i).getValuePrevYear());
        }

    }

    @Test
    @Title("Отображение красным не полученных от внешних систем значений")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-02 Ежедневный отчет (АРМ Администратора отчетов)")
    public void redValuesIfNotFromWsTest() {
        dailyReportPage.generateRerport();

        List<MfcStatsGroup> mfcDailyStatsFromDb = statsHelper.getMfcDailyStatsFromExcel();

        List<MfcStatsItem> handItems = mfcDailyStatsFromDb.stream()
                .map(MfcStatsGroup::getMfcStatsItems)
                .flatMap(Collection::stream)
                .filter(item -> (item.getDataSource() == 6 | item.getDataSource() == 14))
                .collect(Collectors.toList());

        for (MfcStatsItem handItem : handItems) {
            assertTrue(dailyReportPage.isValuesForItemRed(handItem.getName()));
        }
    }

    @Test
    public void ttt() {
        dailyReportPage.generateRerport();

        List<MfcStatsGroup> mfcDailyStatsFromUi = dailyReportPage.getDailyStatsFromUi();

        String value = mfcDailyStatsFromUi.get(0).getMfcStatsItems().get(0).getValue();

        System.out.println("value = " + value);

        String valueWithoutSpaces = value.replaceAll("\\s", "");

        System.out.println("value without spaces = " + valueWithoutSpaces);

        String valueAfterDouble = String.valueOf(Double.parseDouble(valueWithoutSpaces));

        System.out.println("value after Double = " + valueAfterDouble);

//        List<MfcStatsGroup> fromUi = dailyReportPage.getDailyStatsFromUi();
//
//        fromUi.stream()
//                .map(MfcStatsGroup::getMfcStatsItems)
//                .flatMap(Collection::stream)
//                .filter(item -> item.getDataSource() == 14)
//                .peek(item -> System.out.println("value " + item.getValue() + " valueYear " + item.getValueYear() +
//                " valuePrevYear " + item.getValuePrevYear()))
//                .collect(Collectors.toList());

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

