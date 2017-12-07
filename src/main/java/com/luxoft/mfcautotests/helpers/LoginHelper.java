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
        String usrName = null;
        String password = env.passwordToArms;

        switch (role) {
            case OOO: usrName = env.oooLogin;
            break;
            case OBO: usrName = env.oboLogin;
            break;
            case OVP: usrName = env.ovpLogin;
            break;
            case INSURANCE_ADMIN: usrName = env.insuranceAdminLogin;
            break;
            case STATS_USER: usrName = env.statsUserLogin;
            break;
            case STATS_ADMIN: usrName = env.statsAdminLogin;
            break;
            case DASHBOARD_USER: usrName = env.dashboardUserLogin;
            break;
            case DASHBOARD_MANAGER: usrName = env.dashboardManagerLogin;
            break;
        }
        createUser(usrName, password, role);
        loginPage.login(user);
    }

    public void createUser(String login, String password, Role role) {
        user.setLogin(login)
                .setPassword(password)
                .setRoles(role);
    }

}
