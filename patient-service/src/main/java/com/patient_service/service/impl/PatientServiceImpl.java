package com.patient_service.service.impl;

import com.patient_service.dto.request.CreatePatientRequest;
import com.patient_service.dto.response.PatientResponse;
import com.patient_service.entity.Patient;
import com.patient_service.exception.BadRequestException;
import com.patient_service.exception.ResourceNotFoundException;
import com.patient_service.repository.PatientRepository;
import com.patient_service.service.PatientService.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long createPatient(CreatePatientRequest request) {

        // 🔒 Business validation
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        // 🔄 DTO → Entity
        Patient patient = mapToEntity(request);

        return repository.save(patient).getId();
    }

    @Override
    public PatientResponse getPatient(Long id) {

        Patient patient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        return mapToResponse(patient);
    }

    @Override
    public Page<PatientResponse> getAllPatients(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        return repository.findAll(pageable)
                .map(this::mapToResponse);
    }

    // =========================
    // 🔄 MAPPING METHODS
    // =========================

    public Patient mapToEntity(CreatePatientRequest request) {

        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setCreatedAt(LocalDateTime.now());

        return patient;
    }

    private PatientResponse mapToResponse(Patient patient) {

        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setEmail(patient.getEmail());

        return response;
    }
}