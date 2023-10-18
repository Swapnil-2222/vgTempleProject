package com.techvg.temple.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "location")
    private String location;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "description")
    private String description;

    @Column(name = "no_of_participants")
    private Long noOfParticipants;

    @Column(name = "time")
    private String time;

    @Column(name = "mobile_no")
    private Long mobileNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "comments")
    private String comments;

    @Column(name = "profile_photo_type")
    private String profilePhotoType;

    @Column(name = "signature_image_type")
    private String signatureImageType;

    @Column(name = "status")
    private String status;

    @Column(name = "last_modified")
    private Instant lastModified;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

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

    @Column(name = "free_field_11")
    private Boolean freeField11;

    @Column(name = "free_field_12")
    private Boolean freeField12;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Activity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return this.date;
    }

    public Activity date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public Activity organizationName(String organizationName) {
        this.setOrganizationName(organizationName);
        return this;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLocation() {
        return this.location;
    }

    public Activity location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public Activity activityName(String activityName) {
        this.setActivityName(activityName);
        return this;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return this.description;
    }

    public Activity description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNoOfParticipants() {
        return this.noOfParticipants;
    }

    public Activity noOfParticipants(Long noOfParticipants) {
        this.setNoOfParticipants(noOfParticipants);
        return this;
    }

    public void setNoOfParticipants(Long noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public String getTime() {
        return this.time;
    }

    public Activity time(String time) {
        this.setTime(time);
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getMobileNo() {
        return this.mobileNo;
    }

    public Activity mobileNo(Long mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public Activity emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public Activity contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getComments() {
        return this.comments;
    }

    public Activity comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProfilePhotoType() {
        return this.profilePhotoType;
    }

    public Activity profilePhotoType(String profilePhotoType) {
        this.setProfilePhotoType(profilePhotoType);
        return this;
    }

    public void setProfilePhotoType(String profilePhotoType) {
        this.profilePhotoType = profilePhotoType;
    }

    public String getSignatureImageType() {
        return this.signatureImageType;
    }

    public Activity signatureImageType(String signatureImageType) {
        this.setSignatureImageType(signatureImageType);
        return this;
    }

    public void setSignatureImageType(String signatureImageType) {
        this.signatureImageType = signatureImageType;
    }

    public String getStatus() {
        return this.status;
    }

    public Activity status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getLastModified() {
        return this.lastModified;
    }

    public Activity lastModified(Instant lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Activity lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Activity freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Activity freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Activity freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Activity freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public Long getFreeField5() {
        return this.freeField5;
    }

    public Activity freeField5(Long freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(Long freeField5) {
        this.freeField5 = freeField5;
    }

    public Long getFreeField6() {
        return this.freeField6;
    }

    public Activity freeField6(Long freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(Long freeField6) {
        this.freeField6 = freeField6;
    }

    public Instant getFreeField7() {
        return this.freeField7;
    }

    public Activity freeField7(Instant freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(Instant freeField7) {
        this.freeField7 = freeField7;
    }

    public Instant getFreeField8() {
        return this.freeField8;
    }

    public Activity freeField8(Instant freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(Instant freeField8) {
        this.freeField8 = freeField8;
    }

    public Boolean getFreeField9() {
        return this.freeField9;
    }

    public Activity freeField9(Boolean freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(Boolean freeField9) {
        this.freeField9 = freeField9;
    }

    public Boolean getFreeField10() {
        return this.freeField10;
    }

    public Activity freeField10(Boolean freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(Boolean freeField10) {
        this.freeField10 = freeField10;
    }

    public Boolean getFreeField11() {
        return this.freeField11;
    }

    public Activity freeField11(Boolean freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(Boolean freeField11) {
        this.freeField11 = freeField11;
    }

    public Boolean getFreeField12() {
        return this.freeField12;
    }

    public Activity freeField12(Boolean freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(Boolean freeField12) {
        this.freeField12 = freeField12;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activity)) {
            return false;
        }
        return getId() != null && getId().equals(((Activity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Activity{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", organizationName='" + getOrganizationName() + "'" +
            ", location='" + getLocation() + "'" +
            ", activityName='" + getActivityName() + "'" +
            ", description='" + getDescription() + "'" +
            ", noOfParticipants=" + getNoOfParticipants() +
            ", time='" + getTime() + "'" +
            ", mobileNo=" + getMobileNo() +
            ", emailId='" + getEmailId() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", comments='" + getComments() + "'" +
            ", profilePhotoType='" + getProfilePhotoType() + "'" +
            ", signatureImageType='" + getSignatureImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
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
            ", freeField11='" + getFreeField11() + "'" +
            ", freeField12='" + getFreeField12() + "'" +
            "}";
    }
}
