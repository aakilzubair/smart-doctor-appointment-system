package doctor_service.service;

public interface AppointmentService {

    void bookAppointment(Long userId, Long slotId);

}