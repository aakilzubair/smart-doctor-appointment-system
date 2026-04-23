package doctor_service.DtoRequest;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddDoctorAvailabilityRequestDto {
    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date cannot be in past")
    private LocalDate date;


    @NotEmpty(message = "Time slots are required")
    private List<@NotNull(message = "Time slot cannot be null") LocalTime> timeSlots;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LocalTime> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<LocalTime> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
