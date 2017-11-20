package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

@Helper
public class QMSHelper extends ServicesHelper {

    public void getTicketForWindowId(String windowId) {
        log.info("Getting a new ticket for windowId " + windowId);
        StringWriter request = new StringWriter().append(windowId);
        HttpResponse responseFromQms = sendPostRequestToService(request, env.baseUrl+env.qmsUrl + "setNextTicket", "application/json; charset=UTF-8");
        StringBuilder sbResponseFromQms = getResponseAsStringBuilder(responseFromQms);
        log.info("Ticket number is " + getSubstringFromString(sbResponseFromQms.toString(), "\"ticketId\":\"(.*?)\""));
    }
}
