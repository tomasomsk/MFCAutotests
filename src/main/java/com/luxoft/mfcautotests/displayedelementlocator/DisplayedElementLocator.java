package com.luxoft.mfcautotests.displayedelementlocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;

import java.lang.reflect.Field;

public class DisplayedElementLocator extends AjaxElementLocator {

    public DisplayedElementLocator(WebDriver driver, Field field, int timeOutInSeconds) {
        super(driver, field, timeOutInSeconds);
    }

    @Override
    protected boolean isElementUsable(WebElement element) {
        return element.isDisplayed();
    }
}
