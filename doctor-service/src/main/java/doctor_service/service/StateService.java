package doctor_service.service;

import doctor_service.entity.State;

import java.util.List;

public interface StateService {
    Long createState(String name);
    List<State> getAllStates();
}
