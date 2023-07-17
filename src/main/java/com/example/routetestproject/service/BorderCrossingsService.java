package com.example.routetestproject.service;

import com.example.routetestproject.response.BorderCrossingResponse;
import com.example.routetestproject.response.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.routetestproject.util.CountryUtils.getCountriesWithBorders;

@Service
public class BorderCrossingsService {

    public BorderCrossingResponse getBorderCrossings(String origin, String destination) {
        Map<String, Country> countryMap = getCountriesWithBorders().stream()
                .collect(Collectors.toMap(Country::getCca3, Function.identity()));

        Country originCountry = countryMap.get(origin);
        originCountry.setVisited(true);

        Country destinationCountry = countryMap.get(destination);

        List<String> route = new ArrayList<>();
        route.add(originCountry.getCca3());
        List<List<String>> routes = new ArrayList<>();
        routes.add(route);

        return getBorderCrossingResponse(getWay(routes, destinationCountry, countryMap));
    }

    private List<String> getWay(List<List<String>> routes, Country destinationCountry, Map<String, Country> countryMap) {
        if (routes.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<String>> newRoutes = new ArrayList<>();

        for (List<String> route : routes) {
            if (route.get(route.size() - 1).equals(destinationCountry.getCca3())) {
                return route;
            }

            List<String> borders = countryMap.get(route.get(route.size() - 1)).getBorders().stream()
                    .filter(border -> !countryMap.get(border).isVisited())
                    .toList();

            for (String border : borders) {
                countryMap.get(border).setVisited(true);
                List<String> newRoute = new ArrayList<>(route);
                newRoute.add(border);
                newRoutes.add(newRoute);
            }
        }

        return getWay(newRoutes, destinationCountry, countryMap);
    }

    private BorderCrossingResponse getBorderCrossingResponse(List<String> shortestWay) {
        BorderCrossingResponse borderCrossingResponse = new BorderCrossingResponse();
        borderCrossingResponse.setRoute(shortestWay);
        return borderCrossingResponse;
    }
}
