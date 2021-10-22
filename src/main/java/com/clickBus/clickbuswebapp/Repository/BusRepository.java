package com.clickBus.clickbuswebapp.Repository;

import com.clickBus.clickbuswebapp.Dao.PlaceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends MongoRepository<PlaceDao, String> {
    PlaceDao findByName(String name);
}
