package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import static org.testng.Assert.assertEquals;

@Page
public class BasePage<T> {

    @Autowired
    @Lazy
    public DriverUtils driverUtils;
    @InjectLogger
    Logger log;

    By saveButton = By.cssSelector(".btn.btn-success");
    By closeButton = By.xpath("//div[text()='Закрыть']");


    public String getTitle() {
        log.info("Getting title of the page");
        return driverUtils.getDriver().getTitle();
    }

    public T fillTheField(WebElement field, String text) {
        log.info("Filling the field '" + getLocator(field) + "' with value '" + text + "'");
        field.sendKeys(text);
        return (T) this;
    }

    // Split full locator name (with service information) to just locator for logs
    private String getLocator(WebElement element) {
        return element.toString().split("-> ")[1].split("]")[0];
    }

    public boolean isClickable(WebElement... elements) {
        try {
            for (WebElement temp : elements) {
                log.info("Checking, that link [" + getLocator(temp) + "] is clickable");
                driverUtils.getWait().until(ExpectedConditions.elementToBeClickable(temp));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotClickable(WebElement... elements) {
        WebDriverWait wait = new WebDriverWait(driverUtils.getDriver(), 3);
        try {
            for (WebElement temp : elements) {
                log.info("Checking, that link [" + getLocator(temp) + "] is not clickable");
                wait.until(ExpectedConditions.elementToBeClickable(temp));
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public WebElement waitUntilClickable(WebElement element) {
        driverUtils.getWait().until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public By waitUntilPresent(By locator) {
        driverUtils.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        return locator;
    }

    public boolean isDisplayed(WebElement... elements) {
        int result = 0;
        for (WebElement temp : elements) {
            if (temp.isDisplayed()) {
                log.info("Checking, that element [" + getLocator(temp) + "] is displayed");
                result += 1;
            }
        }
        return result == elements.length;
    }

    public T clickSaveButton() {
        log.info("Clicking save button");
        waitUntilClickable(findElement(saveButton)).click();
        return (T) this;
    }

    public T clickCloseButtonOnModalWindow() {
        log.info("Clicking close button on modal window");
        waitUntilClickable(findElement(waitUntilPresent(closeButton))).click();

        return (T) this;
    }

    public WebElement findElement(By locator) {
        return driverUtils.getDriver().findElement(locator);
    }

    public T sleep(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public T refreshPage() {
        log.info("Refreshing the page");
        driverUtils.getDriver().get(driverUtils.getDriver().getCurrentUrl());
        return (T) this;
    }

    public T confirmAlertWindow() {
        driverUtils.getDriver().switchTo().alert().accept();
        return (T) this;
    }

    public boolean alertIsPresent() {
        try {
            Alert a = new WebDriverWait (driverUtils.getDriver(), 3).until(ExpectedConditions.alertIsPresent());
            if (a != null) {
                return true;
            } else {
                throw new Throwable();
            }
        } catch (Throwable e) {
            return false;
        }
    }


}
