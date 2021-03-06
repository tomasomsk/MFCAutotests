package com.luxoft.tests._750_39.assk;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.helpers.InsuranceAdminHelper;
import com.luxoft.mfcautotests.model.InsuranceCompany;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.InsuranceAdminPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import java.util.Set;
import static com.luxoft.mfcautotests.model.Role.*;
import static org.testng.Assert.assertEquals;

public class _750_39_ASSK_01_01 extends BaseTest {

    @Autowired
    User insuranceAdmin;
    @Autowired
    InsuranceAdminHelper insuranceAdminHelper;
    @Autowired
    InsuranceAdminPage insuranceAdminPage;

    @BeforeClass
    public void setup() {
        insuranceAdmin.setRoles(INSURANCE_ADMIN)
                .setLogin(env.insuranceAdminLogin)
                .setPassword(env.passwordToArms);
    }

    @Test(enabled = false)
    @Title("Проверка списка СК на соответствие требованиям")
    @Features("750-39 Функции администрирования страховых компаний")
    @Stories("АССК-01-01 Вход в арм администратора страховых компаний")
    public void checkInsuranceCompaniesTest() {
        navHelper.openInsuranceCompaniesCatalog();
        Set<InsuranceCompany> expectedInsuranceCompanies = insuranceAdminHelper.getExpectedInsuranceCompaniesFromJira();

        navHelper.openArmInsuranceAdmin()
                .loginWithRole(INSURANCE_ADMIN);
        Set<InsuranceCompany> actualInsuranceCompanies = insuranceAdminPage.getlInsuranceCompaniesFromUi();

        assertEquals(actualInsuranceCompanies, expectedInsuranceCompanies);
    }

}
