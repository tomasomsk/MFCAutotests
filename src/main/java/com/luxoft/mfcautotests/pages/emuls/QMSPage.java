package com.luxoft.mfcautotests.pages.emuls;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Page
public class QMSPage extends BasePage<QMSPage> {
    @FindBy(xpath = "//tr/td[text()='7070']/../td/button[@ng-click='setNextTicket(ticket.windowId)']")
    private WebElement closeAndCallNextButton; //Кнопка "Закрыть и вызвать следующего" для окна 7070

    @FindBy(css="[ng-click='addingWindowFormShow = true;']")
    private WebElement addNewWindowButton; //Кнопка "Добавить новое окно"

    @FindBy(css = "[ng-model=\"windowId\"]")
    private WebElement windowIDField; //Поле для ввода номера окна

    @FindBy(css = "[ng-click='addNewWindow()']")
    private WebElement addWindowAndCallNextButton; //Кнопка "Добавить окно и вызвать следующего"



    public void createNewTicketForWindow(String windowNumber) {
        List<WebElement> windowsList = driverUtils.getDriver().findElements(By.xpath("//tr/td[1]"));
        boolean isWindowPresent = false;

        for (WebElement windowID : windowsList) {
            if (windowID.getText().equals(windowNumber)) {
                waitUntilClickable(closeAndCallNextButton).click();
                isWindowPresent = true;
                break;
            }
        }
        if (isWindowPresent == false) {
            waitUntilClickable(addNewWindowButton).click();
            waitUntilClickable(windowIDField).sendKeys(windowNumber);
            waitUntilClickable(addWindowAndCallNextButton).click();
        }
    }
}
