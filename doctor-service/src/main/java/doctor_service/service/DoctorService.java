package doctor_service.service;

import doctor_service.DtoRequest.CreateDoctorRequestDto;
import doctor_service.DtoRequest.UpdateDoctorRequestDto;
import doctor_service.DtoResponse.DoctorProfileResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {

    Long createDoctor(CreateDoctorRequestDto dto);

    DoctorProfileResponseDto updateDoctor(Long doctorId, UpdateDoctorRequestDto dto);

    DoctorProfileResponseDto getDoctorById(Long doctorId);
    Page<DoctorProfileResponseDto> getAllDoctors(int page, int size, String sortBy, String sortDir);


    void deleteDoctor(Long doctorId);


    Page<DoctorProfileResponseDto> searchDoctors(
            Long cityId,
            Long areaId,
            String specialization,
            int page,
            int size,
            String sortBy,
            String sortDir
    );

}
