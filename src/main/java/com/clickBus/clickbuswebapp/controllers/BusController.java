package com.clickBus.clickbuswebapp.controllers;

import com.clickBus.clickbuswebapp.Dao.PlaceDao;
import com.clickBus.clickbuswebapp.model.FilterRequestModel;
import com.clickBus.clickbuswebapp.model.Place;
import com.clickBus.clickbuswebapp.services.BusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusController {

    @Autowired
    BusService busService;

    @CrossOrigin(origins = "*")
    @PostMapping("/createPlace")
    public ResponseEntity<String> createPlace(@RequestBody Place place) {
        return new ResponseEntity(busService.createPlace(place).toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/editPlace")
    public ResponseEntity<String> editPlace(@RequestParam String name,
                                            @RequestBody Place place) {
        return new ResponseEntity(busService.editPlace(name, place).toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getPlace")
    public ResponseEntity<PlaceDao> getPlace(@RequestParam String name) {
        return new ResponseEntity(busService.getPlace(name).toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/listPlaces")
    public ResponseEntity<Place> listPlaces(@RequestBody FilterRequestModel filterRequest) {
        JSONObject returnObject = busService.listPlaces(filterRequest);
        return new ResponseEntity(returnObject.toString(), HttpStatus.OK);
    }

}
