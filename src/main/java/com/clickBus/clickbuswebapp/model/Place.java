package com.clickBus.clickbuswebapp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Place {
    private String name;
    private String slug;
    private String city;
    private String state;
//    private Boolean isCreated;
}
