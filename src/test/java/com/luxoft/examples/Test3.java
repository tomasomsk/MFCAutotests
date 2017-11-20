package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.helpers.NavHelper;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Test3 extends BaseTest {

    @Autowired
    NavHelper navHelper;
    @Autowired
    TestEnvironment env;
    @Autowired
    User ooo;
    @Autowired
    LoginPage loginPage;

    @Test
    public void test3() {
        ooo.setLogin(env.oooLogin)
                .setPassword(env.passwordToArms);

        navHelper.openArmOoo();
        assertEquals(loginPage.getTitle(), "Система обеспечения информационной безопасности");
    }

}