package com.luxoft.mfcautotests;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.config.forhelpers.HttpResponseData;
import com.luxoft.mfcautotests.config.forpages.ElementToFindInList;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    public HttpResponseData sendGetHttpRequest(String url) {
        return sendGetHttpRequest(url, new HashMap<>());
    }

    public HttpResponseData sendGetHttpRequest(String url, Map<String, String> urlParameters) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();
            URIBuilder uriBuilder = new URIBuilder(url);

            if (urlParameters.size() > 0) {
                for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }

            url = uriBuilder.build().toString();

            log.info("Sending http get request " + url);

            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpGet, context);
            CookieStore cookieStore = context.getCookieStore();
            List<Cookie> cookies = cookieStore.getCookies();

            HttpEntity entity = response.getEntity();
            String data = IOUtils.toString(entity.getContent(), "UTF-8");
            Document document = Jsoup.parse(data);

            HttpResponseData responseData = new HttpResponseData();
            responseData.setResponseData(document)
                    .setCookies(cookies);

            response.close();

            return responseData;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponseData sendPostHttpRequest(String url) {
        return sendPostHttpRequest(url, new ArrayList<>());
    }

    public HttpResponseData sendPostHttpRequest(String url, List<BasicNameValuePair> postParameters) {
        return sendPostHttpRequest(url, postParameters, new Cookie[0]);
    }

    public HttpResponseData sendPostHttpRequest(String url, List<BasicNameValuePair> postParameters, Cookie[] cookies) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicNameValuePair parameter : postParameters) {
            stringBuilder.append(parameter.getName())
                    .append(" = ")
                    .append(parameter.getValue())
                    .append("; ");
        }

        log.info("Sending http post request " + url + " with parameters: " + stringBuilder.toString());

        CloseableHttpClient client;
        HttpResponseData httpResponseData = new HttpResponseData();
        HttpClientContext context = HttpClientContext.create();
        if (cookies.length > 0) {
            BasicCookieStore cookieStore = new BasicCookieStore();
            cookieStore.addCookies(cookies);
            client = HttpClientBuilder.create()
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .setDefaultCookieStore(cookieStore)
                    .build();
        } else {
            client = HttpClientBuilder.create()
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .build();
        }
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36 ql66u54b");

        try {
            if (postParameters.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(postParameters));
            }

            CloseableHttpResponse response = client.execute(post, context);

            CookieStore cookieStore = context.getCookieStore();
            List<Cookie> cookiesFromResponse = cookieStore.getCookies();

            HttpEntity entity = response.getEntity();
            String data = IOUtils.toString(entity.getContent(), "UTF-8");

            Document document = Jsoup.parse(data);

            httpResponseData.setResponseData(document)
                    .setCookies(cookiesFromResponse);

            response.close();

            return httpResponseData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
