package com.luxoft.mfcautotests.config.postProcessors;

import com.luxoft.mfcautotests.config.annotations.Page;
import com.luxoft.mfcautotests.displayedelementlocator.DisplayedElementLocatorFactory;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// Initialization FindBy-s elements of Pages

@Component
public class InitPagesPostProcessor implements BeanPostProcessor {

    @Autowired
    DriverUtils driverUtils;

    @Autowired
    ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class type = bean.getClass();
//        if (type.isAnnotationPresent(Page.class) && utils.getDriver() == null) {
//            WebDriver driver = (WebDriver)context.getBean("webDriver");
//            PageFactory.initElements(new DisplayedElementLocatorFactory(driver, 15), bean);
//        } else {
            if (type.isAnnotationPresent(Page.class)) {
                PageFactory.initElements(new DisplayedElementLocatorFactory(driverUtils.getDriver(), 15), bean);
            }
//        }
        return bean;
    }
}
