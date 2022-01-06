package com.apress.prospring5.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

public class ShutDownHookBean implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof GenericApplicationContext) {
            ((GenericApplicationContext)applicationContext).registerShutdownHook();
        }
    }
}
