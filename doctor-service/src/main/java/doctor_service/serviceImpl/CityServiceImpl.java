package doctor_service.serviceImpl;

import doctor_service.entity.City;
import doctor_service.entity.State;
import doctor_service.repository.CityRepository;
import doctor_service.repository.StateRepository;
import doctor_service.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityServiceImpl(CityRepository cityRepository,
                           StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public Long createCity(String name, Long stateId) {

        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new RuntimeException("State not found"));

        City city = new City();
        city.setName(name);
        city.setState(state);

        return cityRepository.save(city).getId();
    }

    @Override
    public List<City> getCitiesByState(Long stateId) {
        return cityRepository.findByStateId(stateId);
    }
}
