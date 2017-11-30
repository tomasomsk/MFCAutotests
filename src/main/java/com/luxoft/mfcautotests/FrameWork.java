package com.luxoft.mfcautotests;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameWork<T> {

    @InjectLogger
    public Logger log;

    public T sleep(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public String getSubstringFromString(String input, String pattern){
        log.info("Getting substring from string");
        String output = null;
        Pattern ptrn = Pattern.compile(pattern);
        Matcher matcher = ptrn.matcher(input);
        if (matcher.find()) {
            output = (matcher.group(1));
        }
        return output;
    }

    public Date getDateFromString(String date, String pattern) {
        log.info("Getting Date from String");
        try {
            SimpleDateFormat sf = new SimpleDateFormat(pattern);
            return sf.parse(date);
        } catch (ParseException e) {
            throw  new RuntimeException(e);
        }
    }

    public Date getCurrentDateWithDefinedTime(int hour, int min, int sec) {
        log.info("Getting current date with time " + hour + ":" + min + ":" + sec);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    public String getStringFromDate(Date date, String pattern) {
//        log.info("Getting String from Date");
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

}
