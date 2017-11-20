package com.luxoft.mfcautotests.displayedelementlocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class DisplayedElementLocatorFactory implements ElementLocatorFactory {


    WebDriver driver;
    private final int timeOutInSeconds;

    public DisplayedElementLocatorFactory(WebDriver driver, int timeOutInSeconds) {
        this.driver = driver;
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public ElementLocator createLocator(Field field) {
        return new DisplayedElementLocator(driver, field, timeOutInSeconds);
    }
}
