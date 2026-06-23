package com.example.ddd.helper;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.cm.domain.Customer;

@Documented
@Retention(CLASS)
@Target(TYPE)
public @interface Identity {

	Class<?> entity();

}
