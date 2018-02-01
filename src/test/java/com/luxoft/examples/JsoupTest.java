package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.forhelpers.HttpResponseData;
import com.luxoft.mfcautotests.helpers.HttpHelper;
import com.luxoft.mfcautotests.config.forhelpers.LoginParameters;
import com.luxoft.mfcautotests.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
;

public class JsoupTest extends BaseTest {

    @Autowired
    HttpHelper httpHelper;

    @Test
    public void testJsoup() {
        User user = new User()
                .setLogin(env.oooLogin)
                .setPassword(env.passwordToArms);

        LoginParameters loginParameters =
                httpHelper.getOooLoginParameters("https://lc-mfc-tst2.luxoft.com/mmcreg/search.htm");
        loginParameters.setUserName(user.getLogin())
                .setPassword(user.getPassword());
        HttpResponseData responseData = httpHelper.login(loginParameters);
        System.out.println(responseData.getResponseData().toString());





//        Response response = httpHelper.goToSearch();
//        Document document1 = httpHelper.login(user);
//        System.out.println(document1.toString());
    }
}
