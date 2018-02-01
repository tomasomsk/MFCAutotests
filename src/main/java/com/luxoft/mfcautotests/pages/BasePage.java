package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.FrameWork;
import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.config.forpages.ClickableConfig;
import com.luxoft.mfcautotests.config.forpages.ElementToFindInList;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;

import java.util.*;

import static com.luxoft.mfcautotests.config.forpages.ClickableConfig.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.AssertJUnit.assertTrue;

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

        log.info("Checking, that element [" + getLocator(element) + "] is not clickable by clicking it");
        try {
            element.click();
        } catch (Exception e) {
            return true;
        }

        log.info("Checking, that element [" + getLocator(element) + "] is not clickable by sending keys");
        try {
            element.sendKeys("checking");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public void checkThatDatesDisabledFrom(Date fromDate, WebElement calendarLink) {
        checkThatDatesDisabledFrom(fromDate, 0, calendarLink);
    }

    public void checkThatDatesDisabledFrom(Date fromDate, int countDaysToCheck, WebElement calendarLink) {
        calendarLink.click();
        //get List of WebElements from UI calendar
        List<WebElement> daysInCalendarWebElement = getCalendarElementsAsWebElements();
        //get List of Integer from WebElements
        List<Integer> daysInCalendarInteger = getCalendarElementsAsInteger(daysInCalendarWebElement);
        Integer[] dayPositionsInCalendar = getDayPositionsInCalendar(fromDate, daysInCalendarInteger);
        ClickableConfig calendarDaysClickableConfig = new ClickableConfig("disabled");
        //if we have two same dates in one calendar than we take not old date to check
        if (isDateOld(daysInCalendarWebElement, dayPositionsInCalendar[0])) {
            int startIndex = dayPositionsInCalendar[1];
            if (countDaysToCheck == 0) {
                countDaysToCheck = daysInCalendarWebElement.size() - startIndex;
            }
            assertTrue(isDatesDisabled(startIndex, countDaysToCheck, daysInCalendarWebElement, calendarDaysClickableConfig));
        } else {
            int startIndex = dayPositionsInCalendar[0];
            if (countDaysToCheck == 0) {
                countDaysToCheck = daysInCalendarWebElement.size() - startIndex;
            }
            assertTrue(isDatesDisabled(startIndex, countDaysToCheck, daysInCalendarWebElement, calendarDaysClickableConfig));
        }
    }

    public boolean isDateOld(List<WebElement> calendar, int position) {
        return calendar.get(position).getAttribute("class").contains("old");
    }

    public boolean isDatesDisabled(int startIndex, int countDaysToCheck, List<WebElement> calendar, ClickableConfig clickableConfig) {
        if (startIndex + countDaysToCheck > calendar.size()) {
            throw new RuntimeException("DAYS TO CHECK HAS WENT TO THE NEXT MONTH");
        } else {
            for (int i = startIndex; i < startIndex + countDaysToCheck; i++) {
                System.out.println(calendar.get(i).getText());
                if (!isNotClickable(calendar.get(i), clickableConfig)) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<WebElement> getCalendarElementsAsWebElements() {
        return findElements(By.cssSelector(".day"));
    }

    public List<Integer> getCalendarElementsAsInteger(List<WebElement> daysInCalendarWebElement) {
        List<Integer> daysInCalendarInteger = new ArrayList<>();
        for (int i = 0; i < daysInCalendarWebElement.size(); i++) {
            daysInCalendarInteger.add(Integer.parseInt(daysInCalendarWebElement.get(i).getText()));
        }
        return daysInCalendarInteger;
    }

    public Integer[] getDayPositionsInCalendar(Date date, List<Integer> calendar) {
        int desiredDay = getIntFromDate(date, "dd");
        //Indexes of desired day in List of days from UI calendar
        ElementToFindInList dayToFind = new ElementToFindInList(desiredDay);
        return (Integer[]) getPositionsOfElementInList(dayToFind, calendar);
    }

    public void checkDate(Date expectedDate, String reportDateFromUi) {
        String expectedDateString = getStringFromDate(expectedDate, "dd.MM.yyyy");
        Assert.assertEquals(reportDateFromUi, expectedDateString);
    }

    public void setDateInCalendar(Date defaultDate, WebElement calendarLink) {
        calendarLink.click();
        int indexOfDay;
        List<WebElement> daysInCalendarWebElement = getCalendarElementsAsWebElements();
        //get List of Integer from calendar of WebElements
        List<Integer> daysInCalendarInteger = getCalendarElementsAsInteger(daysInCalendarWebElement);
        //Get positions of date we need in List of Integer
//        GetPositionsInListConfig dayToFind = new GetPositionsInListConfig(getIntFromDate(new Date(), "dd"));
        Integer[] dayPositionsInCalendar = getDayPositionsInCalendar(defaultDate, daysInCalendarInteger);
        //if we have two same dates in one calendar than we take not old date to set
        if (isDateOld(daysInCalendarWebElement, dayPositionsInCalendar[0])) {
            indexOfDay = dayPositionsInCalendar[1];
        } else {
            indexOfDay = dayPositionsInCalendar[0];
        }
        daysInCalendarWebElement.get(indexOfDay).click();
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

    public List<WebElement> findElements(By locator) {
        return driverUtils.getDriver().findElements(locator);
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
