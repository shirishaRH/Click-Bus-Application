package com.clickBus.clickbuswebapp.model;

import lombok.Data;

import java.util.List;

@Data
public class FilterRequestModel {
    private int pageNo;
    private int pageSize;
    private String sort;
    private Boolean isFilter;
    private List<String> filterValue;
}
