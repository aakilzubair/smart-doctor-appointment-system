package doctor_service.controller;

import doctor_service.entity.City;
import doctor_service.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public Long createCity(
            @RequestParam String name,
            @RequestParam Long stateId
    ) {
        return cityService.createCity(name, stateId);
    }

    @GetMapping
    public List<City> getCities(@RequestParam Long stateId) {
        return cityService.getCitiesByState(stateId);
    }
}
