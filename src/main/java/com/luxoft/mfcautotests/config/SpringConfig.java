package com.luxoft.mfcautotests.config;

import com.luxoft.mfcautotests.drivers.Driver;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

import java.util.concurrent.TimeUnit;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Configuration
@PropertySource({"classpath:environment.properties"})
@ComponentScan(basePackages = {"com.luxoft.mfcautotests"})
public class SpringConfig {

    @Value("${driversPath}")
    private String driversPath;

    @Value("${driverName}")
    private String driverName;

    @Autowired
    ApplicationContext context;

    @PostConstruct
    public void prepare() {
        registerDrivers();
    }

    @Bean
    @Lazy
    @Scope(SCOPE_PROTOTYPE)
    public WebDriver webDriver() {
        try {
            WebDriver driver = (WebDriver) Driver.valueOf(driverName.toUpperCase()).getClazz().newInstance();
            driver.manage().window().maximize();
            return driver;
        } catch (IllegalArgumentException iae) {
            throw new RuntimeException("NO BROWSER PROFILE IS CHOSEN");
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void registerDrivers() {
        for (Driver driver : Driver.values()) {
            System.setProperty(driver.getPropertyName(), driversPath + driver.getExecName());
        }
    }

}
