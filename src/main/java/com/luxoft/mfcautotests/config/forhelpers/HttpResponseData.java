package com.luxoft.mfcautotests.config.forhelpers;

import org.apache.http.cookie.Cookie;
import org.jsoup.nodes.Document;

import java.util.List;

public class HttpResponseData {
    private Document responseData;
    private List<Cookie> cookies;

    public Document getResponseData() {
        return responseData;
    }

    public  List<Cookie> getCookies() {
        return cookies;
    }

    public HttpResponseData setResponseData(Document responseData) {
        this.responseData = responseData;
        return this;
    }

    public HttpResponseData setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
        return this;
    }
}
