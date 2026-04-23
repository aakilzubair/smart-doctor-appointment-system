package doctor_service.repository;

import doctor_service.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository    extends  JpaRepository<State,Long> {
}
