package com.luxoft.mfcautotests.config.postProcessors;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import net.vidageek.mirror.dsl.Mirror;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class LoggerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Field> fields = new Mirror().on(bean.getClass()).reflectAll().fields();
        for (Field field : fields) {
            if (Logger.class.isAssignableFrom(field.getType()) && new Mirror().on(field).reflect().annotation(InjectLogger.class) != null) {
                new Mirror().on(bean).set().field(field).withValue(Logger.getLogger(bean.getClass()));
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
