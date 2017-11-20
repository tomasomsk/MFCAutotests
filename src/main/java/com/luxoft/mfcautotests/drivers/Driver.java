package com.luxoft.mfcautotests.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.stereotype.Component;

public enum Driver {
    FIREFOX(FirefoxDriver.class, "geckodriver.exe", "webdriver.gecko.driver"),
    CHROME(ChromeDriver.class, "chromedriver.exe", "webdriver.chrome.driver"),
    IE(InternetExplorerDriver.class, "IEDriverServer.exe", "webdriver.ie.driver");

    private String execName;
    private String propertyName;
    private Class clazz;

    Driver(Class clazz, String execName, String propertyName) {
        this.execName = execName;
        this.propertyName = propertyName;
        this.clazz = clazz;
    }

    public String getExecName() {
        return execName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class getClazz() {
        return clazz;
    }
}
