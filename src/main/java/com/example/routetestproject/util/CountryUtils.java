package com.example.routetestproject.util;

import com.example.routetestproject.response.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@UtilityClass
public class CountryUtils {

    private static final String COUNTRY_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    public static URL getCountryUrl() {
        try {
            return new URL(COUNTRY_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Url is not valid");
        }
    }

    public static List<Country> getCountriesWithBorders() {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(getCountryUrl(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot map countries");
        }
    }
}
