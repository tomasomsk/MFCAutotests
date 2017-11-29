package com.luxoft.tests._750_27.pddb;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.helpers.DashboardHelper;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import java.sql.SQLException;
import java.util.Date;
import static org.testng.Assert.assertEquals;

// ТЕСТОВЫЙ СЦЕНАРИЙ: ПДДБ-03 УСПЕШНАЯ ПЕРЕДАЧА АНАЛИТИЧЕСКИХ ДАННЫХ В ДЭШБОРД
public class _750_27_PDDB_3 extends BaseTest{

    @Autowired
    DaoPostgres daoPostgres;
    @Autowired
    DashboardHelper dashboardHelper;

    @Test
    @Title("Запрос на валидную дату, когда все ОК")
    @Features("750-27 Передача данных о работе ММЦ в Дэшборд")
    @Stories("ПДДБ-03 Успешная передача аналитических данных в дэшборд")
    @NonDriver
    public void RequestMainMmcStatsSuccessTest() throws SQLException {
        Date date = dashboardHelper.addDaysToDate(new Date(), -31);

        daoPostgres.insertMainStatsData(31);

        MainMmcStatsResponseType responseFromMfc = dashboardHelper.getMainMmcStatsResponse(date);

        assertEquals(responseFromMfc.getResult().getCode(), "OK");
        dashboardHelper.assertThatResponseContainsItemsOfMainStats(responseFromMfc.getItem());
    }

}
