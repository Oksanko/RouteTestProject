package com.example.routetestproject.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Country {
    private String cca3;
    private List<String> borders;

    @JsonIgnore
    private boolean visited = false;
}
