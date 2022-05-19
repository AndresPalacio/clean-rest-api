package com.example.service.user.infrastructure.helpers.jdbc.sqlstatement;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class DataAccessAnnotationProcessor implements BeanPostProcessor {

    /**
     * Process Before
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        this.configureFieldInjection(bean);
        return bean;
    }

    /**
     * Process After
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * @param bean
     */
    private void configureFieldInjection(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        ReflectionUtils.FieldCallback fieldCallback = new DataAccessFieldCallback(bean);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
    }
}
