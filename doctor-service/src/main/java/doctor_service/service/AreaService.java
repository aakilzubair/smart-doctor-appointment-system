package doctor_service.service;

import doctor_service.entity.Area;

import java.util.List;

public interface AreaService {
    Long createArea(String name, Long cityId);
    List<Area> getAreasByCity(Long cityId);
}