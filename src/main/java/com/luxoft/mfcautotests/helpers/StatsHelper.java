package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.pages.stats.DailyReportPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.assertEquals;

@Helper
public class StatsHelper extends ServicesHelper {

    @Autowired
    SSHHelper sshHelper;
    @Autowired
    DailyReportPage dailyReportPage;

    public void checkDefaultDateForDailyStats() {
        Date dateFromServer = sshHelper.getDateFromServer(env.dbUrl);
        Date currentDateEightOClock = getCurrentDateWithDefinedTime(8, 0, 0);
        log.info("Getting report date from UI");
        String reportDateFromUi = dailyReportPage.reportDateField.getAttribute("value");
        String expectedDate;
        if (dateFromServer.compareTo(currentDateEightOClock) < 1) {
            expectedDate = getStringFromDate(addDaysToDate(currentDateEightOClock, -2), "dd.MM.yyyy");
            assertEquals(reportDateFromUi, expectedDate);
        } else {
            expectedDate = getStringFromDate(addDaysToDate(currentDateEightOClock, -1), "dd.MM.yyyy");
            assertEquals(reportDateFromUi, expectedDate);
        }
    }
}