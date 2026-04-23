package doctor_service.serviceImpl;

import doctor_service.DtoRequest.CreateDoctorRequestDto;
import doctor_service.DtoRequest.UpdateDoctorRequestDto;
import doctor_service.DtoResponse.DoctorProfileResponseDto;
import doctor_service.entity.Area;
import doctor_service.entity.City;
import doctor_service.entity.Doctor;
import doctor_service.entity.State;
import doctor_service.exception.ResourceNotFoundException;
import doctor_service.repository.AreaRepository;
import doctor_service.repository.CityRepository;
import doctor_service.repository.DoctorRepository;
import doctor_service.repository.StateRepository;
import doctor_service.service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final AreaRepository areaRepository;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            StateRepository stateRepository,
            CityRepository cityRepository,
            AreaRepository areaRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.areaRepository = areaRepository;
    }

    @Override
    @Transactional
    public Long createDoctor(CreateDoctorRequestDto dto) {


        State state = stateRepository.findById(dto.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("State not found"));

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City not found"));

        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new ResourceNotFoundException("Area not found"));

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setQualification(dto.getQualification());
        doctor.setExperience(dto.getExperience());
        doctor.setContact(dto.getContact());
        doctor.setUrl(dto.getUrl());
        doctor.setAddress(dto.getAddress());

        doctor.setState(state);
        doctor.setCity(city);
        doctor.setArea(area);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return savedDoctor.getId();
    }

    @Override
    @Transactional
    public DoctorProfileResponseDto updateDoctor(Long doctorId, UpdateDoctorRequestDto dto) {


        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (dto.getQualification() != null) {
            doctor.setQualification(dto.getQualification());
        }

        if (dto.getContact() != null) {
            doctor.setContact(dto.getContact());
        }

        if (dto.getExperience() != null) {
            doctor.setExperience(dto.getExperience());
        }

        if (dto.getUrl() != null) {
            doctor.setUrl(dto.getUrl());
        }

        if (dto.getAddress() != null) {
            doctor.setAddress(dto.getAddress());
        }

        Doctor updatedDoctor = doctorRepository.save(doctor);

        return mapToProfileDto(updatedDoctor);
    }

    @Override

    public DoctorProfileResponseDto getDoctorById(Long doctorId) {


        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        DoctorProfileResponseDto response = new DoctorProfileResponseDto();

        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setSpecialization(doctor.getSpecialization());
        response.setQualification(doctor.getQualification());
        response.setExperience(doctor.getExperience());
        response.setContact(doctor.getContact());
        response.setUrl(doctor.getUrl());
        response.setAddress(doctor.getAddress());

        response.setStateName(doctor.getState().getName());
        response.setCityName(doctor.getCity().getName());
        response.setAreaName(doctor.getArea().getName());

        return response;
    }

    private DoctorProfileResponseDto mapToProfileDto(Doctor doctor) {

        DoctorProfileResponseDto dto = new DoctorProfileResponseDto();

        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setQualification(doctor.getQualification());
        dto.setExperience(doctor.getExperience());
        dto.setContact(doctor.getContact());
        dto.setUrl(doctor.getUrl());
        dto.setAddress(doctor.getAddress());

        dto.setStateName(doctor.getState().getName());
        dto.setCityName(doctor.getCity().getName());
        dto.setAreaName(doctor.getArea().getName());

        return dto;
    }

    @Override

    public Page<DoctorProfileResponseDto> getAllDoctors(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        return doctorPage.map(this::mapToProfileDto);
    }

    @Override

    public void deleteDoctor(Long doctorId) {


        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        doctorRepository.deleteById(doctorId);
    }

    public Page<DoctorProfileResponseDto> searchDoctors(
            Long cityId,
            Long areaId,
            String specialization,
            int page,
            int size,
            String sortBy,
            String sortDir
    ) {

        // 🔥 basic validation
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid pagination parameters");
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Doctor> doctorPage =
                doctorRepository.searchDoctors(cityId, areaId, specialization, pageable);

        return doctorPage.map(this::mapToProfileDto);
    }


}