package com.techvg.temple.service.criteria;

import com.techvg.temple.domain.enumeration.Gender;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.techvg.temple.domain.Delegate} entity. This class is used
 * in {@link com.techvg.temple.web.rest.DelegateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /delegates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DelegateCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {

        public GenderFilter() {}

        public GenderFilter(GenderFilter filter) {
            super(filter);
        }

        @Override
        public GenderFilter copy() {
            return new GenderFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter fullName;

    private GenderFilter gender;

    private StringFilter nationality;

    private StringFilter countryOfResidence;

    private LocalDateFilter dateOfBirth;

    private StringFilter citizenship;

    private LongFilter mobileNo;

    private StringFilter emailId;

    private StringFilter emergencyContactName;

    private StringFilter emergencyContactRelationship;

    private LongFilter emergencyContactNo;

    private StringFilter countryCodeOne;

    private StringFilter countryCodeTwo;

    private StringFilter nationalityOne;

    private StringFilter countryOfBirth;

    private StringFilter cityOfResidence;

    private StringFilter stateOfResidence;

    private StringFilter districtOfResidence;

    private InstantFilter departureDate;

    private StringFilter departurePlace;

    private InstantFilter arrivalDate;

    private StringFilter arrivalPlace;

    private StringFilter status;

    private InstantFilter lastModified;

    private StringFilter lastModifiedBy;

    private BooleanFilter isTaxReceipt;

    private StringFilter profilePhotoContentType;

    private StringFilter passportImageContentType;

    private StringFilter conferenceName;

    private StringFilter freeField1;

    private StringFilter freeField2;

    private StringFilter freeField3;

    private StringFilter freeField4;

    private LongFilter freeField5;

    private LongFilter freeField6;

    private InstantFilter freeField7;

    private InstantFilter freeField8;

    private BooleanFilter freeField9;

    private BooleanFilter freeField10;

    private Boolean distinct;

    public DelegateCriteria() {}

    public DelegateCriteria(DelegateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.nationality = other.nationality == null ? null : other.nationality.copy();
        this.countryOfResidence = other.countryOfResidence == null ? null : other.countryOfResidence.copy();
        this.dateOfBirth = other.dateOfBirth == null ? null : other.dateOfBirth.copy();
        this.citizenship = other.citizenship == null ? null : other.citizenship.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.emailId = other.emailId == null ? null : other.emailId.copy();
        this.emergencyContactName = other.emergencyContactName == null ? null : other.emergencyContactName.copy();
        this.emergencyContactRelationship = other.emergencyContactRelationship == null ? null : other.emergencyContactRelationship.copy();
        this.emergencyContactNo = other.emergencyContactNo == null ? null : other.emergencyContactNo.copy();
        this.countryCodeOne = other.countryCodeOne == null ? null : other.countryCodeOne.copy();
        this.countryCodeTwo = other.countryCodeTwo == null ? null : other.countryCodeTwo.copy();
        this.nationalityOne = other.nationalityOne == null ? null : other.nationalityOne.copy();
        this.countryOfBirth = other.countryOfBirth == null ? null : other.countryOfBirth.copy();
        this.cityOfResidence = other.cityOfResidence == null ? null : other.cityOfResidence.copy();
        this.stateOfResidence = other.stateOfResidence == null ? null : other.stateOfResidence.copy();
        this.districtOfResidence = other.districtOfResidence == null ? null : other.districtOfResidence.copy();
        this.departureDate = other.departureDate == null ? null : other.departureDate.copy();
        this.departurePlace = other.departurePlace == null ? null : other.departurePlace.copy();
        this.arrivalDate = other.arrivalDate == null ? null : other.arrivalDate.copy();
        this.arrivalPlace = other.arrivalPlace == null ? null : other.arrivalPlace.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.isTaxReceipt = other.isTaxReceipt == null ? null : other.isTaxReceipt.copy();
        this.profilePhotoContentType = other.profilePhotoContentType == null ? null : other.profilePhotoContentType.copy();
        this.passportImageContentType = other.passportImageContentType == null ? null : other.passportImageContentType.copy();
        this.conferenceName = other.conferenceName == null ? null : other.conferenceName.copy();
        this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
        this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
        this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
        this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
        this.freeField5 = other.freeField5 == null ? null : other.freeField5.copy();
        this.freeField6 = other.freeField6 == null ? null : other.freeField6.copy();
        this.freeField7 = other.freeField7 == null ? null : other.freeField7.copy();
        this.freeField8 = other.freeField8 == null ? null : other.freeField8.copy();
        this.freeField9 = other.freeField9 == null ? null : other.freeField9.copy();
        this.freeField10 = other.freeField10 == null ? null : other.freeField10.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DelegateCriteria copy() {
        return new DelegateCriteria(this);
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

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public StringFilter fullName() {
        if (fullName == null) {
            fullName = new StringFilter();
        }
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public GenderFilter gender() {
        if (gender == null) {
            gender = new GenderFilter();
        }
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public StringFilter getNationality() {
        return nationality;
    }

    public StringFilter nationality() {
        if (nationality == null) {
            nationality = new StringFilter();
        }
        return nationality;
    }

    public void setNationality(StringFilter nationality) {
        this.nationality = nationality;
    }

    public StringFilter getCountryOfResidence() {
        return countryOfResidence;
    }

    public StringFilter countryOfResidence() {
        if (countryOfResidence == null) {
            countryOfResidence = new StringFilter();
        }
        return countryOfResidence;
    }

    public void setCountryOfResidence(StringFilter countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public LocalDateFilter getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDateFilter dateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = new LocalDateFilter();
        }
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateFilter dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StringFilter getCitizenship() {
        return citizenship;
    }

    public StringFilter citizenship() {
        if (citizenship == null) {
            citizenship = new StringFilter();
        }
        return citizenship;
    }

    public void setCitizenship(StringFilter citizenship) {
        this.citizenship = citizenship;
    }

    public LongFilter getMobileNo() {
        return mobileNo;
    }

    public LongFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new LongFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(LongFilter mobileNo) {
        this.mobileNo = mobileNo;
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

    public StringFilter getEmergencyContactName() {
        return emergencyContactName;
    }

    public StringFilter emergencyContactName() {
        if (emergencyContactName == null) {
            emergencyContactName = new StringFilter();
        }
        return emergencyContactName;
    }

    public void setEmergencyContactName(StringFilter emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public StringFilter getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public StringFilter emergencyContactRelationship() {
        if (emergencyContactRelationship == null) {
            emergencyContactRelationship = new StringFilter();
        }
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(StringFilter emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public LongFilter getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public LongFilter emergencyContactNo() {
        if (emergencyContactNo == null) {
            emergencyContactNo = new LongFilter();
        }
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(LongFilter emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public StringFilter getCountryCodeOne() {
        return countryCodeOne;
    }

    public StringFilter countryCodeOne() {
        if (countryCodeOne == null) {
            countryCodeOne = new StringFilter();
        }
        return countryCodeOne;
    }

    public void setCountryCodeOne(StringFilter countryCodeOne) {
        this.countryCodeOne = countryCodeOne;
    }

    public StringFilter getCountryCodeTwo() {
        return countryCodeTwo;
    }

    public StringFilter countryCodeTwo() {
        if (countryCodeTwo == null) {
            countryCodeTwo = new StringFilter();
        }
        return countryCodeTwo;
    }

    public void setCountryCodeTwo(StringFilter countryCodeTwo) {
        this.countryCodeTwo = countryCodeTwo;
    }

    public StringFilter getNationalityOne() {
        return nationalityOne;
    }

    public StringFilter nationalityOne() {
        if (nationalityOne == null) {
            nationalityOne = new StringFilter();
        }
        return nationalityOne;
    }

    public void setNationalityOne(StringFilter nationalityOne) {
        this.nationalityOne = nationalityOne;
    }

    public StringFilter getCountryOfBirth() {
        return countryOfBirth;
    }

    public StringFilter countryOfBirth() {
        if (countryOfBirth == null) {
            countryOfBirth = new StringFilter();
        }
        return countryOfBirth;
    }

    public void setCountryOfBirth(StringFilter countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public StringFilter getCityOfResidence() {
        return cityOfResidence;
    }

    public StringFilter cityOfResidence() {
        if (cityOfResidence == null) {
            cityOfResidence = new StringFilter();
        }
        return cityOfResidence;
    }

    public void setCityOfResidence(StringFilter cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public StringFilter getStateOfResidence() {
        return stateOfResidence;
    }

    public StringFilter stateOfResidence() {
        if (stateOfResidence == null) {
            stateOfResidence = new StringFilter();
        }
        return stateOfResidence;
    }

    public void setStateOfResidence(StringFilter stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public StringFilter getDistrictOfResidence() {
        return districtOfResidence;
    }

    public StringFilter districtOfResidence() {
        if (districtOfResidence == null) {
            districtOfResidence = new StringFilter();
        }
        return districtOfResidence;
    }

    public void setDistrictOfResidence(StringFilter districtOfResidence) {
        this.districtOfResidence = districtOfResidence;
    }

    public InstantFilter getDepartureDate() {
        return departureDate;
    }

    public InstantFilter departureDate() {
        if (departureDate == null) {
            departureDate = new InstantFilter();
        }
        return departureDate;
    }

    public void setDepartureDate(InstantFilter departureDate) {
        this.departureDate = departureDate;
    }

    public StringFilter getDeparturePlace() {
        return departurePlace;
    }

    public StringFilter departurePlace() {
        if (departurePlace == null) {
            departurePlace = new StringFilter();
        }
        return departurePlace;
    }

    public void setDeparturePlace(StringFilter departurePlace) {
        this.departurePlace = departurePlace;
    }

    public InstantFilter getArrivalDate() {
        return arrivalDate;
    }

    public InstantFilter arrivalDate() {
        if (arrivalDate == null) {
            arrivalDate = new InstantFilter();
        }
        return arrivalDate;
    }

    public void setArrivalDate(InstantFilter arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public StringFilter getArrivalPlace() {
        return arrivalPlace;
    }

    public StringFilter arrivalPlace() {
        if (arrivalPlace == null) {
            arrivalPlace = new StringFilter();
        }
        return arrivalPlace;
    }

    public void setArrivalPlace(StringFilter arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
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

    public BooleanFilter getIsTaxReceipt() {
        return isTaxReceipt;
    }

    public BooleanFilter isTaxReceipt() {
        if (isTaxReceipt == null) {
            isTaxReceipt = new BooleanFilter();
        }
        return isTaxReceipt;
    }

    public void setIsTaxReceipt(BooleanFilter isTaxReceipt) {
        this.isTaxReceipt = isTaxReceipt;
    }

    public StringFilter getProfilePhotoContentType() {
        return profilePhotoContentType;
    }

    public StringFilter profilePhotoContentType() {
        if (profilePhotoContentType == null) {
            profilePhotoContentType = new StringFilter();
        }
        return profilePhotoContentType;
    }

    public void setProfilePhotoContentType(StringFilter profilePhotoContentType) {
        this.profilePhotoContentType = profilePhotoContentType;
    }

    public StringFilter getPassportImageContentType() {
        return passportImageContentType;
    }

    public StringFilter passportImageContentType() {
        if (passportImageContentType == null) {
            passportImageContentType = new StringFilter();
        }
        return passportImageContentType;
    }

    public void setPassportImageContentType(StringFilter passportImageContentType) {
        this.passportImageContentType = passportImageContentType;
    }

    public StringFilter getConferenceName() {
        return conferenceName;
    }

    public StringFilter conferenceName() {
        if (conferenceName == null) {
            conferenceName = new StringFilter();
        }
        return conferenceName;
    }

    public void setConferenceName(StringFilter conferenceName) {
        this.conferenceName = conferenceName;
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

    public StringFilter getFreeField3() {
        return freeField3;
    }

    public StringFilter freeField3() {
        if (freeField3 == null) {
            freeField3 = new StringFilter();
        }
        return freeField3;
    }

    public void setFreeField3(StringFilter freeField3) {
        this.freeField3 = freeField3;
    }

    public StringFilter getFreeField4() {
        return freeField4;
    }

    public StringFilter freeField4() {
        if (freeField4 == null) {
            freeField4 = new StringFilter();
        }
        return freeField4;
    }

    public void setFreeField4(StringFilter freeField4) {
        this.freeField4 = freeField4;
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
        final DelegateCriteria that = (DelegateCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(nationality, that.nationality) &&
            Objects.equals(countryOfResidence, that.countryOfResidence) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(citizenship, that.citizenship) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(emailId, that.emailId) &&
            Objects.equals(emergencyContactName, that.emergencyContactName) &&
            Objects.equals(emergencyContactRelationship, that.emergencyContactRelationship) &&
            Objects.equals(emergencyContactNo, that.emergencyContactNo) &&
            Objects.equals(countryCodeOne, that.countryCodeOne) &&
            Objects.equals(countryCodeTwo, that.countryCodeTwo) &&
            Objects.equals(nationalityOne, that.nationalityOne) &&
            Objects.equals(countryOfBirth, that.countryOfBirth) &&
            Objects.equals(cityOfResidence, that.cityOfResidence) &&
            Objects.equals(stateOfResidence, that.stateOfResidence) &&
            Objects.equals(districtOfResidence, that.districtOfResidence) &&
            Objects.equals(departureDate, that.departureDate) &&
            Objects.equals(departurePlace, that.departurePlace) &&
            Objects.equals(arrivalDate, that.arrivalDate) &&
            Objects.equals(arrivalPlace, that.arrivalPlace) &&
            Objects.equals(status, that.status) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(isTaxReceipt, that.isTaxReceipt) &&
            Objects.equals(profilePhotoContentType, that.profilePhotoContentType) &&
            Objects.equals(passportImageContentType, that.passportImageContentType) &&
            Objects.equals(conferenceName, that.conferenceName) &&
            Objects.equals(freeField1, that.freeField1) &&
            Objects.equals(freeField2, that.freeField2) &&
            Objects.equals(freeField3, that.freeField3) &&
            Objects.equals(freeField4, that.freeField4) &&
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
            title,
            fullName,
            gender,
            nationality,
            countryOfResidence,
            dateOfBirth,
            citizenship,
            mobileNo,
            emailId,
            emergencyContactName,
            emergencyContactRelationship,
            emergencyContactNo,
            countryCodeOne,
            countryCodeTwo,
            nationalityOne,
            countryOfBirth,
            cityOfResidence,
            stateOfResidence,
            districtOfResidence,
            departureDate,
            departurePlace,
            arrivalDate,
            arrivalPlace,
            status,
            lastModified,
            lastModifiedBy,
            isTaxReceipt,
            profilePhotoContentType,
            passportImageContentType,
            conferenceName,
            freeField1,
            freeField2,
            freeField3,
            freeField4,
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
        return "DelegateCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (gender != null ? "gender=" + gender + ", " : "") +
            (nationality != null ? "nationality=" + nationality + ", " : "") +
            (countryOfResidence != null ? "countryOfResidence=" + countryOfResidence + ", " : "") +
            (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "") +
            (citizenship != null ? "citizenship=" + citizenship + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (emailId != null ? "emailId=" + emailId + ", " : "") +
            (emergencyContactName != null ? "emergencyContactName=" + emergencyContactName + ", " : "") +
            (emergencyContactRelationship != null ? "emergencyContactRelationship=" + emergencyContactRelationship + ", " : "") +
            (emergencyContactNo != null ? "emergencyContactNo=" + emergencyContactNo + ", " : "") +
            (countryCodeOne != null ? "countryCodeOne=" + countryCodeOne + ", " : "") +
            (countryCodeTwo != null ? "countryCodeTwo=" + countryCodeTwo + ", " : "") +
            (nationalityOne != null ? "nationalityOne=" + nationalityOne + ", " : "") +
            (countryOfBirth != null ? "countryOfBirth=" + countryOfBirth + ", " : "") +
            (cityOfResidence != null ? "cityOfResidence=" + cityOfResidence + ", " : "") +
            (stateOfResidence != null ? "stateOfResidence=" + stateOfResidence + ", " : "") +
            (districtOfResidence != null ? "districtOfResidence=" + districtOfResidence + ", " : "") +
            (departureDate != null ? "departureDate=" + departureDate + ", " : "") +
            (departurePlace != null ? "departurePlace=" + departurePlace + ", " : "") +
            (arrivalDate != null ? "arrivalDate=" + arrivalDate + ", " : "") +
            (arrivalPlace != null ? "arrivalPlace=" + arrivalPlace + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (isTaxReceipt != null ? "isTaxReceipt=" + isTaxReceipt + ", " : "") +
            (profilePhotoContentType != null ? "profilePhotoContentType=" + profilePhotoContentType + ", " : "") +
            (passportImageContentType != null ? "passportImageContentType=" + passportImageContentType + ", " : "") +
            (conferenceName != null ? "conferenceName=" + conferenceName + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
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
