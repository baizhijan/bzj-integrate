package com.bzj.spring.processor;

import java.lang.annotation.*;

/**
 * @author aaronbai
 * @create 2018-01-16 20:00
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConvertTag {
}
