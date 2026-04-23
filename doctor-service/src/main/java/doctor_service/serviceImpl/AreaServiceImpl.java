package doctor_service.serviceImpl;

import doctor_service.entity.Area;
import doctor_service.entity.City;
import doctor_service.repository.AreaRepository;
import doctor_service.repository.CityRepository;
import doctor_service.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final CityRepository cityRepository;

    public AreaServiceImpl(AreaRepository areaRepository,
                           CityRepository cityRepository) {
        this.areaRepository = areaRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Long createArea(String name, Long cityId) {

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));

        Area area = new Area();
        area.setName(name);
        area.setCity(city);

        return areaRepository.save(area).getId();
    }

    @Override
    public List<Area> getAreasByCity(Long cityId) {
        return areaRepository.findByCityId(cityId);
    }
}