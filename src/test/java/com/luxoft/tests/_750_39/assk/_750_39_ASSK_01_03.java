package com.luxoft.tests._750_39.assk;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.helpers.InsuranceAdminHelper;
import com.luxoft.mfcautotests.helpers.QMSHelper;
import com.luxoft.mfcautotests.helpers.SSHHelper;
import com.luxoft.mfcautotests.model.BoxWithInsuranceCompany;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.app.DmsPage;
import com.luxoft.mfcautotests.pages.InsuranceAdminPage;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.app.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class _750_39_ASSK_01_03 extends BaseTest {

    @Autowired
    TestEnvironment env;
    @Autowired
    User user;
    @Autowired
    InsuranceAdminHelper insuranceAdminHelper;
    @Autowired
    SSHHelper sshHelper;
    @Autowired
    QMSHelper qmsHelper;
    @Autowired
    InsuranceAdminPage insuranceAdminPage;
    @Autowired
    SearchPage searchPage;
    @Autowired
    LoginPage loginPage;
    @Autowired
    DmsPage dmsPage;

    @BeforeMethod
    public void setup() {
        if (! driverUtils.getDriver().getCurrentUrl().contains("mmcinsurance/insurance_admin")) {
            navHelper.openArmInsuranceAdmin()
                    .loginWithRole(Role.INSURANCE_ADMIN);
        }
    }

    @Test
    @Title("Перемещение бокса со страховой компанией")
    @Features("750-39 Функции администрирования страховых компаний")
    @Stories("АССК-01-03 Настройка списка страховых компаний")
    public void checkRelocatedBoxPositionTest() {
        insuranceAdminHelper
                .moveBoxFromLastToFirst();

        List<BoxWithInsuranceCompany>
                boxesAfterMovingPosition = insuranceAdminPage.getBoxesFromUi();
        List<BoxWithInsuranceCompany>
                boxesExpectedPosition = insuranceAdminPage.createExpectedBoxesList();

        // Checking that boxes in expected positions after moving downer box to upper position
        assertEquals(boxesAfterMovingPosition, boxesExpectedPosition);
    }

    @Test
    @Title("Сортировка компаний в АРМе Админа СК в соответствии с полем admin_ui_seq_no в БД")
    @Features("750-39 Функции администрирования страховых компаний")
    @Stories("АССК-01-03 Настройка списка страховых компаний")
    public void soringAtArmAdminUiTest() {
        insuranceAdminHelper.moveBoxFromLastToFirst();

        List<String>
                sortingBoxesUi = insuranceAdminPage.getNamesFromBoxesListAfter();
        List<String>
                sortingBoxesDbByAdminUiSeq = insuranceAdminHelper.getInsuranceCompaniesNamesFromDbSortByAdminUiSeq();

        // Checking that company names in Arm Insurance admin has sorted as company names in DB. Companies in DB have sorted by AdminUiSeq
        assertEquals(sortingBoxesUi, sortingBoxesDbByAdminUiSeq);
    }

    @Test
    @Title("Совпадение полей admin_ui_seq_no и ui_seq_no в БД")
    @Features("750-39 Функции администрирования страховых компаний")
    @Stories("АССК-01-03 Настройка списка страховых компаний")
    public void fieldsForSortingIsEqualsInDbTest() {
        insuranceAdminHelper.moveBoxFromLastToFirst();

        List<Integer>
                adminSequenceForSortingDb = insuranceAdminHelper.getAdminUiSeq();
        List<Integer>
                uiSequenceForSortingDb = insuranceAdminHelper.getUiSeq();

        // Checking that field "dms_admin_ui_seq_no" equals to field "ui_seq_no" in DB
        assertEquals(uiSequenceForSortingDb, adminSequenceForSortingDb);
    }

    @Test
    @Title("Сортировка СК в АРМе ООО после перемещения боксов")
    @Features("750-39 Функции администрирования страховых компаний")
    @Stories("АССК-01-03 Настройка списка страховых компаний")
    public void sortingInArmOooTest() {
        insuranceAdminHelper.
                moveBoxFromLastToFirst();

        List<BoxWithInsuranceCompany>
                companiesFromArmInsuranceAdmin = insuranceAdminPage.getBoxesFromUi();

        loginPage.logout();
        sshHelper.restartModuleMmc();
        qmsHelper.getTicketForWindowId(env.windowId);
        navHelper.openArmOoo()
                .loginWithRole(Role.OOO);
        searchPage.addApplication()
                .selectSection("dms_police");

        List<BoxWithInsuranceCompany>
                companiesFromArmOoo = dmsPage.getInsuranceCompaniesWhenExecutingInSystem();

        loginPage.logout();

        // Checking that companies in Arm OOO has sorted as companies in Arm Insurance Admin
        assertEquals(companiesFromArmOoo, companiesFromArmInsuranceAdmin);


    }


}
