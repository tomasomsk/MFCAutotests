package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Helper
public class BaseHelper {

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

    public void sleep(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getSubstringFromString(String input, String pattern){
        String output = null;
        Pattern ptrn = Pattern.compile(pattern);
        Matcher matcher = ptrn.matcher(input);
        if (matcher.find())
            output = (matcher.group(1));
        return output;
    }
}
