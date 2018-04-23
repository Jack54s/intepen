package com.jack.intepen.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by 11407 on 23/023.
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext == null){
           SpringBeanUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> bClazz){
        return getApplicationContext().getBean(bClazz);
    }

    public static <T> T getBean(String name, Class<T> bClass){
        return getApplicationContext().getBean(name, bClass);
    }
}
