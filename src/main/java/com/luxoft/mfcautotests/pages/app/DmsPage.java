package com.luxoft.mfcautotests.pages.app;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.model.BoxWithInsuranceCompany;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Page
public class DmsPage extends ApplicationPage {

    @FindBy(css = "#selectedPolicyId b")
    public WebElement dmsType;

    @FindBy(css = "#selectedPolicyId input")
    public WebElement dmsTypeInput;

    @FindBy(name = "dmsIssuer")
    public WebElement dmsCompaniesDropDown;

    @FindBy(css = "#ui-select-choices-11 .ng-binding.ng-scope")
    public List<WebElement> companiesWhenExecutingInSystem;


    public List<BoxWithInsuranceCompany> getInsuranceCompaniesWhenExecutingInSystem() {
        log.info("Getting insurance companies when executing in system from Arm OOO ");
        setCustomListValue(dmsType, dmsTypeInput, "Оформление полиса ДМС в системе");
        dmsCompaniesDropDown.click();
        List<BoxWithInsuranceCompany> companies = new ArrayList<>();
        for(WebElement temp : companiesWhenExecutingInSystem) {
            BoxWithInsuranceCompany company = new BoxWithInsuranceCompany();
            company.setName(temp.getText())
                    .setLocation(temp.getLocation());
            companies.add(company);
        }
        companies.sort(Comparator.comparingInt(o -> o.getLocation().y));

        return companies;
    }
}
