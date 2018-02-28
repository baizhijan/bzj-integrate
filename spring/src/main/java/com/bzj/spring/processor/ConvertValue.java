package com.bzj.spring.processor;

import java.lang.annotation.*;

/**
 * 参数转换注解
 *
 * @author aaronbai
 * @create 2018-01-16 15:00
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertValue {
    String value() default "";
}
