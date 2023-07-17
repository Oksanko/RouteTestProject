package com.example.routetestproject.validation.impl;

import com.example.routetestproject.validation.ValidIsoCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;

import static java.util.Locale.IsoCountryCode.PART1_ALPHA3;

public class ValidISOCodeValidator implements ConstraintValidator<ValidIsoCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Locale.getISOCountries(PART1_ALPHA3).contains(value);
    }
}