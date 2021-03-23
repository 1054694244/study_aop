package com.shenzc.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author Shenzc
 */
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	String msg() default "this is myAnnotation"; //default 默认值
}