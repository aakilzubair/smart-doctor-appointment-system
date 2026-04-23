package doctor_service.controller;

import doctor_service.DtoRequest.CreateDoctorRequestDto;
import doctor_service.DtoRequest.UpdateDoctorRequestDto;
import doctor_service.DtoResponse.DoctorProfileResponseDto;
import doctor_service.common.ApiResponse;
import doctor_service.service.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @PostMapping

    public ResponseEntity<ApiResponse<Long>> createDoctor(
            @Valid @RequestBody CreateDoctorRequestDto dto
    ) {
        Long doctorId = doctorService.createDoctor(dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctor created successfully", doctorId)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorProfileResponseDto>> updateDoctor(
            @PathVariable @NotNull @Min(1) Long id,
            @Valid @RequestBody UpdateDoctorRequestDto dto
    ) {
        DoctorProfileResponseDto response = doctorService.updateDoctor(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctor updated successfully", response)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorProfileResponseDto>> getDoctor(
            @PathVariable @NotNull @Min(1) Long id
    ) {
        DoctorProfileResponseDto response = doctorService.getDoctorById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctor fetched successfully", response)
        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<DoctorProfileResponseDto>>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Page<DoctorProfileResponseDto> doctors =
                doctorService.getAllDoctors(page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctors fetched successfully", doctors)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(
            @PathVariable @NotNull @Min(1) Long id
    ) {
        doctorService.deleteDoctor(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctor deleted successfully", null)
        );
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<DoctorProfileResponseDto>>> searchDoctors(
            @RequestParam(required = false) Long cityId,
            @RequestParam(required = false) Long areaId,
            @RequestParam(required = false) String specialization,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Page<DoctorProfileResponseDto> result =
                doctorService.searchDoctors(cityId, areaId, specialization, page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Doctors fetched successfully", result)
        );
    }
}