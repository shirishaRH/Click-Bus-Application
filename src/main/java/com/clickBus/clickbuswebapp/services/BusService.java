package com.clickBus.clickbuswebapp.services;

import com.clickBus.clickbuswebapp.Dao.PlaceDao;
import com.clickBus.clickbuswebapp.Repository.BusRepository;
import com.clickBus.clickbuswebapp.model.FilterRequestModel;
import com.clickBus.clickbuswebapp.model.Place;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.google.api.client.util.Lists;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    @Autowired
    BusRepository busRepository;

    public JSONObject createPlace(Place place) {
        JSONObject returnObject = new JSONObject();
        PlaceDao dao = new PlaceDao();
        try {
            dao.setName(place.getName());
            dao.setSlug(place.getSlug());
            dao.setCity(place.getCity());
            dao.setState(place.getState());
            dao.setIsCreated(true);
            busRepository.save(dao);
            returnObject.put("Message", "Place Created Successfully!");
        } catch (DuplicateKeyException e) {
            returnObject.put("Message", "Place is already present with the name - " + place.getName());
            returnObject.put("Exception", e);
        } catch (Exception e) {
            returnObject.put("Message", "Failed to Create Place " + e);
        }
        return returnObject;
    }

    public JSONObject editPlace(String name, Place place) {
        JSONObject returnObject = new JSONObject();
        PlaceDao dao = busRepository.findByName(name);
        try {
            if (dao != null) {
                dao.setName(place.getName());
                dao.setSlug(place.getSlug());
                dao.setCity(place.getCity());
                dao.setState(place.getState());
                busRepository.save(dao);
                returnObject.put("Message", "Place Edited Successfully!");
            } else {
                returnObject.put("Message", "Place Not Found with the name - " + name);
            }
        } catch (DuplicateKeyException e) {
            returnObject.put("Message", "Place is already present with the name - " + place.getName());
            returnObject.put("Exception", e);
        }
        return returnObject;
    }

    public JSONObject getPlace(String name) {
        JSONObject returnObject = new JSONObject();
        returnObject.put("data", new JSONObject(busRepository.findByName(name)));
        return returnObject;
    }

    public JSONObject listPlaces(FilterRequestModel filterRequest) {
        JSONObject returnObject = new JSONObject();
        List<Place> listOfPlaces = new ArrayList<>();
        List<PlaceDao> places = getListsFromDB(filterRequest);
        for (PlaceDao eachPlace : places) {
            Place place = new Place();
            place.setName(eachPlace.getName());
            place.setSlug(eachPlace.getSlug());
            place.setCity(eachPlace.getCity());
            place.setState(eachPlace.getState());
            listOfPlaces.add(place);
        }
        returnObject.put("data", listOfPlaces);
        returnObject.put("totalSize", busRepository.findAll().size());
        if (filterRequest.getIsFilter()) {
            returnObject.put("filteredSize", listOfPlaces.size());
        }
        return returnObject;
    }

    public List<PlaceDao> getListsFromDB(FilterRequestModel filterRequest) {
        Sort sort;
        if (filterRequest.getSort().equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Direction.ASC, "name");
        } else {
            sort = Sort.by(Sort.Direction.DESC, "name");
        }
        Pageable pageable = PageRequest.of(filterRequest.getPageNo() == 0 ? filterRequest.getPageNo() : filterRequest.getPageNo() - 1, filterRequest.getPageSize(), sort);

        if (filterRequest.getIsFilter()) {
            List<PlaceDao> filteredPlaces = new ArrayList<>();
            for (String eachValue : filterRequest.getFilterValue()) {
                PlaceDao place = busRepository.findByName(eachValue);
                filteredPlaces.add(place);
            }
            return filteredPlaces;
        } else {
            Page<PlaceDao> placeList = busRepository.findAll(pageable);
            return Lists.newArrayList(placeList);
        }
    }
}
