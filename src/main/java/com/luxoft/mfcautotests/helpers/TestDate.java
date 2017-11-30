package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;

import java.util.Date;

public class TestDate extends BaseHelper {


    public static void main(String[] args) {
        TestDate testDate = new TestDate();
        Date date = new Date();
        Date newDate = testDate.addDaysToDate(date, 32);
        System.out.println(testDate.getStringFromDate(newDate, "dd"));
    }


}
