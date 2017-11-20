package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

@Page
public class LoginPage extends BasePage{

    @FindBy(id = "username")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "loginFormSubmit")
    private WebElement submitButton;

    @FindBy(css = "[href*='logout']")
    private  WebElement logoutLink;

    public void login(User user) {
        log.info("Logging in");
        fillTheField(login, user.getLogin());
        fillTheField(password, user.getPassword());
        submitButton.click();
    }

    public LoginPage logout() {
        log.info("Logging out");
        waitUntilClickable(logoutLink).click();
        if(alertIsPresent()) {
            driverUtils.getDriver().switchTo().alert().accept();
        }
        return this;
    }
}
