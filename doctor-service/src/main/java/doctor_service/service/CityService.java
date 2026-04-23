package doctor_service.service;

import doctor_service.entity.City;

import java.util.List;

public interface CityService {
    Long createCity(String name, Long stateId);
    List<City> getCitiesByState(Long stateId);
}