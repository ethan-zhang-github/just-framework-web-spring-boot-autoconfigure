package pers.just1984.framework.web.autoconfigure.annotation;

import pers.just1984.framework.web.autoconfigure.component.ResponseBodyWrapper;

import java.lang.annotation.*;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBodyWrap {

    Class<? extends ResponseBodyWrapper> value() default ResponseBodyWrapper.DefaultResponseBodyWrapper.class;

}
