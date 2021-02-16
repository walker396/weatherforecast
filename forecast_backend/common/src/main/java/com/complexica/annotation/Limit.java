package com.complexica.annotation;



import com.complexica.aspect.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Li He
 * @date 2019-6-4 13:52:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // Resource name, used to describe the interface function
    String name() default "";

    // resource key
    String key() default "";

    // key prefix
    String prefix() default "";

    // time, in seconds
    int period();

    // Limit the number of visits
    int count();

    // restriction type
    LimitType limitType() default LimitType.CUSTOMER;

}
