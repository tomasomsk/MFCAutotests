package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.helpers.StatsHelper;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSql extends BaseTest {

    @Autowired
    DaoPostgres daoPostgres;
    @Autowired
    StatsHelper statsHelper;

    @Test
    @NonDriver
    public void testSql() {
//        statsHelper.insertDailyStatsItemsToDb(LocalDateTime.of(2017, 12, 14, 8, 0, 0));

//        String integers = "[1233457890]";
//        System.out.println(daoPostgres.getSubstringFromString(integers.toString(), "\\[(.*)\\]"));

        List<MfcStatsGroup> mfcDailyStats = statsHelper.getMfcDailyStatsFromExcel();

        List<MfcStatsItem> itemsWithSourceEmias = statsHelper.getItemsWithSource(1, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceBank = statsHelper.getItemsWithSource(2, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceQms = statsHelper.getItemsWithSource(3, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcAuto = statsHelper.getItemsWithSource(5, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcManual = statsHelper.getItemsWithSource(6, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourcePpot = statsHelper.getItemsWithSource(8, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceTesting = statsHelper.getItemsWithSource(16, mfcDailyStats);


        LocalDateTime periodStart = LocalDateTime.of(2017, 12, 13, 8, 0, 0);
        List<Integer> dataUploadIds = daoPostgres.selectDataUploadIdsForDate(periodStart);
        if (!dataUploadIds.isEmpty()) {
            daoPostgres.deleteFromAnReportItemData(dataUploadIds);
        }
        daoPostgres.deleteFromAnDataUpload(periodStart);
        daoPostgres.insertInReportItemDataItemsWithSource(itemsWithSourceBank, periodStart);
    }
}
