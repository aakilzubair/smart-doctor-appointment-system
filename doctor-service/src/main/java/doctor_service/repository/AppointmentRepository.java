package doctor_service.repository;

import doctor_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByTimeSlotId(Long timeSlotId);

    boolean existsByUserIdAndTimeSlotId(Long userId, Long timeSlotId);
}
