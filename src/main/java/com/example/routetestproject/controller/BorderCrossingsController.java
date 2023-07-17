package com.example.routetestproject.controller;

import com.example.routetestproject.response.BorderCrossingResponse;
import com.example.routetestproject.service.BorderCrossingsService;
import com.example.routetestproject.validation.ValidIsoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "routing", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class BorderCrossingsController {

    private final BorderCrossingsService borderCrossingsService;

    @GetMapping(value = "/{origin}/{destination}")
    public BorderCrossingResponse getBorderCrossings(@ValidIsoCode @PathVariable String origin,
                                                     @ValidIsoCode @PathVariable String destination) {
        BorderCrossingResponse borderCrossings = borderCrossingsService.getBorderCrossings(origin, destination);
        if (borderCrossings.getRoute().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return borderCrossings;
    }
}
