package com.techvg.temple.domain;

import com.techvg.temple.domain.enumeration.Gender;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Delegate.
 */
@Entity
@Table(name = "delegate")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Delegate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "country_of_residence")
    private String countryOfResidence;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "mobile_no")
    private Long mobileNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_relationship")
    private String emergencyContactRelationship;

    @Column(name = "emergency_contact_no")
    private Long emergencyContactNo;

    @Column(name = "country_code_one")
    private String countryCodeOne;

    @Column(name = "country_code_two")
    private String countryCodeTwo;

    @Column(name = "nationality_one")
    private String nationalityOne;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @Column(name = "city_of_residence")
    private String cityOfResidence;

    @Column(name = "state_of_residence")
    private String stateOfResidence;

    @Column(name = "district_of_residence")
    private String districtOfResidence;

    @Column(name = "departure_date")
    private Instant departureDate;

    @Column(name = "departure_place")
    private String departurePlace;

    @Column(name = "arrival_date")
    private Instant arrivalDate;

    @Column(name = "arrival_place")
    private String arrivalPlace;

    @Column(name = "status")
    private String status;

    @Column(name = "last_modified")
    private Instant lastModified;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "is_tax_receipt")
    private Boolean isTaxReceipt;

    @Column(name = "profile_photo_content_type")
    private String profilePhotoContentType;

    @Column(name = "passport_image_content_type")
    private String passportImageContentType;

    @Column(name = "conference_name")
    private String conferenceName;

    @Column(name = "free_field_1")
    private String freeField1;

    @Column(name = "free_field_2")
    private String freeField2;

    @Column(name = "free_field_3")
    private String freeField3;

    @Column(name = "free_field_4")
    private String freeField4;

    @Column(name = "free_field_5")
    private Long freeField5;

    @Column(name = "free_field_6")
    private Long freeField6;

    @Column(name = "free_field_7")
    private Instant freeField7;

    @Column(name = "free_field_8")
    private Instant freeField8;

    @Column(name = "free_field_9")
    private Boolean freeField9;

    @Column(name = "free_field_10")
    private Boolean freeField10;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Delegate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Delegate title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Delegate fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Delegate gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public Delegate nationality(String nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }

    public Delegate countryOfResidence(String countryOfResidence) {
        this.setCountryOfResidence(countryOfResidence);
        return this;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Delegate dateOfBirth(LocalDate dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCitizenship() {
        return this.citizenship;
    }

    public Delegate citizenship(String citizenship) {
        this.setCitizenship(citizenship);
        return this;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Long getMobileNo() {
        return this.mobileNo;
    }

    public Delegate mobileNo(Long mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public Delegate emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmergencyContactName() {
        return this.emergencyContactName;
    }

    public Delegate emergencyContactName(String emergencyContactName) {
        this.setEmergencyContactName(emergencyContactName);
        return this;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelationship() {
        return this.emergencyContactRelationship;
    }

    public Delegate emergencyContactRelationship(String emergencyContactRelationship) {
        this.setEmergencyContactRelationship(emergencyContactRelationship);
        return this;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public Long getEmergencyContactNo() {
        return this.emergencyContactNo;
    }

    public Delegate emergencyContactNo(Long emergencyContactNo) {
        this.setEmergencyContactNo(emergencyContactNo);
        return this;
    }

    public void setEmergencyContactNo(Long emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getCountryCodeOne() {
        return this.countryCodeOne;
    }

    public Delegate countryCodeOne(String countryCodeOne) {
        this.setCountryCodeOne(countryCodeOne);
        return this;
    }

    public void setCountryCodeOne(String countryCodeOne) {
        this.countryCodeOne = countryCodeOne;
    }

    public String getCountryCodeTwo() {
        return this.countryCodeTwo;
    }

    public Delegate countryCodeTwo(String countryCodeTwo) {
        this.setCountryCodeTwo(countryCodeTwo);
        return this;
    }

    public void setCountryCodeTwo(String countryCodeTwo) {
        this.countryCodeTwo = countryCodeTwo;
    }

    public String getNationalityOne() {
        return this.nationalityOne;
    }

    public Delegate nationalityOne(String nationalityOne) {
        this.setNationalityOne(nationalityOne);
        return this;
    }

    public void setNationalityOne(String nationalityOne) {
        this.nationalityOne = nationalityOne;
    }

    public String getCountryOfBirth() {
        return this.countryOfBirth;
    }

    public Delegate countryOfBirth(String countryOfBirth) {
        this.setCountryOfBirth(countryOfBirth);
        return this;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getCityOfResidence() {
        return this.cityOfResidence;
    }

    public Delegate cityOfResidence(String cityOfResidence) {
        this.setCityOfResidence(cityOfResidence);
        return this;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getStateOfResidence() {
        return this.stateOfResidence;
    }

    public Delegate stateOfResidence(String stateOfResidence) {
        this.setStateOfResidence(stateOfResidence);
        return this;
    }

    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public String getDistrictOfResidence() {
        return this.districtOfResidence;
    }

    public Delegate districtOfResidence(String districtOfResidence) {
        this.setDistrictOfResidence(districtOfResidence);
        return this;
    }

    public void setDistrictOfResidence(String districtOfResidence) {
        this.districtOfResidence = districtOfResidence;
    }

    public Instant getDepartureDate() {
        return this.departureDate;
    }

    public Delegate departureDate(Instant departureDate) {
        this.setDepartureDate(departureDate);
        return this;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeparturePlace() {
        return this.departurePlace;
    }

    public Delegate departurePlace(String departurePlace) {
        this.setDeparturePlace(departurePlace);
        return this;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public Instant getArrivalDate() {
        return this.arrivalDate;
    }

    public Delegate arrivalDate(Instant arrivalDate) {
        this.setArrivalDate(arrivalDate);
        return this;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalPlace() {
        return this.arrivalPlace;
    }

    public Delegate arrivalPlace(String arrivalPlace) {
        this.setArrivalPlace(arrivalPlace);
        return this;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getStatus() {
        return this.status;
    }

    public Delegate status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getLastModified() {
        return this.lastModified;
    }

    public Delegate lastModified(Instant lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Delegate lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Boolean getIsTaxReceipt() {
        return this.isTaxReceipt;
    }

    public Delegate isTaxReceipt(Boolean isTaxReceipt) {
        this.setIsTaxReceipt(isTaxReceipt);
        return this;
    }

    public void setIsTaxReceipt(Boolean isTaxReceipt) {
        this.isTaxReceipt = isTaxReceipt;
    }

    public String getProfilePhotoContentType() {
        return this.profilePhotoContentType;
    }

    public Delegate profilePhotoContentType(String profilePhotoContentType) {
        this.setProfilePhotoContentType(profilePhotoContentType);
        return this;
    }

    public void setProfilePhotoContentType(String profilePhotoContentType) {
        this.profilePhotoContentType = profilePhotoContentType;
    }

    public String getPassportImageContentType() {
        return this.passportImageContentType;
    }

    public Delegate passportImageContentType(String passportImageContentType) {
        this.setPassportImageContentType(passportImageContentType);
        return this;
    }

    public void setPassportImageContentType(String passportImageContentType) {
        this.passportImageContentType = passportImageContentType;
    }

    public String getConferenceName() {
        return this.conferenceName;
    }

    public Delegate conferenceName(String conferenceName) {
        this.setConferenceName(conferenceName);
        return this;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Delegate freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Delegate freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Delegate freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Delegate freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public Long getFreeField5() {
        return this.freeField5;
    }

    public Delegate freeField5(Long freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(Long freeField5) {
        this.freeField5 = freeField5;
    }

    public Long getFreeField6() {
        return this.freeField6;
    }

    public Delegate freeField6(Long freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(Long freeField6) {
        this.freeField6 = freeField6;
    }

    public Instant getFreeField7() {
        return this.freeField7;
    }

    public Delegate freeField7(Instant freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(Instant freeField7) {
        this.freeField7 = freeField7;
    }

    public Instant getFreeField8() {
        return this.freeField8;
    }

    public Delegate freeField8(Instant freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(Instant freeField8) {
        this.freeField8 = freeField8;
    }

    public Boolean getFreeField9() {
        return this.freeField9;
    }

    public Delegate freeField9(Boolean freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(Boolean freeField9) {
        this.freeField9 = freeField9;
    }

    public Boolean getFreeField10() {
        return this.freeField10;
    }

    public Delegate freeField10(Boolean freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(Boolean freeField10) {
        this.freeField10 = freeField10;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delegate)) {
            return false;
        }
        return getId() != null && getId().equals(((Delegate) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Delegate{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", gender='" + getGender() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", countryOfResidence='" + getCountryOfResidence() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", citizenship='" + getCitizenship() + "'" +
            ", mobileNo=" + getMobileNo() +
            ", emailId='" + getEmailId() + "'" +
            ", emergencyContactName='" + getEmergencyContactName() + "'" +
            ", emergencyContactRelationship='" + getEmergencyContactRelationship() + "'" +
            ", emergencyContactNo=" + getEmergencyContactNo() +
            ", countryCodeOne='" + getCountryCodeOne() + "'" +
            ", countryCodeTwo='" + getCountryCodeTwo() + "'" +
            ", nationalityOne='" + getNationalityOne() + "'" +
            ", countryOfBirth='" + getCountryOfBirth() + "'" +
            ", cityOfResidence='" + getCityOfResidence() + "'" +
            ", stateOfResidence='" + getStateOfResidence() + "'" +
            ", districtOfResidence='" + getDistrictOfResidence() + "'" +
            ", departureDate='" + getDepartureDate() + "'" +
            ", departurePlace='" + getDeparturePlace() + "'" +
            ", arrivalDate='" + getArrivalDate() + "'" +
            ", arrivalPlace='" + getArrivalPlace() + "'" +
            ", status='" + getStatus() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", isTaxReceipt='" + getIsTaxReceipt() + "'" +
            ", profilePhotoContentType='" + getProfilePhotoContentType() + "'" +
            ", passportImageContentType='" + getPassportImageContentType() + "'" +
            ", conferenceName='" + getConferenceName() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5=" + getFreeField5() +
            ", freeField6=" + getFreeField6() +
            ", freeField7='" + getFreeField7() + "'" +
            ", freeField8='" + getFreeField8() + "'" +
            ", freeField9='" + getFreeField9() + "'" +
            ", freeField10='" + getFreeField10() + "'" +
            "}";
    }
}
