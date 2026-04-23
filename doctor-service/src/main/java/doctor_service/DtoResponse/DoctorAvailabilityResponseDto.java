package doctor_service.DtoResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DoctorAvailabilityResponseDto {

    private Long doctorId;
    private LocalDate date;
    private List<LocalTime> availableSlots;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LocalTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<LocalTime> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
