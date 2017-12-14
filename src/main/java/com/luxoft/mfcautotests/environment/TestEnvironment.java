package com.luxoft.mfcautotests.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Component
@PropertySource({"classpath:environment.properties"})
@Scope(SCOPE_SINGLETON)
public class TestEnvironment {

    @Value("${resourcesPath}")
    public String resourcesPath;

    //URLs
    @Value("${baseUrl}")
    public String baseUrl;
    @Value("${baseUrlWs}")
    public String baseUrlWs;
    @Value("${mmcRegUrl}")
    public String mmcRegUrl;
    @Value("${qmsUrl}")
    public String qmsUrl;
    @Value("${pinUrl}")
    public String pinUrl;
    @Value("${bsoUrl}")
    public String bsoUrl;
    @Value("${statsAdminUrl}")
    public String statsAdminUrl;
    @Value("${statsUserUrl}")
    public String statsUserUrl;
    @Value("${adminUrl}")
    public String adminUrl;
    @Value("${insuranceAdminUrl}")
    public String insuranceAdminUrl;
    @Value("${gisEmpUrl}")
    public String gisEmpUrl;
    @Value("${checkDmsEmulURL}")
    public String checkDmsEmulURL;
    @Value("${epshEmulURL}")
    public String epshEmulURL;
    @Value("${asaoEmulUrl}")
    public String asaoEmulUrl;
    @Value("${rnipEmulUrl}")
    public String rnipEmulUrl;
    @Value("${ossEmulUrl}")
    public String ossEmulUrl;
    @Value("${dashboardUrl}")
    public String dashboardUrl;


    //JIRA Page With Insurance Companies Information
    @Value("${insuranceCompaniesCatalogUrl}")
    public String insuranceCompaniesCatalogUrl;
    @Value("${cssSelectorToInsuranceCompanies}")
    public String cssSelectorToInsuranceCompanies;

    //Passwords
    @Value("${passwordToArms}")
    public String passwordToArms;

    //WindowId
    @Value("${windowId}")
    public String windowId;


    //Logins
    @Value("${oooLogin}")
    public String oooLogin;
    @Value("${oboLogin}")
    public String oboLogin;
    @Value("${ovpLogin}")
    public String ovpLogin;
    @Value("${pinLogin}")
    public String pinLogin;
    @Value("${nbsoLogin}")
    public String nbsoLogin;
    @Value("${gbsoLogin}")
    public String gbsoLogin;
    @Value("${zbsoLogin}")
    public String zbsoLogin;
    @Value("${statsAdminLogin}")
    public String statsAdminLogin;
    @Value("${statsUserLogin}")
    public String statsUserLogin;
    @Value("${dashboardManagerLogin}")
    public String dashboardManagerLogin;
    @Value("${dashboardUserLogin}")
    public String dashboardUserLogin;
    @Value("${smsAdminLogin}")
    public String smsAdminLogin;
    @Value("${adminLogin}")
    public String adminLogin;
    @Value("${insuranceAdminLogin}")
    public String insuranceAdminLogin;

    //Postgre driver
    @Value("${dbPostgreSqlDriverRegistration}")
    public String dbPostgreSqlDriverRegistration;
    @Value("${dbPostgreSqlDriver}")
    public String dbPostgreSqlDriver;

    //WS Database environment
    @Value("${dbWsUrl}")
    public String dbWsUrl;
    @Value("${dbWsName}")
    public String dbWsName;
    @Value("${dbWsPort}")
    public String dbWsPort;
    @Value("${dbWsUserName}")
    public String dbWsUserName;
    @Value("${dbWsPassword}")
    public String dbWsPassword;

    // Database environment
    @Value("${dbUrl}")
    public String dbUrl;
    @Value("${dbName}")
    public String dbName;
    @Value("${dbPort}")
    public String dbPort;
    @Value("${dbUserName}")
    public String dbUserName;
    @Value("${dbPassword}")
    public String dbPassword;

    //SSH environment
    @Value("${shellScriptsPath}")
    public String shellScriptsPath;
    @Value("${mmcRegServerUrl_1}")
    public String mmcRegServerUrl_1;
    @Value("${mmcRegServerUrl_2}")
    public String mmcRegServerUrl_2;
    @Value("${sshUserName}")
    public String sshUserName;
    @Value("${sshPassword}")
    public String sshPassword;
    @Value("${sshPort}")
    public int sshPort;
}