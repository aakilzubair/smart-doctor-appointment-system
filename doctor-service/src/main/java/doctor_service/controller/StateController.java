package doctor_service.controller;

import doctor_service.entity.State;
import doctor_service.service.StateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public Long createState(@RequestParam String name) {
        return stateService.createState(name);
    }

    @GetMapping
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }
}