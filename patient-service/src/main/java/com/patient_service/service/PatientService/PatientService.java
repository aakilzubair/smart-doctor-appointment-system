package com.patient_service.service.PatientService;



import com.patient_service.dto.request.CreatePatientRequest;
import com.patient_service.dto.response.PatientResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    Long createPatient(CreatePatientRequest request);

    PatientResponse getPatient(Long id);

    Page<PatientResponse> getAllPatients(int page, int size, String sortBy);
}