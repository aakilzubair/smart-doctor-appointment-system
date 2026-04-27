package com.patient_service.controller;

import com.patient_service.dto.request.CreatePatientRequest;
import com.patient_service.dto.response.ApiResponse;
import com.patient_service.dto.response.PatientResponse;
import com.patient_service.service.PatientService.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> create(
            @Valid @RequestBody CreatePatientRequest request) {

        Long id = service.createPatient(request);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Patient created successfully", id)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> get(@PathVariable Long id) {

        PatientResponse patient = service.getPatient(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Patient fetched successfully", patient)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PatientResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patients fetched",
                        service.getAllPatients(page, size, sortBy)
                )
        );
    }
}