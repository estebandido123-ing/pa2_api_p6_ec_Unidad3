package ec.edu.uce.application.service;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.interceptor.InterceptorBinding;
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auditar {

}
