package com.example.routetestproject.validation;

import com.example.routetestproject.validation.impl.ValidISOCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target(value = {FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidISOCodeValidator.class)
public @interface ValidIsoCode {
    String message() default "Wrong country code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
