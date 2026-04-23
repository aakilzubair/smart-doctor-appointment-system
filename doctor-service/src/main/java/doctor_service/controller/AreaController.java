package doctor_service.controller;

import doctor_service.entity.Area;
import doctor_service.service.AreaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public Long createArea(
            @RequestParam String name,
            @RequestParam Long cityId
    ) {
        return areaService.createArea(name, cityId);
    }

    @GetMapping
    public List<Area> getAreas(@RequestParam Long cityId) {
        return areaService.getAreasByCity(cityId);
    }
}
