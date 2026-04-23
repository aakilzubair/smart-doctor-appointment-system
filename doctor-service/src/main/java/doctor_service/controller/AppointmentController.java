package doctor_service.controller;

import doctor_service.common.ApiResponse;
import doctor_service.service.AppointmentService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // 🔥 BOOK APPOINTMENT
    @PostMapping("/book")
    public ResponseEntity<ApiResponse<String>> bookAppointment(
            @RequestParam @NotNull @Min(1) Long userId,
            @RequestParam @NotNull @Min(1) Long slotId
    ) {

        appointmentService.bookAppointment(userId, slotId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Appointment booked successfully", null)
        );
    }
}