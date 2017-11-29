package com.luxoft.tests._750_27.pddb;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.helpers.DashboardHelper;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Date;

import static org.testng.Assert.assertEquals;

// ТЕСТОВЫЙ СЦЕНАРИЙ: ПДДБ-01 НЕУСПЕШНАЯ ПЕРЕДАЧА АНАЛИТИЧЕСКИХ ДАННЫХ В ДЭШБОРД (ВАЛИДАЦИЯ ДАННЫХ ЗАПРОСА ЗАВЕРШЕНА ОШИБКОЙ)
public class _750_27_PDDB_1 extends BaseTest {

    @Autowired
    DashboardHelper dashboardHelper;

    @Test
    @Title("Запрос с текущей датой")
    @Features("750-27 Передача данных о работе ММЦ в Дэшборд")
    @Stories("ПДДБ-01 Неуспешная передача ежедневных аналитических данных в Дэшборд (Валидация данных запроса завершена ошибкой)")
    @NonDriver
    public void RequestMainMmcStatsWithCurrentDateTest() {
        MainMmcStatsResponseType responseFromMfc = dashboardHelper.getMainMmcStatsResponse(new Date());

        assertEquals(responseFromMfc.getResult().getCode(), "NO_DATA");
        assertEquals(responseFromMfc.getResult().getErrorDescr(), "Некорректно задана дата, за которую запрашивается аналитика.");
    }

    @Test
    @Title("Запрос с текущей датой +1 день")
    @Features("750-27 Передача данных о работе ММЦ в Дэшборд")
    @Stories("ПДДБ-01 Неуспешная передача ежедневных аналитических данных в Дэшборд (Валидация данных запроса завершена ошибкой)")
    @NonDriver
    public void RequestMainMmcStatsWithTomorrowDateTest() {
        Date date = dashboardHelper.addDaysToDate(new Date(), 1);
        MainMmcStatsResponseType responseFromMfc = dashboardHelper.getMainMmcStatsResponse(date);

        assertEquals(responseFromMfc.getResult().getCode(), "NO_DATA");
        assertEquals(responseFromMfc.getResult().getErrorDescr(), "Некорректно задана дата, за которую запрашивается аналитика.");
    }

    @Test
    @Title("Запрос с текущей датой -32 дня")
    @Features("750-27 Передача данных о работе ММЦ в Дэшборд")
    @Stories("ПДДБ-01 Неуспешная передача ежедневных аналитических данных в Дэшборд (Валидация данных запроса завершена ошибкой)")
    @NonDriver
    public void RequestMainMmcStatsWithPastMonthDateTest() {
        Date date = dashboardHelper.addDaysToDate(new Date(), -32);
        MainMmcStatsResponseType responseFromMfc = dashboardHelper.getMainMmcStatsResponse(date);

        assertEquals(responseFromMfc.getResult().getCode(), "NO_DATA");
        assertEquals(responseFromMfc.getResult().getErrorDescr(), "Некорректно задана дата, за которую запрашивается аналитика.");
    }
}
