package com.luxoft.mfcautotests.config.forhelpers;

import org.apache.http.cookie.Cookie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginParameters {

    String userName;
    String password;
    String _eventId;
    String execution;
    String service;
    String lt;
    List<Cookie> cookies;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String get_eventId() {
        return _eventId;
    }

    public String getExecution() {
        return execution;
    }

    public String getService() {
        return service;
    }

    public String getLt() {
        return lt;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public LoginParameters setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public LoginParameters setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginParameters set_eventId(String _eventId) {
        this._eventId = _eventId;
        return this;
    }

    public LoginParameters setExecution(String execution) {
        this.execution = execution;
        return this;
    }

    public LoginParameters setService(String service) {
        this.service = service;
        return this;
    }

    public LoginParameters setLt(String lt) {
        this.lt = lt;
        return this;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
