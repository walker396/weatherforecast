package com.complexica.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Li He
 * @date 2019-6-4
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /** The attribute name of the base object */
    String propName() default "";
    /** Query function */
    Type type() default Type.EQUAL;

    /**
     * Connect the query attribution name, such as dept in the User class
     * @return
     */
    String joinName() default "";

    /**
     * Default left connection
     * @return
     */
    Join join() default Join.LEFT;

    enum Type {
        /** Equal */
        EQUAL
        /** Greater or equal */
        , GREATER_THAN
        /** Less or equal */
        , LESS_THAN
        /** Inner fuzzy query */
        , INNER_LIKE
        /** Left fuzzy query */
        , LEFT_LIKE
        /** Right fuzzy query */
        , RIGHT_LIKE
        /** Less than */
        , LESS_THAN_NQ
        //** Include */
        , IN
    }

    /**
     * @author Li He
     * Suitable for simple connection query,
     * please customize the annotation for complex,
     * or use sql query
     */
    enum Join {
        /** jie 2019-6-4 13:18:30 左连接 */
        LEFT
        /** jie 2019-6-4 13:18:30 右连接 */
        , RIGHT
    }

}

