package com.clickBus.clickbuswebapp.services;

import com.clickBus.clickbuswebapp.Dao.PlaceDao;
import com.clickBus.clickbuswebapp.Repository.BusRepository;
import com.clickBus.clickbuswebapp.model.FilterRequestModel;
import com.clickBus.clickbuswebapp.model.Place;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestBusService {
    BusService busService;

    Place place = new Place();

    @BeforeEach
    void setUp() {
        busService = new BusService();
        place.setName("abc");
        PlaceDao placeDao = new PlaceDao();
        placeDao.setName("abc");
    }

    @Test
    void createPlace() throws JSONException {
        JSONObject returnObject = new JSONObject();
        assertNotEquals(returnObject, busService.createPlace(place));
    }

    @Test
    void listPlaces() {
        FilterRequestModel request = new FilterRequestModel();
        request.setSort("asc");
        request.setPageNo(1);
        request.setPageSize(25);
        request.setIsFilter(false);
        Assertions.assertThrows(NullPointerException.class, () -> {
            busService.listPlaces(request);
        });
    }

    @Test
    void editPlace() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            busService.editPlace(place.getName(), place);
        });
    }

    @Test
    void getPlace() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            busService.getPlace(place.getName());
        });
    }

    @Test
    void listPlacesWithException() {
        FilterRequestModel request = new FilterRequestModel();
        Assertions.assertThrows(NullPointerException.class, () -> {
            busService.listPlaces(request);
        });
    }

}