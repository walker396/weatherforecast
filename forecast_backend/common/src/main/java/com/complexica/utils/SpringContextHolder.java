package com.complexica.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author
 * @date 2019-01-07
 */
@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * Get the ApplicationContext stored in a static variable.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * Obtain Bean from the static variable applicationContext and automatically transform it to the type of the assigned object.
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * Obtain Bean from the static variable applicationContext and automatically transform it into the type of the assigned object.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * Check that the ApplicationContext is not empty.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext attribute is not injected, please enter applicationContext" +
                    "Define SpringContextHolder in .xml or register SpringContextHolder in SpringBoot startup class.");
        }
    }

    /**
     * Clear the ApplicationContext in SpringContextHolder to Null.
     */
    public static void clearHolder() {
        log.debug("Clear ApplicationContext in SpringContextHolder:"
                + applicationContext);
        applicationContext = null;
    }

    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null) {
            log.warn("ApplicationContext in SpringContextHolder is overwritten, the original ApplicationContext is:" + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;
    }
}
