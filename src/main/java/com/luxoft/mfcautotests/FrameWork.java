package com.luxoft.mfcautotests;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.config.forpages.ElementToFindInList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    public String getSubstringFromString(String input, String pattern) {
        String output = null;
        Pattern ptrn = Pattern.compile(pattern);
        Matcher matcher = ptrn.matcher(input);
        if (matcher.find()) {
            output = (matcher.group(1));
        }
        return output;
    }

    public Date getCurrentDateWithDefinedTime(int hour, int min, int sec) {
        log.info("Getting current date with time " + hour + ":" + min + ":" + sec);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    public Date getDateFromString(String date, String pattern) {
        log.info("Getting Date from String");
        try {
            SimpleDateFormat sf = new SimpleDateFormat(pattern);
            return sf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStringFromDate(Date date, String pattern) {
        log.info("Getting String from Date");
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    public int getIntFromDate(Date date, String pattern) {
        return Integer.parseInt(getStringFromDate(date, pattern));
    }

    public Date addDaysToDate(Date date, int countOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, countOfDays);
        return calendar.getTime();
    }

    public Object getPositionsOfElementInList(ElementToFindInList elementToFind, List list) {
        if (elementToFind.getType().equalsIgnoreCase("int")) {
            return getPositionsOfIntElementInList(elementToFind, list);
        } else {
            throw new RuntimeException("Unknown type of element to find");
        }
    }

    public Integer[] getPositionsOfIntElementInList(ElementToFindInList element, List<Integer> list) {
        return IntStream.range(0, list.size())
                .filter(i -> list.get(i) == element.getIntValue()).boxed().toArray(Integer[]::new);
    }

    public String formatDate(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(date);
    }
}
