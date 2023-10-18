package com.techvg.temple.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Hotel.
 */
@Entity
@Table(name = "hotel")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "rent")
    private Double rent;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "contact_person")
    private String contactPerson;

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

    public Hotel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return this.date;
    }

    public Hotel date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public Hotel name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Hotel description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public Hotel location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRent() {
        return this.rent;
    }

    public Hotel rent(Double rent) {
        this.setRent(rent);
        return this;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public Hotel emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public Hotel contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getStatus() {
        return this.status;
    }

    public Hotel status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getLastModified() {
        return this.lastModified;
    }

    public Hotel lastModified(Instant lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Hotel lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Hotel freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Hotel freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public Long getFreeField5() {
        return this.freeField5;
    }

    public Hotel freeField5(Long freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(Long freeField5) {
        this.freeField5 = freeField5;
    }

    public Long getFreeField6() {
        return this.freeField6;
    }

    public Hotel freeField6(Long freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(Long freeField6) {
        this.freeField6 = freeField6;
    }

    public Instant getFreeField7() {
        return this.freeField7;
    }

    public Hotel freeField7(Instant freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(Instant freeField7) {
        this.freeField7 = freeField7;
    }

    public Instant getFreeField8() {
        return this.freeField8;
    }

    public Hotel freeField8(Instant freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(Instant freeField8) {
        this.freeField8 = freeField8;
    }

    public Boolean getFreeField9() {
        return this.freeField9;
    }

    public Hotel freeField9(Boolean freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(Boolean freeField9) {
        this.freeField9 = freeField9;
    }

    public Boolean getFreeField10() {
        return this.freeField10;
    }

    public Hotel freeField10(Boolean freeField10) {
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
        if (!(o instanceof Hotel)) {
            return false;
        }
        return getId() != null && getId().equals(((Hotel) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hotel{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", location='" + getLocation() + "'" +
            ", rent=" + getRent() +
            ", emailId='" + getEmailId() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", status='" + getStatus() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField5=" + getFreeField5() +
            ", freeField6=" + getFreeField6() +
            ", freeField7='" + getFreeField7() + "'" +
            ", freeField8='" + getFreeField8() + "'" +
            ", freeField9='" + getFreeField9() + "'" +
            ", freeField10='" + getFreeField10() + "'" +
            "}";
    }
}
