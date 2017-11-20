package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.model.InsuranceCompany;
import com.luxoft.mfcautotests.pages.InsuranceAdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Helper
public class InsuranceAdminHelper extends BaseHelper {

    @Autowired
    InsuranceAdminPage insuranceAdminPage;
    @Autowired
    DaoPostgres daoPostgres;

    public Set<InsuranceCompany> getExpectedInsuranceCompaniesFromJira() {
        Set<InsuranceCompany> expectedInsuranceCompanies = new HashSet<>();
        List<WebElement> insuranceCompaniesFromJira = driverUtils.getDriver().findElements(By.cssSelector(env.cssSelectorToInsuranceCompanies));

        for(WebElement temp : insuranceCompaniesFromJira) {
            InsuranceCompany insuranceCompany = new InsuranceCompany();
            insuranceCompany.setName(temp.getText());
            expectedInsuranceCompanies.add(insuranceCompany);
        }

        return expectedInsuranceCompanies;
    }

    public void moveBoxFromLastToFirst() {
        insuranceAdminPage.moveBoxes()
                .clickSaveButton()
                .clickCloseButtonOnModalWindow()
                .refreshPage();
    }

    public List<String> getInsuranceCompaniesNamesFromDbSortByAdminUiSeq() {
        List<InsuranceCompany> insuranceCompanies = daoPostgres.selectDmsCompaniesSortByAdminUiSeq();

        return insuranceCompanies.stream().map(InsuranceCompany::getName).collect(Collectors.toList());
    }

    public List<Integer> getAdminUiSeq() {
        List<InsuranceCompany> insuranceCompanies = daoPostgres.selectDmsCompaniesSortByAdminUiSeq();

        return insuranceCompanies.stream().map(InsuranceCompany::getAdminUiSeqNo).collect(Collectors.toList());
    }

    public List<Integer> getUiSeq() {
        List<InsuranceCompany> insuranceCompanies = daoPostgres.selectDmsCompaniesSortByAdminUiSeq();

        return insuranceCompanies.stream().map(InsuranceCompany::getUiSeqNo).collect(Collectors.toList());
    }
}
