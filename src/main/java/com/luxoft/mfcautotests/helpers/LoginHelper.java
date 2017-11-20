package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

@Helper
public class LoginHelper extends BaseHelper {

    @Autowired
    User user;
    @Autowired
    LoginPage loginPage;

    public void loginWithRole(Role role) {
        String login = null;
        String password = env.passwordToArms;

        switch (role) {
            case OOO: login = env.oooLogin;
            break;
            case OBO: login = env.oboLogin;
            break;
            case OVP: login = env.ovpLogin;
            break;
            case INSURANCE_ADMIN: login = env.insuranceAdminLogin;
            break;
            case STATS_USER: login = env.statsUserLogin;
            break;
            case STATS_ADMIN: login = env.statsAdminLogin;
            break;
            case DASHBOARD_USER: login = env.dashboardUserLogin;
            break;
            case DASHBOARD_MANAGER: login = env.dashboardManagerLogin;
            break;
        }

        createUser(login, password, role);
        loginPage.login(user);
    }

    public void createUser(String login, String password, Role role) {
        user.setLogin(login)
                .setPassword(password)
                .setRoles(role);
    }

}
