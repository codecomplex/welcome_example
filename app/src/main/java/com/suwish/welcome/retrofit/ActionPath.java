package com.suwish.welcome.retrofit;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author by min.su on 2016/10/5.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ActionPath {

    String value() default "";
}
