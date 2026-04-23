package doctor_service.repository;

import doctor_service.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository  extends JpaRepository<Area,Long> {

    List<Area> findByCityId(Long cityId);
}
