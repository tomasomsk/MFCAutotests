package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.helpers.NavHelper;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.app.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Test1 extends BaseTest {

    @Autowired
    User user;
    @Autowired
    TestEnvironment env;
    @Autowired
    NavHelper navHelper;
    @Autowired
    LoginPage loginPage;
    @Autowired
    SearchPage searchPage;

    @Test
    public void test1() {
        user.setLogin(env.oooLogin)
                .setPassword(env.passwordToArms)
                .setRoles(Role.OOO);

        navHelper.openArmOoo()
                .loginWithRole(Role.OOO);

    }
}
