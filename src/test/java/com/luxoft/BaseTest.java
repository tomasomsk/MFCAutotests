package com.luxoft;

import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.config.SpringConfig;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.helpers.NavHelper;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import java.lang.reflect.Method;

@ContextConfiguration(classes = {SpringConfig.class})
//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class BaseTest extends AbstractTestNGSpringContextTests implements IHookable {

    @Autowired
    @Lazy
    public DriverUtils driverUtils;
    @Autowired
    public TestEnvironment env;
    @Autowired
    @Lazy
    public NavHelper navHelper;

    @AfterClass
    public void tearDown() {
        driverUtils.killDriver();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        try {
            //If method doesn't use webDriver (annotation @NonDriver) then screenshot will not be made
            Method method = testResult.getTestClass().getRealClass().getMethod(testResult.getName());
            if (testResult.getThrowable() != null && !method.isAnnotationPresent(NonDriver.class)) {
                driverUtils.makeScreenshot(testResult.getMethod().getMethodName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

