package com.shenzc.aop.aspectj;

import java.lang.annotation.*;

/**
 * 操作日志切面
 * @author: shenzc
 **/

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    String value() default "";
    String args() default "";
}