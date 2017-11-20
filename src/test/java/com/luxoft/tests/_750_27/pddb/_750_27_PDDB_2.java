package com.luxoft.tests._750_27.pddb;

import com.luxoft.BaseTest;
import com.luxoft.annotations.NonDriver;
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

// ТЕСТОВЫЙ СЦЕНАРИЙ: ПДДБ-02 НЕУСПЕШНАЯ ПЕРЕДАЧА АНАЛИТИЧЕСКИХ ДАННЫХ В ДЭШБОРД (В СИСТЕМЕ НЕДОСТАТОЧНО ДАННЫХ ДЛЯ ПЕРЕДАЧИ В ДЭШБОРД)
public class _750_27_PDDB_2 extends BaseTest{

    @Autowired
    DaoPostgres daoPostgres;
    @Autowired
    DashboardHelper dashboardHelper;

    @Test
    @Title("Запрос на валидную дату, когда не хватает показателей")
    @Features("750-27 Передача данных о работе ММЦ в Дэшборд")
    @Stories("ПДДБ-02 Неуспешная передача аналитических данных в Дэшборд (в системе недостаточно данных для передачи в Дэшборд)")
    @NonDriver
    public void RequestMainMmcStatsNotEnoughDataTest() throws SQLException {
        Date date = dashboardHelper.addDaysToDate(new Date(), -21);
        daoPostgres.deleteMainStatsData(21);
        MainMmcStatsResponseType responseFromMfc = dashboardHelper.getMainMmcStatsResponse(date);

        assertEquals(responseFromMfc.getResult().getCode(), "NO_DATA");
        assertEquals(responseFromMfc.getResult().getErrorDescr(), "В ИС ММЦ недостаточно данных на запрошенную дату.");
    }
}
