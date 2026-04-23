package doctor_service.serviceImpl;

import doctor_service.entity.Appointment;
import doctor_service.entity.TimeSlots;
import doctor_service.repository.AppointmentRepository;
import doctor_service.repository.TimeSlotsRepository;
import doctor_service.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final TimeSlotsRepository timeSlotsRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  TimeSlotsRepository timeSlotsRepository) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotsRepository = timeSlotsRepository;
    }

    @Override
    @Transactional
    public void bookAppointment(Long userId, Long slotId) {

        TimeSlots slot = timeSlotsRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        //  double booking check
        if (slot.isBooked() || appointmentRepository.existsByTimeSlotId(slotId)) {
            throw new IllegalStateException("Slot already booked");
        }

        //  same user duplicate booking
        if (appointmentRepository.existsByUserIdAndTimeSlotId(userId, slotId)) {
            throw new IllegalStateException("You already booked this slot");
        }

        //  past slot check
        if (slot.getDoctorAppointmentSchedule().getDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Cannot book past slot");
        }

        // mark slot booked
        slot.setBooked(true);

        // create appointment
        Appointment appointment = new Appointment();
        appointment.setUserId(userId);
        appointment.setDoctor(slot.getDoctorAppointmentSchedule().getDoctor());
        appointment.setTimeSlot(slot);
        appointment.setStatus("BOOKED");

        appointmentRepository.save(appointment);
    }
}