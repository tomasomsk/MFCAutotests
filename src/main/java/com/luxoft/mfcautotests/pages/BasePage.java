package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.FrameWork;
import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.luxoft.mfcautotests.config.forpages.ClickableConfig.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Page
public class BasePage<T> extends FrameWork {

    @Autowired
    @Lazy
    public DriverUtils driverUtils;

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
//        return element.toString().split("-> ")[1].split("]")[0];
        return getSubstringFromString(element.toString(), "-> (.*)]");
    }

    public boolean isClickable(WebElement... elements) {
        try {
            for (WebElement checkedElement : elements) {
                log.info("Checking, that link [" + getLocator(checkedElement) + "] is clickable");
                driverUtils.getWait().until(elementToBeClickable(checkedElement));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotClickable(WebElement... elements) {
        for (WebElement checkedElement : elements) {
            if (!isNotClickable(checkedElement, defaultConfig)) {
                return false;
            }
        }
        return true;


//        List<WebElement> elementsChecked = new ArrayList<>();
//        List<WebElement> elementsToCheckByClass = new ArrayList<>();
//        List<WebElement> elementsToCheckByClick = new ArrayList<>();
//        List<WebElement> elementsToCheckBySendKeys = new ArrayList<>();
//
//        for (WebElement checkedElement : elements) {
//            log.info("Checking, that element [" + getLocator(checkedElement) + "] is not clickable by isEnabled()");
//            if (checkedElement.isEnabled()) {
//                elementsToCheckByClass.add(checkedElement);
//            } else {
//                elementsChecked.add(checkedElement);
//            }
//        }
//        if (!elementsToCheckByClass.isEmpty()) {
//            for (WebElement checkedByClassElement : elementsToCheckByClass) {
//                log.info("Checking, that element [" + getLocator(checkedByClassElement) + "] is not clickable by class");
//                String classOfElement = checkedByClassElement.getAttribute("class");
//                List<String> classes = new ArrayList<>(Arrays.asList(classOfElement.split(" ")));
//                if (!classes.contains("select2-container-disabled")) {
//                    elementsToCheckByClick.add(checkedByClassElement);
//                } else {
//                    elementsChecked.add(checkedByClassElement);
//                }
//            }
//        }
//        if (!elementsToCheckBySendKeys.isEmpty()) {
//            for (WebElement checkedBySendKeysElement : elementsToCheckBySendKeys) {
//                log.info("Checking, that element [" + getLocator(checkedBySendKeysElement) + "] is not clickable by sending keys");
//                try {
//                    checkedBySendKeysElement.sendKeys("checking");
//                    elementsToCheckByClick.add(checkedBySendKeysElement);
//                } catch (Exception e) {
//                    elementsChecked.add(checkedBySendKeysElement);
//                }
//            }
//        }
//        if (!elementsToCheckByClick.isEmpty()) {
//            WebDriverWait wait = new WebDriverWait(driverUtils.getDriver(), 1);
//            for (WebElement checkedByClickElement : elementsToCheckByClick) {
//                log.info("Checking, that element [" + getLocator(checkedByClickElement) + "] is not clickable by clicking it");
//                try {
//                    checkedByClickElement.click();
//                    return false;
//                } catch (Exception e) {
//                    elementsChecked.add(checkedByClickElement);
//                }
//            }
//        }
//        return elementsChecked.size() == elements.length;
    }

    public boolean isNotClickable(WebElement element, ClickableConfig clickableConfig) {

        if (!clickableConfig.equals(defaultConfig)) {
            log.info("Checking, that element [" + getLocator(element) + "] is not clickable by class");
            String classOfElement = element.getAttribute("class");
            List<String> classes = new ArrayList<>(Arrays.asList(classOfElement.split(" ")));
            if (classes.contains(clickableConfig.getElementDisabledMarker())) {
                return true;
            }
        }

        log.info("Checking, that element [" + getLocator(element) + "] is not clickable by isEnabled()");
        if (!element.isEnabled()) {
            return true;
        }

        log.info("Checking, that element [" + getLocator(element) + "] is not clickable by sending keys");
        try {
            element.sendKeys("checking");
        } catch (Exception e) {
            return true;
        }

        log.info("Checking, that element [" + getLocator(element) + "] is not clickable by clicking it");
        try {
            element.click();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public WebElement waitUntilClickable(WebElement element) {
        driverUtils.getWait().until(elementToBeClickable(element));
        return element;
    }

    public By waitUntilPresent(By locator) {
        driverUtils.getWait().until(presenceOfElementLocated(locator));
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

    public T refreshPage() {
        log.info("Refreshing the page");
        driverUtils.getDriver().get(driverUtils.getDriver().getCurrentUrl());
        return (T) this;
    }

    public T confirmAlertWindow() {
        driverUtils.getDriver().switchTo().alert().accept();
        return (T) this;
    }

    public boolean isAlertPresent() {
        try {
            Alert a = new WebDriverWait(driverUtils.getDriver(), 3).until(alertIsPresent());
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
