package com.luxoft.tests._750_24.sau;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.model.Role;
import com.luxoft.mfcautotests.model.User;
import com.luxoft.mfcautotests.pages.LoginPage;
import com.luxoft.mfcautotests.pages.StatsAdminPage;
import com.luxoft.mfcautotests.pages.StatsUserPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.AssertJUnit.assertTrue;

public class _750_24_SAU_01 extends BaseTest {

    @Autowired
    User user;
    @Autowired
    StatsAdminPage statsAdminPage;
    @Autowired
    StatsUserPage statsUserPage;
    @Autowired
    LoginPage loginPage;

    @AfterMethod
    public void logout() {
        loginPage.logout();
    }

    @Test
    @Title("Авторизация в АРМе Админа статистики с ролью Админ статистики")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-01 Авторизация")
    public void authorizationWithRoleStatsAdmin() {
        navHelper.openArmStatsAdmin()
                .loginWithRole(Role.STATS_ADMIN);

        assertTrue(statsAdminPage.isElementsInConditionForStatsAdmin());
    }

    @Test
    @Title("Авторизация в АРМе Пользователя статистики с ролью Пользователь статистики")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-01 Авторизация")
    public void authorizationWithRoleStatsUser() {
        navHelper.openArmStatsUser()
                .loginWithRole(Role.STATS_USER);

        assertTrue(statsUserPage.isElementsInConditionForStatsUser());
    }

    @Test
    @Title("Авторизация в АРМе Пользователя статистики с ролью Менеджер дэшборда")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-01 Авторизация")
    public void authorizationWithRoleDashboarManager() {
        navHelper.openArmStatsUser()
                .loginWithRole(Role.DASHBOARD_MANAGER);

        assertTrue(statsUserPage.isElementsInConditionForDashboardManager());
    }

    @Test
    @Title("Авторизация в АРМе Пользователя статистики с ролью Пользователь дэшборда")
    @Features("750-24 Требования к ручному вводу и просмотру данных отчетности")
    @Stories("MMC-SAU-01 Авторизация")
    public void authorizationWithRoleDashboarUser() {
        navHelper.openArmStatsUser()
                .loginWithRole(Role.DASHBOARD_USER);

        assertTrue(statsUserPage.isElementsInConditionForDashboardUser());
    }

}
