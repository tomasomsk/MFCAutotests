package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.FrameWork;
import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Calendar;
import java.util.Date;

@Helper
public class BaseHelper<T> extends FrameWork {

    @InjectLogger
    Logger log;
    @Autowired
    @Lazy
    DriverUtils driverUtils;
    @Autowired
    TestEnvironment env;

    public Date addDaysToDate(Date date, int countOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, countOfDays);
        return calendar.getTime();
    }

}
