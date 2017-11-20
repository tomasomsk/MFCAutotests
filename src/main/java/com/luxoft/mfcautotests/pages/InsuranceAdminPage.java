package com.luxoft.mfcautotests.pages;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.model.BoxWithInsuranceCompany;
import com.luxoft.mfcautotests.model.InsuranceCompany;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

@Page
public class InsuranceAdminPage extends BasePage<InsuranceAdminPage> {

    @FindBy(css = ".item.ng-binding.ng-scope.ui-sortable-handle")
    public List<WebElement> insuranceCompaniesList;

    public List<BoxWithInsuranceCompany> boxesListBefore;

//    public List<BoxWithInsuranceCompany> boxesListAfter;

    public List<BoxWithInsuranceCompany> expectedBoxesList;


    public Set<InsuranceCompany> getlInsuranceCompaniesFromUi() {
        Set<InsuranceCompany> actualInsuranceCompanies = new HashSet<>();

        for(WebElement temp : insuranceCompaniesList) {
            InsuranceCompany insuranceCompany = new InsuranceCompany();
            insuranceCompany.setName(temp.getText());
            actualInsuranceCompanies.add(insuranceCompany);
        }
        return actualInsuranceCompanies;
    }

    public List<BoxWithInsuranceCompany> getBoxesFromUi() {
        log.info("Getting current insurance companies from UI Arm Insurance admin");
        List<BoxWithInsuranceCompany> boxesList = new ArrayList<>();
        for(WebElement temp : insuranceCompaniesList) {
            BoxWithInsuranceCompany box = new BoxWithInsuranceCompany();
            box.setName(temp.getText());
            box.setLocation(temp.getLocation());
            boxesList.add(box);
        }
        boxesList.sort(Comparator.comparingInt(o -> o.getLocation().y));
        return boxesList;

    }

    public InsuranceAdminPage moveBoxes() {
        boxesListBefore = getBoxesFromUi();
        boxesListBefore.sort(Comparator.comparingInt(o -> o.getLocation().y));

        WebElement firstBoxUi = driverUtils.getDriver().findElement(By.xpath("//li[contains(text(),'" + boxesListBefore.get(0).getName() + "')]"));
        WebElement lastBoxUi = driverUtils.getDriver().findElement(By.xpath("//li[contains(text(),'" + boxesListBefore.get(boxesListBefore.size() - 1).getName() + "')]"));

        log.info("Moving boxes with insurance companies");
        Actions action = new Actions(driverUtils.getDriver());
        action.clickAndHold(lastBoxUi).moveToElement(firstBoxUi).moveByOffset(0, -10).release().build().perform();

        return this;
    }

    public List<BoxWithInsuranceCompany> createExpectedBoxesList() {
        log.info("Creating expected boxes list after moving boxes");
        expectedBoxesList = boxesListBefore;
        List<String> boxesNames = expectedBoxesList.stream().map(BoxWithInsuranceCompany::getName).collect(Collectors.toList());

        String tempName = boxesNames.get(boxesNames.size() - 1);
        for (int i = 1; i < expectedBoxesList.size(); i++) {
            expectedBoxesList.get(i).setName(boxesNames.get(i - 1));
        }
        expectedBoxesList.get(0).setName(tempName);

        return expectedBoxesList;
    }

//    public InsuranceAdminPage createActualBoxesListFromUiAfterMoving() {
//        log.info("Creating actual boxes list from Arm Insurance admin after moving boxes");
//        boxesListAfter = getBoxesFromUi();
//        boxesListAfter.sort(Comparator.comparingInt(o -> o.getLocation().y));
//
//        return this;
//    }

    public List<String> getNamesFromBoxesListAfter() {
        return getBoxesFromUi().stream().map(BoxWithInsuranceCompany::getName).collect(Collectors.toList());
    }
}
