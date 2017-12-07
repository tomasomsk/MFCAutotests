package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.pages.stats.DailyReportPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.testng.Assert.assertEquals;

@Helper
public class StatsHelper extends ServicesHelper {

    @Autowired
    SSHHelper sshHelper;
    @Autowired
    DailyReportPage dailyReportPage;

    public boolean dateFromServerLessThenEightAm() {
        Date dateFromServer = sshHelper.getDateFromServer(env.dbUrl);
        Date currentDateEightOClock = getCurrentDateWithDefinedTime(8, 0, 0);
        return dateFromServer.compareTo(currentDateEightOClock) < 1;
    }
}