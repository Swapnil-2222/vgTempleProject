package com.techvg.temple.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.techvg.temple.domain.Hotel} entity. This class is used
 * in {@link com.techvg.temple.web.rest.HotelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /hotels?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HotelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter date;

    private StringFilter name;

    private StringFilter description;

    private StringFilter location;

    private DoubleFilter rent;

    private StringFilter emailId;

    private StringFilter contactPerson;

    private StringFilter status;

    private InstantFilter lastModified;

    private StringFilter lastModifiedBy;

    private StringFilter freeField1;

    private StringFilter freeField2;

    private LongFilter freeField5;

    private LongFilter freeField6;

    private InstantFilter freeField7;

    private InstantFilter freeField8;

    private BooleanFilter freeField9;

    private BooleanFilter freeField10;

    private Boolean distinct;

    public HotelCriteria() {}

    public HotelCriteria(HotelCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.location = other.location == null ? null : other.location.copy();
        this.rent = other.rent == null ? null : other.rent.copy();
        this.emailId = other.emailId == null ? null : other.emailId.copy();
        this.contactPerson = other.contactPerson == null ? null : other.contactPerson.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
        this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
        this.freeField5 = other.freeField5 == null ? null : other.freeField5.copy();
        this.freeField6 = other.freeField6 == null ? null : other.freeField6.copy();
        this.freeField7 = other.freeField7 == null ? null : other.freeField7.copy();
        this.freeField8 = other.freeField8 == null ? null : other.freeField8.copy();
        this.freeField9 = other.freeField9 == null ? null : other.freeField9.copy();
        this.freeField10 = other.freeField10 == null ? null : other.freeField10.copy();
        this.distinct = other.distinct;
    }

    @Override
    public HotelCriteria copy() {
        return new HotelCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getDate() {
        return date;
    }

    public InstantFilter date() {
        if (date == null) {
            date = new InstantFilter();
        }
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getLocation() {
        return location;
    }

    public StringFilter location() {
        if (location == null) {
            location = new StringFilter();
        }
        return location;
    }

    public void setLocation(StringFilter location) {
        this.location = location;
    }

    public DoubleFilter getRent() {
        return rent;
    }

    public DoubleFilter rent() {
        if (rent == null) {
            rent = new DoubleFilter();
        }
        return rent;
    }

    public void setRent(DoubleFilter rent) {
        this.rent = rent;
    }

    public StringFilter getEmailId() {
        return emailId;
    }

    public StringFilter emailId() {
        if (emailId == null) {
            emailId = new StringFilter();
        }
        return emailId;
    }

    public void setEmailId(StringFilter emailId) {
        this.emailId = emailId;
    }

    public StringFilter getContactPerson() {
        return contactPerson;
    }

    public StringFilter contactPerson() {
        if (contactPerson == null) {
            contactPerson = new StringFilter();
        }
        return contactPerson;
    }

    public void setContactPerson(StringFilter contactPerson) {
        this.contactPerson = contactPerson;
    }

    public StringFilter getStatus() {
        return status;
    }

    public StringFilter status() {
        if (status == null) {
            status = new StringFilter();
        }
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public InstantFilter getLastModified() {
        return lastModified;
    }

    public InstantFilter lastModified() {
        if (lastModified == null) {
            lastModified = new InstantFilter();
        }
        return lastModified;
    }

    public void setLastModified(InstantFilter lastModified) {
        this.lastModified = lastModified;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public StringFilter getFreeField1() {
        return freeField1;
    }

    public StringFilter freeField1() {
        if (freeField1 == null) {
            freeField1 = new StringFilter();
        }
        return freeField1;
    }

    public void setFreeField1(StringFilter freeField1) {
        this.freeField1 = freeField1;
    }

    public StringFilter getFreeField2() {
        return freeField2;
    }

    public StringFilter freeField2() {
        if (freeField2 == null) {
            freeField2 = new StringFilter();
        }
        return freeField2;
    }

    public void setFreeField2(StringFilter freeField2) {
        this.freeField2 = freeField2;
    }

    public LongFilter getFreeField5() {
        return freeField5;
    }

    public LongFilter freeField5() {
        if (freeField5 == null) {
            freeField5 = new LongFilter();
        }
        return freeField5;
    }

    public void setFreeField5(LongFilter freeField5) {
        this.freeField5 = freeField5;
    }

    public LongFilter getFreeField6() {
        return freeField6;
    }

    public LongFilter freeField6() {
        if (freeField6 == null) {
            freeField6 = new LongFilter();
        }
        return freeField6;
    }

    public void setFreeField6(LongFilter freeField6) {
        this.freeField6 = freeField6;
    }

    public InstantFilter getFreeField7() {
        return freeField7;
    }

    public InstantFilter freeField7() {
        if (freeField7 == null) {
            freeField7 = new InstantFilter();
        }
        return freeField7;
    }

    public void setFreeField7(InstantFilter freeField7) {
        this.freeField7 = freeField7;
    }

    public InstantFilter getFreeField8() {
        return freeField8;
    }

    public InstantFilter freeField8() {
        if (freeField8 == null) {
            freeField8 = new InstantFilter();
        }
        return freeField8;
    }

    public void setFreeField8(InstantFilter freeField8) {
        this.freeField8 = freeField8;
    }

    public BooleanFilter getFreeField9() {
        return freeField9;
    }

    public BooleanFilter freeField9() {
        if (freeField9 == null) {
            freeField9 = new BooleanFilter();
        }
        return freeField9;
    }

    public void setFreeField9(BooleanFilter freeField9) {
        this.freeField9 = freeField9;
    }

    public BooleanFilter getFreeField10() {
        return freeField10;
    }

    public BooleanFilter freeField10() {
        if (freeField10 == null) {
            freeField10 = new BooleanFilter();
        }
        return freeField10;
    }

    public void setFreeField10(BooleanFilter freeField10) {
        this.freeField10 = freeField10;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HotelCriteria that = (HotelCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(location, that.location) &&
            Objects.equals(rent, that.rent) &&
            Objects.equals(emailId, that.emailId) &&
            Objects.equals(contactPerson, that.contactPerson) &&
            Objects.equals(status, that.status) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(freeField1, that.freeField1) &&
            Objects.equals(freeField2, that.freeField2) &&
            Objects.equals(freeField5, that.freeField5) &&
            Objects.equals(freeField6, that.freeField6) &&
            Objects.equals(freeField7, that.freeField7) &&
            Objects.equals(freeField8, that.freeField8) &&
            Objects.equals(freeField9, that.freeField9) &&
            Objects.equals(freeField10, that.freeField10) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            date,
            name,
            description,
            location,
            rent,
            emailId,
            contactPerson,
            status,
            lastModified,
            lastModifiedBy,
            freeField1,
            freeField2,
            freeField5,
            freeField6,
            freeField7,
            freeField8,
            freeField9,
            freeField10,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HotelCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (location != null ? "location=" + location + ", " : "") +
            (rent != null ? "rent=" + rent + ", " : "") +
            (emailId != null ? "emailId=" + emailId + ", " : "") +
            (contactPerson != null ? "contactPerson=" + contactPerson + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField5 != null ? "freeField5=" + freeField5 + ", " : "") +
            (freeField6 != null ? "freeField6=" + freeField6 + ", " : "") +
            (freeField7 != null ? "freeField7=" + freeField7 + ", " : "") +
            (freeField8 != null ? "freeField8=" + freeField8 + ", " : "") +
            (freeField9 != null ? "freeField9=" + freeField9 + ", " : "") +
            (freeField10 != null ? "freeField10=" + freeField10 + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
