package com.clickBus.clickbuswebapp.Dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Data
@Component
@Document(collection = "place")
public class PlaceDao {

    @Id
    private  String id;

    @Indexed(unique=true)
    private String name;
    private String slug;
    private String city;
    private String state;
    private Boolean isCreated;
}
