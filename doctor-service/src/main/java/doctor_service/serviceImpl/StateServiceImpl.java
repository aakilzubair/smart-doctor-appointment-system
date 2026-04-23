package doctor_service.serviceImpl;

import doctor_service.entity.State;
import doctor_service.repository.StateRepository;
import doctor_service.service.StateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Long createState(String name) {
        State state = new State();
        state.setName(name);
        return stateRepository.save(state).getId();
    }

    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }
}