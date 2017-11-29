package com.luxoft.mfcautotests.pages.app;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Page
public class ApplicationPage<T> extends BasePage {

    public void selectSection(String section) {
        log.info("Selecting section " + section);
        driverUtils.getWait().until(visibilityOfElementLocated(By.xpath("//a[@ng-click=\"wizardSelectContent('" + section + "')\"]"))).click();
        sleep(2000);
    }

    public T setListValue(String listName, String value) {
        waitUntilClickable(findElement(By.name(listName))).click();
        waitUntilClickable(findElement(By.xpath("//div[@name='" + listName + "']//input[@ng-model='$select.search']"))).sendKeys(value);
        waitUntilClickable(findElement((By.xpath("//div[@name='" + listName + "']//li[@class='ui-select-choices-row ng-scope select2-highlighted']")))).click();
        sleep(100);
        return (T) this;
    }

    public T setCustomListValue(WebElement list, WebElement inputField, String value){
        waitUntilClickable(list).click();
        waitUntilClickable(inputField).sendKeys(value, Keys.RETURN);
        sleep(100);
        return (T) this;
    }



}
