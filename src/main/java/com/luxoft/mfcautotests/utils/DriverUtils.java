package com.luxoft.mfcautotests.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
public class DriverUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    @Autowired
    ApplicationContext context;

    @Step("Скриншот ошибки")
    @Attachment(value = "{0}", type = "image/png")
    public byte[] makeScreenshot(String name) {
        byte[] screenshot = (((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        return screenshot;
        }

    private void createDriver(){
            driver = (WebDriver) context.getBean("webDriver");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 15);
    }

    public void killDriver() {
        if (!(driver == null)){
            driver.quit();
        }
        driver = null;
    }

    public WebDriver getDriver() {
        if(driver == null) {
            createDriver();
        }
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
}