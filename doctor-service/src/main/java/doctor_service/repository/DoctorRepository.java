package doctor_service.repository;

import doctor_service.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository  extends JpaRepository<Doctor,Long> {
    @Query("""
SELECT d FROM Doctor d
WHERE (:cityId IS NULL OR d.city.id = :cityId)
AND (:areaId IS NULL OR d.area.id = :areaId)
AND (:specialization IS NULL OR :specialization = '' 
     OR LOWER(d.specialization) LIKE LOWER(CONCAT('%', :specialization, '%')))
""")
    Page<Doctor> searchDoctors(
            Long cityId,
            Long areaId,
            String specialization,
            Pageable pageable
    );

}
