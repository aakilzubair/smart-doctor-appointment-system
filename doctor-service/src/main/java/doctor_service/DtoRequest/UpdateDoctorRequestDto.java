package doctor_service.DtoRequest;

import jakarta.validation.constraints.Size;

public class UpdateDoctorRequestDto {
    @Size(min = 2, message = "Qualification must be at least 2 characters")
    private String qualification;

    @Size(min = 10, message = "Contact  cannot be empty")
    private String contact;

    @Size(min = 1, message = "Experience cannot be empty")
    private String experience;

    @Size(max = 2000, message = "URL too long")
    private String url;

    @Size(min = 5, message = "Address too short")
    private String address;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
