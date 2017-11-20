package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.config.annotations.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Page
public class SearchPage extends BasePage {

    @Autowired
    ApplicationPage applicationPage;

    @FindBy(css = "[ng-click='doSearch()']")
    public WebElement doSearchButton;

    @FindBy(css = "[ng-click='addNewApplication()']")
    public WebElement addAppButton;

    @FindBy(id = "filterLastName")
    public WebElement filterLastName;


    public ApplicationPage addApplication() {
        log.info("Adding new application");
        addAppButton.click();
        return applicationPage;
    }
}
