package doctor_service.repository;

import doctor_service.entity.DoctorAppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentScheduleRepository extends JpaRepository<DoctorAppointmentSchedule,Long> {
}
