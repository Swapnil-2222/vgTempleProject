package com.techvg.temple.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.temple.IntegrationTest;
import com.techvg.temple.domain.Delegate;
import com.techvg.temple.domain.enumeration.Gender;
import com.techvg.temple.repository.DelegateRepository;
import com.techvg.temple.service.dto.DelegateDTO;
import com.techvg.temple.service.mapper.DelegateMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DelegateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DelegateResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_RESIDENCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_OF_BIRTH = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CITIZENSHIP = "AAAAAAAAAA";
    private static final String UPDATED_CITIZENSHIP = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE_NO = 1L;
    private static final Long UPDATED_MOBILE_NO = 2L;
    private static final Long SMALLER_MOBILE_NO = 1L - 1L;

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_RELATIONSHIP = "BBBBBBBBBB";

    private static final Long DEFAULT_EMERGENCY_CONTACT_NO = 1L;
    private static final Long UPDATED_EMERGENCY_CONTACT_NO = 2L;
    private static final Long SMALLER_EMERGENCY_CONTACT_NO = 1L - 1L;

    private static final String DEFAULT_COUNTRY_CODE_ONE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE_ONE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE_TWO = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE_TWO = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY_ONE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY_ONE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_OF_RESIDENCE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_OF_RESIDENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_OF_RESIDENCE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DEPARTURE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEPARTURE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DEPARTURE_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTURE_PLACE = "BBBBBBBBBB";

    private static final Instant DEFAULT_ARRIVAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ARRIVAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ARRIVAL_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_TAX_RECEIPT = false;
    private static final Boolean UPDATED_IS_TAX_RECEIPT = true;

    private static final String DEFAULT_PROFILE_PHOTO_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_PHOTO_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_IMAGE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFERENCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFERENCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final Long DEFAULT_FREE_FIELD_5 = 1L;
    private static final Long UPDATED_FREE_FIELD_5 = 2L;
    private static final Long SMALLER_FREE_FIELD_5 = 1L - 1L;

    private static final Long DEFAULT_FREE_FIELD_6 = 1L;
    private static final Long UPDATED_FREE_FIELD_6 = 2L;
    private static final Long SMALLER_FREE_FIELD_6 = 1L - 1L;

    private static final Instant DEFAULT_FREE_FIELD_7 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FREE_FIELD_7 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FREE_FIELD_8 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FREE_FIELD_8 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_FREE_FIELD_9 = false;
    private static final Boolean UPDATED_FREE_FIELD_9 = true;

    private static final Boolean DEFAULT_FREE_FIELD_10 = false;
    private static final Boolean UPDATED_FREE_FIELD_10 = true;

    private static final String ENTITY_API_URL = "/api/delegates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DelegateRepository delegateRepository;

    @Autowired
    private DelegateMapper delegateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDelegateMockMvc;

    private Delegate delegate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegate createEntity(EntityManager em) {
        Delegate delegate = new Delegate()
            .title(DEFAULT_TITLE)
            .fullName(DEFAULT_FULL_NAME)
            .gender(DEFAULT_GENDER)
            .nationality(DEFAULT_NATIONALITY)
            .countryOfResidence(DEFAULT_COUNTRY_OF_RESIDENCE)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .citizenship(DEFAULT_CITIZENSHIP)
            .mobileNo(DEFAULT_MOBILE_NO)
            .emailId(DEFAULT_EMAIL_ID)
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactRelationship(DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP)
            .emergencyContactNo(DEFAULT_EMERGENCY_CONTACT_NO)
            .countryCodeOne(DEFAULT_COUNTRY_CODE_ONE)
            .countryCodeTwo(DEFAULT_COUNTRY_CODE_TWO)
            .nationalityOne(DEFAULT_NATIONALITY_ONE)
            .countryOfBirth(DEFAULT_COUNTRY_OF_BIRTH)
            .cityOfResidence(DEFAULT_CITY_OF_RESIDENCE)
            .stateOfResidence(DEFAULT_STATE_OF_RESIDENCE)
            .districtOfResidence(DEFAULT_DISTRICT_OF_RESIDENCE)
            .departureDate(DEFAULT_DEPARTURE_DATE)
            .departurePlace(DEFAULT_DEPARTURE_PLACE)
            .arrivalDate(DEFAULT_ARRIVAL_DATE)
            .arrivalPlace(DEFAULT_ARRIVAL_PLACE)
            .status(DEFAULT_STATUS)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .isTaxReceipt(DEFAULT_IS_TAX_RECEIPT)
            .profilePhotoContentType(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE)
            .passportImageContentType(DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE)
            .conferenceName(DEFAULT_CONFERENCE_NAME)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10);
        return delegate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegate createUpdatedEntity(EntityManager em) {
        Delegate delegate = new Delegate()
            .title(UPDATED_TITLE)
            .fullName(UPDATED_FULL_NAME)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .countryOfResidence(UPDATED_COUNTRY_OF_RESIDENCE)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .citizenship(UPDATED_CITIZENSHIP)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactRelationship(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .countryCodeOne(UPDATED_COUNTRY_CODE_ONE)
            .countryCodeTwo(UPDATED_COUNTRY_CODE_TWO)
            .nationalityOne(UPDATED_NATIONALITY_ONE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .cityOfResidence(UPDATED_CITY_OF_RESIDENCE)
            .stateOfResidence(UPDATED_STATE_OF_RESIDENCE)
            .districtOfResidence(UPDATED_DISTRICT_OF_RESIDENCE)
            .departureDate(UPDATED_DEPARTURE_DATE)
            .departurePlace(UPDATED_DEPARTURE_PLACE)
            .arrivalDate(UPDATED_ARRIVAL_DATE)
            .arrivalPlace(UPDATED_ARRIVAL_PLACE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .isTaxReceipt(UPDATED_IS_TAX_RECEIPT)
            .profilePhotoContentType(UPDATED_PROFILE_PHOTO_CONTENT_TYPE)
            .passportImageContentType(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE)
            .conferenceName(UPDATED_CONFERENCE_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);
        return delegate;
    }

    @BeforeEach
    public void initTest() {
        delegate = createEntity(em);
    }

    @Test
    @Transactional
    void createDelegate() throws Exception {
        int databaseSizeBeforeCreate = delegateRepository.findAll().size();
        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);
        restDelegateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(delegateDTO)))
            .andExpect(status().isCreated());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeCreate + 1);
        Delegate testDelegate = delegateList.get(delegateList.size() - 1);
        assertThat(testDelegate.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testDelegate.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testDelegate.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDelegate.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testDelegate.getCountryOfResidence()).isEqualTo(DEFAULT_COUNTRY_OF_RESIDENCE);
        assertThat(testDelegate.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testDelegate.getCitizenship()).isEqualTo(DEFAULT_CITIZENSHIP);
        assertThat(testDelegate.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testDelegate.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testDelegate.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testDelegate.getEmergencyContactRelationship()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP);
        assertThat(testDelegate.getEmergencyContactNo()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NO);
        assertThat(testDelegate.getCountryCodeOne()).isEqualTo(DEFAULT_COUNTRY_CODE_ONE);
        assertThat(testDelegate.getCountryCodeTwo()).isEqualTo(DEFAULT_COUNTRY_CODE_TWO);
        assertThat(testDelegate.getNationalityOne()).isEqualTo(DEFAULT_NATIONALITY_ONE);
        assertThat(testDelegate.getCountryOfBirth()).isEqualTo(DEFAULT_COUNTRY_OF_BIRTH);
        assertThat(testDelegate.getCityOfResidence()).isEqualTo(DEFAULT_CITY_OF_RESIDENCE);
        assertThat(testDelegate.getStateOfResidence()).isEqualTo(DEFAULT_STATE_OF_RESIDENCE);
        assertThat(testDelegate.getDistrictOfResidence()).isEqualTo(DEFAULT_DISTRICT_OF_RESIDENCE);
        assertThat(testDelegate.getDepartureDate()).isEqualTo(DEFAULT_DEPARTURE_DATE);
        assertThat(testDelegate.getDeparturePlace()).isEqualTo(DEFAULT_DEPARTURE_PLACE);
        assertThat(testDelegate.getArrivalDate()).isEqualTo(DEFAULT_ARRIVAL_DATE);
        assertThat(testDelegate.getArrivalPlace()).isEqualTo(DEFAULT_ARRIVAL_PLACE);
        assertThat(testDelegate.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDelegate.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testDelegate.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testDelegate.getIsTaxReceipt()).isEqualTo(DEFAULT_IS_TAX_RECEIPT);
        assertThat(testDelegate.getProfilePhotoContentType()).isEqualTo(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE);
        assertThat(testDelegate.getPassportImageContentType()).isEqualTo(DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE);
        assertThat(testDelegate.getConferenceName()).isEqualTo(DEFAULT_CONFERENCE_NAME);
        assertThat(testDelegate.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDelegate.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDelegate.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDelegate.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDelegate.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDelegate.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDelegate.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testDelegate.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDelegate.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDelegate.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void createDelegateWithExistingId() throws Exception {
        // Create the Delegate with an existing ID
        delegate.setId(1L);
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        int databaseSizeBeforeCreate = delegateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDelegateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(delegateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDelegates() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(delegate.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].countryOfResidence").value(hasItem(DEFAULT_COUNTRY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].citizenship").value(hasItem(DEFAULT_CITIZENSHIP)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactRelationship").value(hasItem(DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO.intValue())))
            .andExpect(jsonPath("$.[*].countryCodeOne").value(hasItem(DEFAULT_COUNTRY_CODE_ONE)))
            .andExpect(jsonPath("$.[*].countryCodeTwo").value(hasItem(DEFAULT_COUNTRY_CODE_TWO)))
            .andExpect(jsonPath("$.[*].nationalityOne").value(hasItem(DEFAULT_NATIONALITY_ONE)))
            .andExpect(jsonPath("$.[*].countryOfBirth").value(hasItem(DEFAULT_COUNTRY_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].cityOfResidence").value(hasItem(DEFAULT_CITY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].stateOfResidence").value(hasItem(DEFAULT_STATE_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].districtOfResidence").value(hasItem(DEFAULT_DISTRICT_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].departureDate").value(hasItem(DEFAULT_DEPARTURE_DATE.toString())))
            .andExpect(jsonPath("$.[*].departurePlace").value(hasItem(DEFAULT_DEPARTURE_PLACE)))
            .andExpect(jsonPath("$.[*].arrivalDate").value(hasItem(DEFAULT_ARRIVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].arrivalPlace").value(hasItem(DEFAULT_ARRIVAL_PLACE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].isTaxReceipt").value(hasItem(DEFAULT_IS_TAX_RECEIPT.booleanValue())))
            .andExpect(jsonPath("$.[*].profilePhotoContentType").value(hasItem(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].passportImageContentType").value(hasItem(DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].conferenceName").value(hasItem(DEFAULT_CONFERENCE_NAME)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())));
    }

    @Test
    @Transactional
    void getDelegate() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get the delegate
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL_ID, delegate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(delegate.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.countryOfResidence").value(DEFAULT_COUNTRY_OF_RESIDENCE))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.citizenship").value(DEFAULT_CITIZENSHIP))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.intValue()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME))
            .andExpect(jsonPath("$.emergencyContactRelationship").value(DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP))
            .andExpect(jsonPath("$.emergencyContactNo").value(DEFAULT_EMERGENCY_CONTACT_NO.intValue()))
            .andExpect(jsonPath("$.countryCodeOne").value(DEFAULT_COUNTRY_CODE_ONE))
            .andExpect(jsonPath("$.countryCodeTwo").value(DEFAULT_COUNTRY_CODE_TWO))
            .andExpect(jsonPath("$.nationalityOne").value(DEFAULT_NATIONALITY_ONE))
            .andExpect(jsonPath("$.countryOfBirth").value(DEFAULT_COUNTRY_OF_BIRTH))
            .andExpect(jsonPath("$.cityOfResidence").value(DEFAULT_CITY_OF_RESIDENCE))
            .andExpect(jsonPath("$.stateOfResidence").value(DEFAULT_STATE_OF_RESIDENCE))
            .andExpect(jsonPath("$.districtOfResidence").value(DEFAULT_DISTRICT_OF_RESIDENCE))
            .andExpect(jsonPath("$.departureDate").value(DEFAULT_DEPARTURE_DATE.toString()))
            .andExpect(jsonPath("$.departurePlace").value(DEFAULT_DEPARTURE_PLACE))
            .andExpect(jsonPath("$.arrivalDate").value(DEFAULT_ARRIVAL_DATE.toString()))
            .andExpect(jsonPath("$.arrivalPlace").value(DEFAULT_ARRIVAL_PLACE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.isTaxReceipt").value(DEFAULT_IS_TAX_RECEIPT.booleanValue()))
            .andExpect(jsonPath("$.profilePhotoContentType").value(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.passportImageContentType").value(DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.conferenceName").value(DEFAULT_CONFERENCE_NAME))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
            .andExpect(jsonPath("$.freeField5").value(DEFAULT_FREE_FIELD_5.intValue()))
            .andExpect(jsonPath("$.freeField6").value(DEFAULT_FREE_FIELD_6.intValue()))
            .andExpect(jsonPath("$.freeField7").value(DEFAULT_FREE_FIELD_7.toString()))
            .andExpect(jsonPath("$.freeField8").value(DEFAULT_FREE_FIELD_8.toString()))
            .andExpect(jsonPath("$.freeField9").value(DEFAULT_FREE_FIELD_9.booleanValue()))
            .andExpect(jsonPath("$.freeField10").value(DEFAULT_FREE_FIELD_10.booleanValue()));
    }

    @Test
    @Transactional
    void getDelegatesByIdFiltering() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        Long id = delegate.getId();

        defaultDelegateShouldBeFound("id.equals=" + id);
        defaultDelegateShouldNotBeFound("id.notEquals=" + id);

        defaultDelegateShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDelegateShouldNotBeFound("id.greaterThan=" + id);

        defaultDelegateShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDelegateShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDelegatesByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where title equals to DEFAULT_TITLE
        defaultDelegateShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the delegateList where title equals to UPDATED_TITLE
        defaultDelegateShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllDelegatesByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultDelegateShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the delegateList where title equals to UPDATED_TITLE
        defaultDelegateShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllDelegatesByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where title is not null
        defaultDelegateShouldBeFound("title.specified=true");

        // Get all the delegateList where title is null
        defaultDelegateShouldNotBeFound("title.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByTitleContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where title contains DEFAULT_TITLE
        defaultDelegateShouldBeFound("title.contains=" + DEFAULT_TITLE);

        // Get all the delegateList where title contains UPDATED_TITLE
        defaultDelegateShouldNotBeFound("title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllDelegatesByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where title does not contain DEFAULT_TITLE
        defaultDelegateShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

        // Get all the delegateList where title does not contain UPDATED_TITLE
        defaultDelegateShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllDelegatesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where fullName equals to DEFAULT_FULL_NAME
        defaultDelegateShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the delegateList where fullName equals to UPDATED_FULL_NAME
        defaultDelegateShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultDelegateShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the delegateList where fullName equals to UPDATED_FULL_NAME
        defaultDelegateShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where fullName is not null
        defaultDelegateShouldBeFound("fullName.specified=true");

        // Get all the delegateList where fullName is null
        defaultDelegateShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFullNameContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where fullName contains DEFAULT_FULL_NAME
        defaultDelegateShouldBeFound("fullName.contains=" + DEFAULT_FULL_NAME);

        // Get all the delegateList where fullName contains UPDATED_FULL_NAME
        defaultDelegateShouldNotBeFound("fullName.contains=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByFullNameNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where fullName does not contain DEFAULT_FULL_NAME
        defaultDelegateShouldNotBeFound("fullName.doesNotContain=" + DEFAULT_FULL_NAME);

        // Get all the delegateList where fullName does not contain UPDATED_FULL_NAME
        defaultDelegateShouldBeFound("fullName.doesNotContain=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where gender equals to DEFAULT_GENDER
        defaultDelegateShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the delegateList where gender equals to UPDATED_GENDER
        defaultDelegateShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllDelegatesByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultDelegateShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the delegateList where gender equals to UPDATED_GENDER
        defaultDelegateShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllDelegatesByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where gender is not null
        defaultDelegateShouldBeFound("gender.specified=true");

        // Get all the delegateList where gender is null
        defaultDelegateShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationality equals to DEFAULT_NATIONALITY
        defaultDelegateShouldBeFound("nationality.equals=" + DEFAULT_NATIONALITY);

        // Get all the delegateList where nationality equals to UPDATED_NATIONALITY
        defaultDelegateShouldNotBeFound("nationality.equals=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationality in DEFAULT_NATIONALITY or UPDATED_NATIONALITY
        defaultDelegateShouldBeFound("nationality.in=" + DEFAULT_NATIONALITY + "," + UPDATED_NATIONALITY);

        // Get all the delegateList where nationality equals to UPDATED_NATIONALITY
        defaultDelegateShouldNotBeFound("nationality.in=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationality is not null
        defaultDelegateShouldBeFound("nationality.specified=true");

        // Get all the delegateList where nationality is null
        defaultDelegateShouldNotBeFound("nationality.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationality contains DEFAULT_NATIONALITY
        defaultDelegateShouldBeFound("nationality.contains=" + DEFAULT_NATIONALITY);

        // Get all the delegateList where nationality contains UPDATED_NATIONALITY
        defaultDelegateShouldNotBeFound("nationality.contains=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationality does not contain DEFAULT_NATIONALITY
        defaultDelegateShouldNotBeFound("nationality.doesNotContain=" + DEFAULT_NATIONALITY);

        // Get all the delegateList where nationality does not contain UPDATED_NATIONALITY
        defaultDelegateShouldBeFound("nationality.doesNotContain=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfResidence equals to DEFAULT_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldBeFound("countryOfResidence.equals=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the delegateList where countryOfResidence equals to UPDATED_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("countryOfResidence.equals=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfResidence in DEFAULT_COUNTRY_OF_RESIDENCE or UPDATED_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldBeFound("countryOfResidence.in=" + DEFAULT_COUNTRY_OF_RESIDENCE + "," + UPDATED_COUNTRY_OF_RESIDENCE);

        // Get all the delegateList where countryOfResidence equals to UPDATED_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("countryOfResidence.in=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfResidence is not null
        defaultDelegateShouldBeFound("countryOfResidence.specified=true");

        // Get all the delegateList where countryOfResidence is null
        defaultDelegateShouldNotBeFound("countryOfResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfResidenceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfResidence contains DEFAULT_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldBeFound("countryOfResidence.contains=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the delegateList where countryOfResidence contains UPDATED_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("countryOfResidence.contains=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfResidence does not contain DEFAULT_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("countryOfResidence.doesNotContain=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the delegateList where countryOfResidence does not contain UPDATED_COUNTRY_OF_RESIDENCE
        defaultDelegateShouldBeFound("countryOfResidence.doesNotContain=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth equals to DEFAULT_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.equals=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.equals=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth in DEFAULT_DATE_OF_BIRTH or UPDATED_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.in=" + DEFAULT_DATE_OF_BIRTH + "," + UPDATED_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.in=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth is not null
        defaultDelegateShouldBeFound("dateOfBirth.specified=true");

        // Get all the delegateList where dateOfBirth is null
        defaultDelegateShouldNotBeFound("dateOfBirth.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth is greater than or equal to DEFAULT_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.greaterThanOrEqual=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth is greater than or equal to UPDATED_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.greaterThanOrEqual=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth is less than or equal to DEFAULT_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.lessThanOrEqual=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth is less than or equal to SMALLER_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.lessThanOrEqual=" + SMALLER_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsLessThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth is less than DEFAULT_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.lessThan=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth is less than UPDATED_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.lessThan=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByDateOfBirthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where dateOfBirth is greater than DEFAULT_DATE_OF_BIRTH
        defaultDelegateShouldNotBeFound("dateOfBirth.greaterThan=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the delegateList where dateOfBirth is greater than SMALLER_DATE_OF_BIRTH
        defaultDelegateShouldBeFound("dateOfBirth.greaterThan=" + SMALLER_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByCitizenshipIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where citizenship equals to DEFAULT_CITIZENSHIP
        defaultDelegateShouldBeFound("citizenship.equals=" + DEFAULT_CITIZENSHIP);

        // Get all the delegateList where citizenship equals to UPDATED_CITIZENSHIP
        defaultDelegateShouldNotBeFound("citizenship.equals=" + UPDATED_CITIZENSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByCitizenshipIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where citizenship in DEFAULT_CITIZENSHIP or UPDATED_CITIZENSHIP
        defaultDelegateShouldBeFound("citizenship.in=" + DEFAULT_CITIZENSHIP + "," + UPDATED_CITIZENSHIP);

        // Get all the delegateList where citizenship equals to UPDATED_CITIZENSHIP
        defaultDelegateShouldNotBeFound("citizenship.in=" + UPDATED_CITIZENSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByCitizenshipIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where citizenship is not null
        defaultDelegateShouldBeFound("citizenship.specified=true");

        // Get all the delegateList where citizenship is null
        defaultDelegateShouldNotBeFound("citizenship.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCitizenshipContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where citizenship contains DEFAULT_CITIZENSHIP
        defaultDelegateShouldBeFound("citizenship.contains=" + DEFAULT_CITIZENSHIP);

        // Get all the delegateList where citizenship contains UPDATED_CITIZENSHIP
        defaultDelegateShouldNotBeFound("citizenship.contains=" + UPDATED_CITIZENSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByCitizenshipNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where citizenship does not contain DEFAULT_CITIZENSHIP
        defaultDelegateShouldNotBeFound("citizenship.doesNotContain=" + DEFAULT_CITIZENSHIP);

        // Get all the delegateList where citizenship does not contain UPDATED_CITIZENSHIP
        defaultDelegateShouldBeFound("citizenship.doesNotContain=" + UPDATED_CITIZENSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo equals to DEFAULT_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.equals=" + DEFAULT_MOBILE_NO);

        // Get all the delegateList where mobileNo equals to UPDATED_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.equals=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo in DEFAULT_MOBILE_NO or UPDATED_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.in=" + DEFAULT_MOBILE_NO + "," + UPDATED_MOBILE_NO);

        // Get all the delegateList where mobileNo equals to UPDATED_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.in=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo is not null
        defaultDelegateShouldBeFound("mobileNo.specified=true");

        // Get all the delegateList where mobileNo is null
        defaultDelegateShouldNotBeFound("mobileNo.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo is greater than or equal to DEFAULT_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.greaterThanOrEqual=" + DEFAULT_MOBILE_NO);

        // Get all the delegateList where mobileNo is greater than or equal to UPDATED_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.greaterThanOrEqual=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo is less than or equal to DEFAULT_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.lessThanOrEqual=" + DEFAULT_MOBILE_NO);

        // Get all the delegateList where mobileNo is less than or equal to SMALLER_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.lessThanOrEqual=" + SMALLER_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsLessThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo is less than DEFAULT_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.lessThan=" + DEFAULT_MOBILE_NO);

        // Get all the delegateList where mobileNo is less than UPDATED_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.lessThan=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByMobileNoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where mobileNo is greater than DEFAULT_MOBILE_NO
        defaultDelegateShouldNotBeFound("mobileNo.greaterThan=" + DEFAULT_MOBILE_NO);

        // Get all the delegateList where mobileNo is greater than SMALLER_MOBILE_NO
        defaultDelegateShouldBeFound("mobileNo.greaterThan=" + SMALLER_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmailIdIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emailId equals to DEFAULT_EMAIL_ID
        defaultDelegateShouldBeFound("emailId.equals=" + DEFAULT_EMAIL_ID);

        // Get all the delegateList where emailId equals to UPDATED_EMAIL_ID
        defaultDelegateShouldNotBeFound("emailId.equals=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmailIdIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emailId in DEFAULT_EMAIL_ID or UPDATED_EMAIL_ID
        defaultDelegateShouldBeFound("emailId.in=" + DEFAULT_EMAIL_ID + "," + UPDATED_EMAIL_ID);

        // Get all the delegateList where emailId equals to UPDATED_EMAIL_ID
        defaultDelegateShouldNotBeFound("emailId.in=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmailIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emailId is not null
        defaultDelegateShouldBeFound("emailId.specified=true");

        // Get all the delegateList where emailId is null
        defaultDelegateShouldNotBeFound("emailId.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByEmailIdContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emailId contains DEFAULT_EMAIL_ID
        defaultDelegateShouldBeFound("emailId.contains=" + DEFAULT_EMAIL_ID);

        // Get all the delegateList where emailId contains UPDATED_EMAIL_ID
        defaultDelegateShouldNotBeFound("emailId.contains=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmailIdNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emailId does not contain DEFAULT_EMAIL_ID
        defaultDelegateShouldNotBeFound("emailId.doesNotContain=" + DEFAULT_EMAIL_ID);

        // Get all the delegateList where emailId does not contain UPDATED_EMAIL_ID
        defaultDelegateShouldBeFound("emailId.doesNotContain=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNameIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactName equals to DEFAULT_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldBeFound("emergencyContactName.equals=" + DEFAULT_EMERGENCY_CONTACT_NAME);

        // Get all the delegateList where emergencyContactName equals to UPDATED_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldNotBeFound("emergencyContactName.equals=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNameIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactName in DEFAULT_EMERGENCY_CONTACT_NAME or UPDATED_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldBeFound("emergencyContactName.in=" + DEFAULT_EMERGENCY_CONTACT_NAME + "," + UPDATED_EMERGENCY_CONTACT_NAME);

        // Get all the delegateList where emergencyContactName equals to UPDATED_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldNotBeFound("emergencyContactName.in=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactName is not null
        defaultDelegateShouldBeFound("emergencyContactName.specified=true");

        // Get all the delegateList where emergencyContactName is null
        defaultDelegateShouldNotBeFound("emergencyContactName.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNameContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactName contains DEFAULT_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldBeFound("emergencyContactName.contains=" + DEFAULT_EMERGENCY_CONTACT_NAME);

        // Get all the delegateList where emergencyContactName contains UPDATED_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldNotBeFound("emergencyContactName.contains=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNameNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactName does not contain DEFAULT_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldNotBeFound("emergencyContactName.doesNotContain=" + DEFAULT_EMERGENCY_CONTACT_NAME);

        // Get all the delegateList where emergencyContactName does not contain UPDATED_EMERGENCY_CONTACT_NAME
        defaultDelegateShouldBeFound("emergencyContactName.doesNotContain=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactRelationshipIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactRelationship equals to DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldBeFound("emergencyContactRelationship.equals=" + DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP);

        // Get all the delegateList where emergencyContactRelationship equals to UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldNotBeFound("emergencyContactRelationship.equals=" + UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactRelationshipIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactRelationship in DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP or UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldBeFound(
            "emergencyContactRelationship.in=" + DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP + "," + UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        );

        // Get all the delegateList where emergencyContactRelationship equals to UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldNotBeFound("emergencyContactRelationship.in=" + UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactRelationshipIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactRelationship is not null
        defaultDelegateShouldBeFound("emergencyContactRelationship.specified=true");

        // Get all the delegateList where emergencyContactRelationship is null
        defaultDelegateShouldNotBeFound("emergencyContactRelationship.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactRelationshipContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactRelationship contains DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldBeFound("emergencyContactRelationship.contains=" + DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP);

        // Get all the delegateList where emergencyContactRelationship contains UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldNotBeFound("emergencyContactRelationship.contains=" + UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactRelationshipNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactRelationship does not contain DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldNotBeFound("emergencyContactRelationship.doesNotContain=" + DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP);

        // Get all the delegateList where emergencyContactRelationship does not contain UPDATED_EMERGENCY_CONTACT_RELATIONSHIP
        defaultDelegateShouldBeFound("emergencyContactRelationship.doesNotContain=" + UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo equals to DEFAULT_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.equals=" + DEFAULT_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo equals to UPDATED_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.equals=" + UPDATED_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo in DEFAULT_EMERGENCY_CONTACT_NO or UPDATED_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.in=" + DEFAULT_EMERGENCY_CONTACT_NO + "," + UPDATED_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo equals to UPDATED_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.in=" + UPDATED_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo is not null
        defaultDelegateShouldBeFound("emergencyContactNo.specified=true");

        // Get all the delegateList where emergencyContactNo is null
        defaultDelegateShouldNotBeFound("emergencyContactNo.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo is greater than or equal to DEFAULT_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.greaterThanOrEqual=" + DEFAULT_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo is greater than or equal to UPDATED_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.greaterThanOrEqual=" + UPDATED_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo is less than or equal to DEFAULT_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.lessThanOrEqual=" + DEFAULT_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo is less than or equal to SMALLER_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.lessThanOrEqual=" + SMALLER_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsLessThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo is less than DEFAULT_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.lessThan=" + DEFAULT_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo is less than UPDATED_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.lessThan=" + UPDATED_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByEmergencyContactNoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where emergencyContactNo is greater than DEFAULT_EMERGENCY_CONTACT_NO
        defaultDelegateShouldNotBeFound("emergencyContactNo.greaterThan=" + DEFAULT_EMERGENCY_CONTACT_NO);

        // Get all the delegateList where emergencyContactNo is greater than SMALLER_EMERGENCY_CONTACT_NO
        defaultDelegateShouldBeFound("emergencyContactNo.greaterThan=" + SMALLER_EMERGENCY_CONTACT_NO);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeOneIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeOne equals to DEFAULT_COUNTRY_CODE_ONE
        defaultDelegateShouldBeFound("countryCodeOne.equals=" + DEFAULT_COUNTRY_CODE_ONE);

        // Get all the delegateList where countryCodeOne equals to UPDATED_COUNTRY_CODE_ONE
        defaultDelegateShouldNotBeFound("countryCodeOne.equals=" + UPDATED_COUNTRY_CODE_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeOneIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeOne in DEFAULT_COUNTRY_CODE_ONE or UPDATED_COUNTRY_CODE_ONE
        defaultDelegateShouldBeFound("countryCodeOne.in=" + DEFAULT_COUNTRY_CODE_ONE + "," + UPDATED_COUNTRY_CODE_ONE);

        // Get all the delegateList where countryCodeOne equals to UPDATED_COUNTRY_CODE_ONE
        defaultDelegateShouldNotBeFound("countryCodeOne.in=" + UPDATED_COUNTRY_CODE_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeOneIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeOne is not null
        defaultDelegateShouldBeFound("countryCodeOne.specified=true");

        // Get all the delegateList where countryCodeOne is null
        defaultDelegateShouldNotBeFound("countryCodeOne.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeOneContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeOne contains DEFAULT_COUNTRY_CODE_ONE
        defaultDelegateShouldBeFound("countryCodeOne.contains=" + DEFAULT_COUNTRY_CODE_ONE);

        // Get all the delegateList where countryCodeOne contains UPDATED_COUNTRY_CODE_ONE
        defaultDelegateShouldNotBeFound("countryCodeOne.contains=" + UPDATED_COUNTRY_CODE_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeOneNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeOne does not contain DEFAULT_COUNTRY_CODE_ONE
        defaultDelegateShouldNotBeFound("countryCodeOne.doesNotContain=" + DEFAULT_COUNTRY_CODE_ONE);

        // Get all the delegateList where countryCodeOne does not contain UPDATED_COUNTRY_CODE_ONE
        defaultDelegateShouldBeFound("countryCodeOne.doesNotContain=" + UPDATED_COUNTRY_CODE_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeTwoIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeTwo equals to DEFAULT_COUNTRY_CODE_TWO
        defaultDelegateShouldBeFound("countryCodeTwo.equals=" + DEFAULT_COUNTRY_CODE_TWO);

        // Get all the delegateList where countryCodeTwo equals to UPDATED_COUNTRY_CODE_TWO
        defaultDelegateShouldNotBeFound("countryCodeTwo.equals=" + UPDATED_COUNTRY_CODE_TWO);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeTwoIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeTwo in DEFAULT_COUNTRY_CODE_TWO or UPDATED_COUNTRY_CODE_TWO
        defaultDelegateShouldBeFound("countryCodeTwo.in=" + DEFAULT_COUNTRY_CODE_TWO + "," + UPDATED_COUNTRY_CODE_TWO);

        // Get all the delegateList where countryCodeTwo equals to UPDATED_COUNTRY_CODE_TWO
        defaultDelegateShouldNotBeFound("countryCodeTwo.in=" + UPDATED_COUNTRY_CODE_TWO);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeTwoIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeTwo is not null
        defaultDelegateShouldBeFound("countryCodeTwo.specified=true");

        // Get all the delegateList where countryCodeTwo is null
        defaultDelegateShouldNotBeFound("countryCodeTwo.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeTwoContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeTwo contains DEFAULT_COUNTRY_CODE_TWO
        defaultDelegateShouldBeFound("countryCodeTwo.contains=" + DEFAULT_COUNTRY_CODE_TWO);

        // Get all the delegateList where countryCodeTwo contains UPDATED_COUNTRY_CODE_TWO
        defaultDelegateShouldNotBeFound("countryCodeTwo.contains=" + UPDATED_COUNTRY_CODE_TWO);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryCodeTwoNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryCodeTwo does not contain DEFAULT_COUNTRY_CODE_TWO
        defaultDelegateShouldNotBeFound("countryCodeTwo.doesNotContain=" + DEFAULT_COUNTRY_CODE_TWO);

        // Get all the delegateList where countryCodeTwo does not contain UPDATED_COUNTRY_CODE_TWO
        defaultDelegateShouldBeFound("countryCodeTwo.doesNotContain=" + UPDATED_COUNTRY_CODE_TWO);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityOneIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationalityOne equals to DEFAULT_NATIONALITY_ONE
        defaultDelegateShouldBeFound("nationalityOne.equals=" + DEFAULT_NATIONALITY_ONE);

        // Get all the delegateList where nationalityOne equals to UPDATED_NATIONALITY_ONE
        defaultDelegateShouldNotBeFound("nationalityOne.equals=" + UPDATED_NATIONALITY_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityOneIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationalityOne in DEFAULT_NATIONALITY_ONE or UPDATED_NATIONALITY_ONE
        defaultDelegateShouldBeFound("nationalityOne.in=" + DEFAULT_NATIONALITY_ONE + "," + UPDATED_NATIONALITY_ONE);

        // Get all the delegateList where nationalityOne equals to UPDATED_NATIONALITY_ONE
        defaultDelegateShouldNotBeFound("nationalityOne.in=" + UPDATED_NATIONALITY_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityOneIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationalityOne is not null
        defaultDelegateShouldBeFound("nationalityOne.specified=true");

        // Get all the delegateList where nationalityOne is null
        defaultDelegateShouldNotBeFound("nationalityOne.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityOneContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationalityOne contains DEFAULT_NATIONALITY_ONE
        defaultDelegateShouldBeFound("nationalityOne.contains=" + DEFAULT_NATIONALITY_ONE);

        // Get all the delegateList where nationalityOne contains UPDATED_NATIONALITY_ONE
        defaultDelegateShouldNotBeFound("nationalityOne.contains=" + UPDATED_NATIONALITY_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByNationalityOneNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where nationalityOne does not contain DEFAULT_NATIONALITY_ONE
        defaultDelegateShouldNotBeFound("nationalityOne.doesNotContain=" + DEFAULT_NATIONALITY_ONE);

        // Get all the delegateList where nationalityOne does not contain UPDATED_NATIONALITY_ONE
        defaultDelegateShouldBeFound("nationalityOne.doesNotContain=" + UPDATED_NATIONALITY_ONE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfBirthIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfBirth equals to DEFAULT_COUNTRY_OF_BIRTH
        defaultDelegateShouldBeFound("countryOfBirth.equals=" + DEFAULT_COUNTRY_OF_BIRTH);

        // Get all the delegateList where countryOfBirth equals to UPDATED_COUNTRY_OF_BIRTH
        defaultDelegateShouldNotBeFound("countryOfBirth.equals=" + UPDATED_COUNTRY_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfBirthIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfBirth in DEFAULT_COUNTRY_OF_BIRTH or UPDATED_COUNTRY_OF_BIRTH
        defaultDelegateShouldBeFound("countryOfBirth.in=" + DEFAULT_COUNTRY_OF_BIRTH + "," + UPDATED_COUNTRY_OF_BIRTH);

        // Get all the delegateList where countryOfBirth equals to UPDATED_COUNTRY_OF_BIRTH
        defaultDelegateShouldNotBeFound("countryOfBirth.in=" + UPDATED_COUNTRY_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfBirthIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfBirth is not null
        defaultDelegateShouldBeFound("countryOfBirth.specified=true");

        // Get all the delegateList where countryOfBirth is null
        defaultDelegateShouldNotBeFound("countryOfBirth.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfBirthContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfBirth contains DEFAULT_COUNTRY_OF_BIRTH
        defaultDelegateShouldBeFound("countryOfBirth.contains=" + DEFAULT_COUNTRY_OF_BIRTH);

        // Get all the delegateList where countryOfBirth contains UPDATED_COUNTRY_OF_BIRTH
        defaultDelegateShouldNotBeFound("countryOfBirth.contains=" + UPDATED_COUNTRY_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByCountryOfBirthNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where countryOfBirth does not contain DEFAULT_COUNTRY_OF_BIRTH
        defaultDelegateShouldNotBeFound("countryOfBirth.doesNotContain=" + DEFAULT_COUNTRY_OF_BIRTH);

        // Get all the delegateList where countryOfBirth does not contain UPDATED_COUNTRY_OF_BIRTH
        defaultDelegateShouldBeFound("countryOfBirth.doesNotContain=" + UPDATED_COUNTRY_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllDelegatesByCityOfResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where cityOfResidence equals to DEFAULT_CITY_OF_RESIDENCE
        defaultDelegateShouldBeFound("cityOfResidence.equals=" + DEFAULT_CITY_OF_RESIDENCE);

        // Get all the delegateList where cityOfResidence equals to UPDATED_CITY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("cityOfResidence.equals=" + UPDATED_CITY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCityOfResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where cityOfResidence in DEFAULT_CITY_OF_RESIDENCE or UPDATED_CITY_OF_RESIDENCE
        defaultDelegateShouldBeFound("cityOfResidence.in=" + DEFAULT_CITY_OF_RESIDENCE + "," + UPDATED_CITY_OF_RESIDENCE);

        // Get all the delegateList where cityOfResidence equals to UPDATED_CITY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("cityOfResidence.in=" + UPDATED_CITY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCityOfResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where cityOfResidence is not null
        defaultDelegateShouldBeFound("cityOfResidence.specified=true");

        // Get all the delegateList where cityOfResidence is null
        defaultDelegateShouldNotBeFound("cityOfResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByCityOfResidenceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where cityOfResidence contains DEFAULT_CITY_OF_RESIDENCE
        defaultDelegateShouldBeFound("cityOfResidence.contains=" + DEFAULT_CITY_OF_RESIDENCE);

        // Get all the delegateList where cityOfResidence contains UPDATED_CITY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("cityOfResidence.contains=" + UPDATED_CITY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByCityOfResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where cityOfResidence does not contain DEFAULT_CITY_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("cityOfResidence.doesNotContain=" + DEFAULT_CITY_OF_RESIDENCE);

        // Get all the delegateList where cityOfResidence does not contain UPDATED_CITY_OF_RESIDENCE
        defaultDelegateShouldBeFound("cityOfResidence.doesNotContain=" + UPDATED_CITY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByStateOfResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where stateOfResidence equals to DEFAULT_STATE_OF_RESIDENCE
        defaultDelegateShouldBeFound("stateOfResidence.equals=" + DEFAULT_STATE_OF_RESIDENCE);

        // Get all the delegateList where stateOfResidence equals to UPDATED_STATE_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("stateOfResidence.equals=" + UPDATED_STATE_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByStateOfResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where stateOfResidence in DEFAULT_STATE_OF_RESIDENCE or UPDATED_STATE_OF_RESIDENCE
        defaultDelegateShouldBeFound("stateOfResidence.in=" + DEFAULT_STATE_OF_RESIDENCE + "," + UPDATED_STATE_OF_RESIDENCE);

        // Get all the delegateList where stateOfResidence equals to UPDATED_STATE_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("stateOfResidence.in=" + UPDATED_STATE_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByStateOfResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where stateOfResidence is not null
        defaultDelegateShouldBeFound("stateOfResidence.specified=true");

        // Get all the delegateList where stateOfResidence is null
        defaultDelegateShouldNotBeFound("stateOfResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByStateOfResidenceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where stateOfResidence contains DEFAULT_STATE_OF_RESIDENCE
        defaultDelegateShouldBeFound("stateOfResidence.contains=" + DEFAULT_STATE_OF_RESIDENCE);

        // Get all the delegateList where stateOfResidence contains UPDATED_STATE_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("stateOfResidence.contains=" + UPDATED_STATE_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByStateOfResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where stateOfResidence does not contain DEFAULT_STATE_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("stateOfResidence.doesNotContain=" + DEFAULT_STATE_OF_RESIDENCE);

        // Get all the delegateList where stateOfResidence does not contain UPDATED_STATE_OF_RESIDENCE
        defaultDelegateShouldBeFound("stateOfResidence.doesNotContain=" + UPDATED_STATE_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDistrictOfResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where districtOfResidence equals to DEFAULT_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldBeFound("districtOfResidence.equals=" + DEFAULT_DISTRICT_OF_RESIDENCE);

        // Get all the delegateList where districtOfResidence equals to UPDATED_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("districtOfResidence.equals=" + UPDATED_DISTRICT_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDistrictOfResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where districtOfResidence in DEFAULT_DISTRICT_OF_RESIDENCE or UPDATED_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldBeFound("districtOfResidence.in=" + DEFAULT_DISTRICT_OF_RESIDENCE + "," + UPDATED_DISTRICT_OF_RESIDENCE);

        // Get all the delegateList where districtOfResidence equals to UPDATED_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("districtOfResidence.in=" + UPDATED_DISTRICT_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDistrictOfResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where districtOfResidence is not null
        defaultDelegateShouldBeFound("districtOfResidence.specified=true");

        // Get all the delegateList where districtOfResidence is null
        defaultDelegateShouldNotBeFound("districtOfResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByDistrictOfResidenceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where districtOfResidence contains DEFAULT_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldBeFound("districtOfResidence.contains=" + DEFAULT_DISTRICT_OF_RESIDENCE);

        // Get all the delegateList where districtOfResidence contains UPDATED_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("districtOfResidence.contains=" + UPDATED_DISTRICT_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDistrictOfResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where districtOfResidence does not contain DEFAULT_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldNotBeFound("districtOfResidence.doesNotContain=" + DEFAULT_DISTRICT_OF_RESIDENCE);

        // Get all the delegateList where districtOfResidence does not contain UPDATED_DISTRICT_OF_RESIDENCE
        defaultDelegateShouldBeFound("districtOfResidence.doesNotContain=" + UPDATED_DISTRICT_OF_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDepartureDateIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departureDate equals to DEFAULT_DEPARTURE_DATE
        defaultDelegateShouldBeFound("departureDate.equals=" + DEFAULT_DEPARTURE_DATE);

        // Get all the delegateList where departureDate equals to UPDATED_DEPARTURE_DATE
        defaultDelegateShouldNotBeFound("departureDate.equals=" + UPDATED_DEPARTURE_DATE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDepartureDateIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departureDate in DEFAULT_DEPARTURE_DATE or UPDATED_DEPARTURE_DATE
        defaultDelegateShouldBeFound("departureDate.in=" + DEFAULT_DEPARTURE_DATE + "," + UPDATED_DEPARTURE_DATE);

        // Get all the delegateList where departureDate equals to UPDATED_DEPARTURE_DATE
        defaultDelegateShouldNotBeFound("departureDate.in=" + UPDATED_DEPARTURE_DATE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDepartureDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departureDate is not null
        defaultDelegateShouldBeFound("departureDate.specified=true");

        // Get all the delegateList where departureDate is null
        defaultDelegateShouldNotBeFound("departureDate.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByDeparturePlaceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departurePlace equals to DEFAULT_DEPARTURE_PLACE
        defaultDelegateShouldBeFound("departurePlace.equals=" + DEFAULT_DEPARTURE_PLACE);

        // Get all the delegateList where departurePlace equals to UPDATED_DEPARTURE_PLACE
        defaultDelegateShouldNotBeFound("departurePlace.equals=" + UPDATED_DEPARTURE_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDeparturePlaceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departurePlace in DEFAULT_DEPARTURE_PLACE or UPDATED_DEPARTURE_PLACE
        defaultDelegateShouldBeFound("departurePlace.in=" + DEFAULT_DEPARTURE_PLACE + "," + UPDATED_DEPARTURE_PLACE);

        // Get all the delegateList where departurePlace equals to UPDATED_DEPARTURE_PLACE
        defaultDelegateShouldNotBeFound("departurePlace.in=" + UPDATED_DEPARTURE_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDeparturePlaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departurePlace is not null
        defaultDelegateShouldBeFound("departurePlace.specified=true");

        // Get all the delegateList where departurePlace is null
        defaultDelegateShouldNotBeFound("departurePlace.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByDeparturePlaceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departurePlace contains DEFAULT_DEPARTURE_PLACE
        defaultDelegateShouldBeFound("departurePlace.contains=" + DEFAULT_DEPARTURE_PLACE);

        // Get all the delegateList where departurePlace contains UPDATED_DEPARTURE_PLACE
        defaultDelegateShouldNotBeFound("departurePlace.contains=" + UPDATED_DEPARTURE_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByDeparturePlaceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where departurePlace does not contain DEFAULT_DEPARTURE_PLACE
        defaultDelegateShouldNotBeFound("departurePlace.doesNotContain=" + DEFAULT_DEPARTURE_PLACE);

        // Get all the delegateList where departurePlace does not contain UPDATED_DEPARTURE_PLACE
        defaultDelegateShouldBeFound("departurePlace.doesNotContain=" + UPDATED_DEPARTURE_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalDateIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalDate equals to DEFAULT_ARRIVAL_DATE
        defaultDelegateShouldBeFound("arrivalDate.equals=" + DEFAULT_ARRIVAL_DATE);

        // Get all the delegateList where arrivalDate equals to UPDATED_ARRIVAL_DATE
        defaultDelegateShouldNotBeFound("arrivalDate.equals=" + UPDATED_ARRIVAL_DATE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalDateIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalDate in DEFAULT_ARRIVAL_DATE or UPDATED_ARRIVAL_DATE
        defaultDelegateShouldBeFound("arrivalDate.in=" + DEFAULT_ARRIVAL_DATE + "," + UPDATED_ARRIVAL_DATE);

        // Get all the delegateList where arrivalDate equals to UPDATED_ARRIVAL_DATE
        defaultDelegateShouldNotBeFound("arrivalDate.in=" + UPDATED_ARRIVAL_DATE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalDate is not null
        defaultDelegateShouldBeFound("arrivalDate.specified=true");

        // Get all the delegateList where arrivalDate is null
        defaultDelegateShouldNotBeFound("arrivalDate.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalPlaceIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalPlace equals to DEFAULT_ARRIVAL_PLACE
        defaultDelegateShouldBeFound("arrivalPlace.equals=" + DEFAULT_ARRIVAL_PLACE);

        // Get all the delegateList where arrivalPlace equals to UPDATED_ARRIVAL_PLACE
        defaultDelegateShouldNotBeFound("arrivalPlace.equals=" + UPDATED_ARRIVAL_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalPlaceIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalPlace in DEFAULT_ARRIVAL_PLACE or UPDATED_ARRIVAL_PLACE
        defaultDelegateShouldBeFound("arrivalPlace.in=" + DEFAULT_ARRIVAL_PLACE + "," + UPDATED_ARRIVAL_PLACE);

        // Get all the delegateList where arrivalPlace equals to UPDATED_ARRIVAL_PLACE
        defaultDelegateShouldNotBeFound("arrivalPlace.in=" + UPDATED_ARRIVAL_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalPlaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalPlace is not null
        defaultDelegateShouldBeFound("arrivalPlace.specified=true");

        // Get all the delegateList where arrivalPlace is null
        defaultDelegateShouldNotBeFound("arrivalPlace.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalPlaceContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalPlace contains DEFAULT_ARRIVAL_PLACE
        defaultDelegateShouldBeFound("arrivalPlace.contains=" + DEFAULT_ARRIVAL_PLACE);

        // Get all the delegateList where arrivalPlace contains UPDATED_ARRIVAL_PLACE
        defaultDelegateShouldNotBeFound("arrivalPlace.contains=" + UPDATED_ARRIVAL_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByArrivalPlaceNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where arrivalPlace does not contain DEFAULT_ARRIVAL_PLACE
        defaultDelegateShouldNotBeFound("arrivalPlace.doesNotContain=" + DEFAULT_ARRIVAL_PLACE);

        // Get all the delegateList where arrivalPlace does not contain UPDATED_ARRIVAL_PLACE
        defaultDelegateShouldBeFound("arrivalPlace.doesNotContain=" + UPDATED_ARRIVAL_PLACE);
    }

    @Test
    @Transactional
    void getAllDelegatesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where status equals to DEFAULT_STATUS
        defaultDelegateShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the delegateList where status equals to UPDATED_STATUS
        defaultDelegateShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDelegatesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultDelegateShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the delegateList where status equals to UPDATED_STATUS
        defaultDelegateShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDelegatesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where status is not null
        defaultDelegateShouldBeFound("status.specified=true");

        // Get all the delegateList where status is null
        defaultDelegateShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByStatusContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where status contains DEFAULT_STATUS
        defaultDelegateShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the delegateList where status contains UPDATED_STATUS
        defaultDelegateShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDelegatesByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where status does not contain DEFAULT_STATUS
        defaultDelegateShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the delegateList where status does not contain UPDATED_STATUS
        defaultDelegateShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultDelegateShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the delegateList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultDelegateShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultDelegateShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the delegateList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultDelegateShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModified is not null
        defaultDelegateShouldBeFound("lastModified.specified=true");

        // Get all the delegateList where lastModified is null
        defaultDelegateShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultDelegateShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the delegateList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultDelegateShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultDelegateShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the delegateList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultDelegateShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModifiedBy is not null
        defaultDelegateShouldBeFound("lastModifiedBy.specified=true");

        // Get all the delegateList where lastModifiedBy is null
        defaultDelegateShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultDelegateShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the delegateList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultDelegateShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDelegatesByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultDelegateShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the delegateList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultDelegateShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDelegatesByIsTaxReceiptIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where isTaxReceipt equals to DEFAULT_IS_TAX_RECEIPT
        defaultDelegateShouldBeFound("isTaxReceipt.equals=" + DEFAULT_IS_TAX_RECEIPT);

        // Get all the delegateList where isTaxReceipt equals to UPDATED_IS_TAX_RECEIPT
        defaultDelegateShouldNotBeFound("isTaxReceipt.equals=" + UPDATED_IS_TAX_RECEIPT);
    }

    @Test
    @Transactional
    void getAllDelegatesByIsTaxReceiptIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where isTaxReceipt in DEFAULT_IS_TAX_RECEIPT or UPDATED_IS_TAX_RECEIPT
        defaultDelegateShouldBeFound("isTaxReceipt.in=" + DEFAULT_IS_TAX_RECEIPT + "," + UPDATED_IS_TAX_RECEIPT);

        // Get all the delegateList where isTaxReceipt equals to UPDATED_IS_TAX_RECEIPT
        defaultDelegateShouldNotBeFound("isTaxReceipt.in=" + UPDATED_IS_TAX_RECEIPT);
    }

    @Test
    @Transactional
    void getAllDelegatesByIsTaxReceiptIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where isTaxReceipt is not null
        defaultDelegateShouldBeFound("isTaxReceipt.specified=true");

        // Get all the delegateList where isTaxReceipt is null
        defaultDelegateShouldNotBeFound("isTaxReceipt.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByProfilePhotoContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where profilePhotoContentType equals to DEFAULT_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldBeFound("profilePhotoContentType.equals=" + DEFAULT_PROFILE_PHOTO_CONTENT_TYPE);

        // Get all the delegateList where profilePhotoContentType equals to UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("profilePhotoContentType.equals=" + UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByProfilePhotoContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where profilePhotoContentType in DEFAULT_PROFILE_PHOTO_CONTENT_TYPE or UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldBeFound(
            "profilePhotoContentType.in=" + DEFAULT_PROFILE_PHOTO_CONTENT_TYPE + "," + UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        );

        // Get all the delegateList where profilePhotoContentType equals to UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("profilePhotoContentType.in=" + UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByProfilePhotoContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where profilePhotoContentType is not null
        defaultDelegateShouldBeFound("profilePhotoContentType.specified=true");

        // Get all the delegateList where profilePhotoContentType is null
        defaultDelegateShouldNotBeFound("profilePhotoContentType.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByProfilePhotoContentTypeContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where profilePhotoContentType contains DEFAULT_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldBeFound("profilePhotoContentType.contains=" + DEFAULT_PROFILE_PHOTO_CONTENT_TYPE);

        // Get all the delegateList where profilePhotoContentType contains UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("profilePhotoContentType.contains=" + UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByProfilePhotoContentTypeNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where profilePhotoContentType does not contain DEFAULT_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("profilePhotoContentType.doesNotContain=" + DEFAULT_PROFILE_PHOTO_CONTENT_TYPE);

        // Get all the delegateList where profilePhotoContentType does not contain UPDATED_PROFILE_PHOTO_CONTENT_TYPE
        defaultDelegateShouldBeFound("profilePhotoContentType.doesNotContain=" + UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByPassportImageContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where passportImageContentType equals to DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldBeFound("passportImageContentType.equals=" + DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE);

        // Get all the delegateList where passportImageContentType equals to UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("passportImageContentType.equals=" + UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByPassportImageContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where passportImageContentType in DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE or UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldBeFound(
            "passportImageContentType.in=" + DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE + "," + UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        );

        // Get all the delegateList where passportImageContentType equals to UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("passportImageContentType.in=" + UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByPassportImageContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where passportImageContentType is not null
        defaultDelegateShouldBeFound("passportImageContentType.specified=true");

        // Get all the delegateList where passportImageContentType is null
        defaultDelegateShouldNotBeFound("passportImageContentType.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByPassportImageContentTypeContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where passportImageContentType contains DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldBeFound("passportImageContentType.contains=" + DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE);

        // Get all the delegateList where passportImageContentType contains UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("passportImageContentType.contains=" + UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByPassportImageContentTypeNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where passportImageContentType does not contain DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldNotBeFound("passportImageContentType.doesNotContain=" + DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE);

        // Get all the delegateList where passportImageContentType does not contain UPDATED_PASSPORT_IMAGE_CONTENT_TYPE
        defaultDelegateShouldBeFound("passportImageContentType.doesNotContain=" + UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void getAllDelegatesByConferenceNameIsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where conferenceName equals to DEFAULT_CONFERENCE_NAME
        defaultDelegateShouldBeFound("conferenceName.equals=" + DEFAULT_CONFERENCE_NAME);

        // Get all the delegateList where conferenceName equals to UPDATED_CONFERENCE_NAME
        defaultDelegateShouldNotBeFound("conferenceName.equals=" + UPDATED_CONFERENCE_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByConferenceNameIsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where conferenceName in DEFAULT_CONFERENCE_NAME or UPDATED_CONFERENCE_NAME
        defaultDelegateShouldBeFound("conferenceName.in=" + DEFAULT_CONFERENCE_NAME + "," + UPDATED_CONFERENCE_NAME);

        // Get all the delegateList where conferenceName equals to UPDATED_CONFERENCE_NAME
        defaultDelegateShouldNotBeFound("conferenceName.in=" + UPDATED_CONFERENCE_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByConferenceNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where conferenceName is not null
        defaultDelegateShouldBeFound("conferenceName.specified=true");

        // Get all the delegateList where conferenceName is null
        defaultDelegateShouldNotBeFound("conferenceName.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByConferenceNameContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where conferenceName contains DEFAULT_CONFERENCE_NAME
        defaultDelegateShouldBeFound("conferenceName.contains=" + DEFAULT_CONFERENCE_NAME);

        // Get all the delegateList where conferenceName contains UPDATED_CONFERENCE_NAME
        defaultDelegateShouldNotBeFound("conferenceName.contains=" + UPDATED_CONFERENCE_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByConferenceNameNotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where conferenceName does not contain DEFAULT_CONFERENCE_NAME
        defaultDelegateShouldNotBeFound("conferenceName.doesNotContain=" + DEFAULT_CONFERENCE_NAME);

        // Get all the delegateList where conferenceName does not contain UPDATED_CONFERENCE_NAME
        defaultDelegateShouldBeFound("conferenceName.doesNotContain=" + UPDATED_CONFERENCE_NAME);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultDelegateShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the delegateList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultDelegateShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultDelegateShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the delegateList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultDelegateShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField1 is not null
        defaultDelegateShouldBeFound("freeField1.specified=true");

        // Get all the delegateList where freeField1 is null
        defaultDelegateShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultDelegateShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the delegateList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultDelegateShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultDelegateShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the delegateList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultDelegateShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultDelegateShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the delegateList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultDelegateShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultDelegateShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the delegateList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultDelegateShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField2 is not null
        defaultDelegateShouldBeFound("freeField2.specified=true");

        // Get all the delegateList where freeField2 is null
        defaultDelegateShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultDelegateShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the delegateList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultDelegateShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultDelegateShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the delegateList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultDelegateShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField3IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField3 equals to DEFAULT_FREE_FIELD_3
        defaultDelegateShouldBeFound("freeField3.equals=" + DEFAULT_FREE_FIELD_3);

        // Get all the delegateList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultDelegateShouldNotBeFound("freeField3.equals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField3IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
        defaultDelegateShouldBeFound("freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3);

        // Get all the delegateList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultDelegateShouldNotBeFound("freeField3.in=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField3IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField3 is not null
        defaultDelegateShouldBeFound("freeField3.specified=true");

        // Get all the delegateList where freeField3 is null
        defaultDelegateShouldNotBeFound("freeField3.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField3ContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField3 contains DEFAULT_FREE_FIELD_3
        defaultDelegateShouldBeFound("freeField3.contains=" + DEFAULT_FREE_FIELD_3);

        // Get all the delegateList where freeField3 contains UPDATED_FREE_FIELD_3
        defaultDelegateShouldNotBeFound("freeField3.contains=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField3NotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField3 does not contain DEFAULT_FREE_FIELD_3
        defaultDelegateShouldNotBeFound("freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3);

        // Get all the delegateList where freeField3 does not contain UPDATED_FREE_FIELD_3
        defaultDelegateShouldBeFound("freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField4IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField4 equals to DEFAULT_FREE_FIELD_4
        defaultDelegateShouldBeFound("freeField4.equals=" + DEFAULT_FREE_FIELD_4);

        // Get all the delegateList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultDelegateShouldNotBeFound("freeField4.equals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField4IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
        defaultDelegateShouldBeFound("freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4);

        // Get all the delegateList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultDelegateShouldNotBeFound("freeField4.in=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField4IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField4 is not null
        defaultDelegateShouldBeFound("freeField4.specified=true");

        // Get all the delegateList where freeField4 is null
        defaultDelegateShouldNotBeFound("freeField4.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField4ContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField4 contains DEFAULT_FREE_FIELD_4
        defaultDelegateShouldBeFound("freeField4.contains=" + DEFAULT_FREE_FIELD_4);

        // Get all the delegateList where freeField4 contains UPDATED_FREE_FIELD_4
        defaultDelegateShouldNotBeFound("freeField4.contains=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField4NotContainsSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField4 does not contain DEFAULT_FREE_FIELD_4
        defaultDelegateShouldNotBeFound("freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4);

        // Get all the delegateList where freeField4 does not contain UPDATED_FREE_FIELD_4
        defaultDelegateShouldBeFound("freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 equals to DEFAULT_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.equals=" + DEFAULT_FREE_FIELD_5);

        // Get all the delegateList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.equals=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 in DEFAULT_FREE_FIELD_5 or UPDATED_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.in=" + DEFAULT_FREE_FIELD_5 + "," + UPDATED_FREE_FIELD_5);

        // Get all the delegateList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.in=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 is not null
        defaultDelegateShouldBeFound("freeField5.specified=true");

        // Get all the delegateList where freeField5 is null
        defaultDelegateShouldNotBeFound("freeField5.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 is greater than or equal to DEFAULT_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the delegateList where freeField5 is greater than or equal to UPDATED_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.greaterThanOrEqual=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 is less than or equal to DEFAULT_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.lessThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the delegateList where freeField5 is less than or equal to SMALLER_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.lessThanOrEqual=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsLessThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 is less than DEFAULT_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.lessThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the delegateList where freeField5 is less than UPDATED_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.lessThan=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField5IsGreaterThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField5 is greater than DEFAULT_FREE_FIELD_5
        defaultDelegateShouldNotBeFound("freeField5.greaterThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the delegateList where freeField5 is greater than SMALLER_FREE_FIELD_5
        defaultDelegateShouldBeFound("freeField5.greaterThan=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 equals to DEFAULT_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.equals=" + DEFAULT_FREE_FIELD_6);

        // Get all the delegateList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.equals=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 in DEFAULT_FREE_FIELD_6 or UPDATED_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.in=" + DEFAULT_FREE_FIELD_6 + "," + UPDATED_FREE_FIELD_6);

        // Get all the delegateList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.in=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 is not null
        defaultDelegateShouldBeFound("freeField6.specified=true");

        // Get all the delegateList where freeField6 is null
        defaultDelegateShouldNotBeFound("freeField6.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 is greater than or equal to DEFAULT_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the delegateList where freeField6 is greater than or equal to UPDATED_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.greaterThanOrEqual=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 is less than or equal to DEFAULT_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.lessThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the delegateList where freeField6 is less than or equal to SMALLER_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.lessThanOrEqual=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsLessThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 is less than DEFAULT_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.lessThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the delegateList where freeField6 is less than UPDATED_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.lessThan=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField6IsGreaterThanSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField6 is greater than DEFAULT_FREE_FIELD_6
        defaultDelegateShouldNotBeFound("freeField6.greaterThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the delegateList where freeField6 is greater than SMALLER_FREE_FIELD_6
        defaultDelegateShouldBeFound("freeField6.greaterThan=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField7IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField7 equals to DEFAULT_FREE_FIELD_7
        defaultDelegateShouldBeFound("freeField7.equals=" + DEFAULT_FREE_FIELD_7);

        // Get all the delegateList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultDelegateShouldNotBeFound("freeField7.equals=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField7IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField7 in DEFAULT_FREE_FIELD_7 or UPDATED_FREE_FIELD_7
        defaultDelegateShouldBeFound("freeField7.in=" + DEFAULT_FREE_FIELD_7 + "," + UPDATED_FREE_FIELD_7);

        // Get all the delegateList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultDelegateShouldNotBeFound("freeField7.in=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField7IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField7 is not null
        defaultDelegateShouldBeFound("freeField7.specified=true");

        // Get all the delegateList where freeField7 is null
        defaultDelegateShouldNotBeFound("freeField7.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField8IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField8 equals to DEFAULT_FREE_FIELD_8
        defaultDelegateShouldBeFound("freeField8.equals=" + DEFAULT_FREE_FIELD_8);

        // Get all the delegateList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultDelegateShouldNotBeFound("freeField8.equals=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField8IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField8 in DEFAULT_FREE_FIELD_8 or UPDATED_FREE_FIELD_8
        defaultDelegateShouldBeFound("freeField8.in=" + DEFAULT_FREE_FIELD_8 + "," + UPDATED_FREE_FIELD_8);

        // Get all the delegateList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultDelegateShouldNotBeFound("freeField8.in=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField8IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField8 is not null
        defaultDelegateShouldBeFound("freeField8.specified=true");

        // Get all the delegateList where freeField8 is null
        defaultDelegateShouldNotBeFound("freeField8.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField9IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField9 equals to DEFAULT_FREE_FIELD_9
        defaultDelegateShouldBeFound("freeField9.equals=" + DEFAULT_FREE_FIELD_9);

        // Get all the delegateList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultDelegateShouldNotBeFound("freeField9.equals=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField9IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField9 in DEFAULT_FREE_FIELD_9 or UPDATED_FREE_FIELD_9
        defaultDelegateShouldBeFound("freeField9.in=" + DEFAULT_FREE_FIELD_9 + "," + UPDATED_FREE_FIELD_9);

        // Get all the delegateList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultDelegateShouldNotBeFound("freeField9.in=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField9IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField9 is not null
        defaultDelegateShouldBeFound("freeField9.specified=true");

        // Get all the delegateList where freeField9 is null
        defaultDelegateShouldNotBeFound("freeField9.specified=false");
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField10IsEqualToSomething() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField10 equals to DEFAULT_FREE_FIELD_10
        defaultDelegateShouldBeFound("freeField10.equals=" + DEFAULT_FREE_FIELD_10);

        // Get all the delegateList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultDelegateShouldNotBeFound("freeField10.equals=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField10IsInShouldWork() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField10 in DEFAULT_FREE_FIELD_10 or UPDATED_FREE_FIELD_10
        defaultDelegateShouldBeFound("freeField10.in=" + DEFAULT_FREE_FIELD_10 + "," + UPDATED_FREE_FIELD_10);

        // Get all the delegateList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultDelegateShouldNotBeFound("freeField10.in=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllDelegatesByFreeField10IsNullOrNotNull() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        // Get all the delegateList where freeField10 is not null
        defaultDelegateShouldBeFound("freeField10.specified=true");

        // Get all the delegateList where freeField10 is null
        defaultDelegateShouldNotBeFound("freeField10.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDelegateShouldBeFound(String filter) throws Exception {
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(delegate.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].countryOfResidence").value(hasItem(DEFAULT_COUNTRY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].citizenship").value(hasItem(DEFAULT_CITIZENSHIP)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactRelationship").value(hasItem(DEFAULT_EMERGENCY_CONTACT_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO.intValue())))
            .andExpect(jsonPath("$.[*].countryCodeOne").value(hasItem(DEFAULT_COUNTRY_CODE_ONE)))
            .andExpect(jsonPath("$.[*].countryCodeTwo").value(hasItem(DEFAULT_COUNTRY_CODE_TWO)))
            .andExpect(jsonPath("$.[*].nationalityOne").value(hasItem(DEFAULT_NATIONALITY_ONE)))
            .andExpect(jsonPath("$.[*].countryOfBirth").value(hasItem(DEFAULT_COUNTRY_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].cityOfResidence").value(hasItem(DEFAULT_CITY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].stateOfResidence").value(hasItem(DEFAULT_STATE_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].districtOfResidence").value(hasItem(DEFAULT_DISTRICT_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].departureDate").value(hasItem(DEFAULT_DEPARTURE_DATE.toString())))
            .andExpect(jsonPath("$.[*].departurePlace").value(hasItem(DEFAULT_DEPARTURE_PLACE)))
            .andExpect(jsonPath("$.[*].arrivalDate").value(hasItem(DEFAULT_ARRIVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].arrivalPlace").value(hasItem(DEFAULT_ARRIVAL_PLACE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].isTaxReceipt").value(hasItem(DEFAULT_IS_TAX_RECEIPT.booleanValue())))
            .andExpect(jsonPath("$.[*].profilePhotoContentType").value(hasItem(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].passportImageContentType").value(hasItem(DEFAULT_PASSPORT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].conferenceName").value(hasItem(DEFAULT_CONFERENCE_NAME)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())));

        // Check, that the count call also returns 1
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDelegateShouldNotBeFound(String filter) throws Exception {
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDelegateMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDelegate() throws Exception {
        // Get the delegate
        restDelegateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDelegate() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();

        // Update the delegate
        Delegate updatedDelegate = delegateRepository.findById(delegate.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDelegate are not directly saved in db
        em.detach(updatedDelegate);
        updatedDelegate
            .title(UPDATED_TITLE)
            .fullName(UPDATED_FULL_NAME)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .countryOfResidence(UPDATED_COUNTRY_OF_RESIDENCE)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .citizenship(UPDATED_CITIZENSHIP)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactRelationship(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .countryCodeOne(UPDATED_COUNTRY_CODE_ONE)
            .countryCodeTwo(UPDATED_COUNTRY_CODE_TWO)
            .nationalityOne(UPDATED_NATIONALITY_ONE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .cityOfResidence(UPDATED_CITY_OF_RESIDENCE)
            .stateOfResidence(UPDATED_STATE_OF_RESIDENCE)
            .districtOfResidence(UPDATED_DISTRICT_OF_RESIDENCE)
            .departureDate(UPDATED_DEPARTURE_DATE)
            .departurePlace(UPDATED_DEPARTURE_PLACE)
            .arrivalDate(UPDATED_ARRIVAL_DATE)
            .arrivalPlace(UPDATED_ARRIVAL_PLACE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .isTaxReceipt(UPDATED_IS_TAX_RECEIPT)
            .profilePhotoContentType(UPDATED_PROFILE_PHOTO_CONTENT_TYPE)
            .passportImageContentType(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE)
            .conferenceName(UPDATED_CONFERENCE_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);
        DelegateDTO delegateDTO = delegateMapper.toDto(updatedDelegate);

        restDelegateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, delegateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isOk());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
        Delegate testDelegate = delegateList.get(delegateList.size() - 1);
        assertThat(testDelegate.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testDelegate.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testDelegate.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDelegate.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testDelegate.getCountryOfResidence()).isEqualTo(UPDATED_COUNTRY_OF_RESIDENCE);
        assertThat(testDelegate.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testDelegate.getCitizenship()).isEqualTo(UPDATED_CITIZENSHIP);
        assertThat(testDelegate.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testDelegate.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testDelegate.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testDelegate.getEmergencyContactRelationship()).isEqualTo(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
        assertThat(testDelegate.getEmergencyContactNo()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NO);
        assertThat(testDelegate.getCountryCodeOne()).isEqualTo(UPDATED_COUNTRY_CODE_ONE);
        assertThat(testDelegate.getCountryCodeTwo()).isEqualTo(UPDATED_COUNTRY_CODE_TWO);
        assertThat(testDelegate.getNationalityOne()).isEqualTo(UPDATED_NATIONALITY_ONE);
        assertThat(testDelegate.getCountryOfBirth()).isEqualTo(UPDATED_COUNTRY_OF_BIRTH);
        assertThat(testDelegate.getCityOfResidence()).isEqualTo(UPDATED_CITY_OF_RESIDENCE);
        assertThat(testDelegate.getStateOfResidence()).isEqualTo(UPDATED_STATE_OF_RESIDENCE);
        assertThat(testDelegate.getDistrictOfResidence()).isEqualTo(UPDATED_DISTRICT_OF_RESIDENCE);
        assertThat(testDelegate.getDepartureDate()).isEqualTo(UPDATED_DEPARTURE_DATE);
        assertThat(testDelegate.getDeparturePlace()).isEqualTo(UPDATED_DEPARTURE_PLACE);
        assertThat(testDelegate.getArrivalDate()).isEqualTo(UPDATED_ARRIVAL_DATE);
        assertThat(testDelegate.getArrivalPlace()).isEqualTo(UPDATED_ARRIVAL_PLACE);
        assertThat(testDelegate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDelegate.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testDelegate.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testDelegate.getIsTaxReceipt()).isEqualTo(UPDATED_IS_TAX_RECEIPT);
        assertThat(testDelegate.getProfilePhotoContentType()).isEqualTo(UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
        assertThat(testDelegate.getPassportImageContentType()).isEqualTo(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
        assertThat(testDelegate.getConferenceName()).isEqualTo(UPDATED_CONFERENCE_NAME);
        assertThat(testDelegate.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDelegate.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDelegate.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDelegate.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDelegate.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDelegate.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDelegate.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testDelegate.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDelegate.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDelegate.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void putNonExistingDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, delegateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(delegateDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDelegateWithPatch() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();

        // Update the delegate using partial update
        Delegate partialUpdatedDelegate = new Delegate();
        partialUpdatedDelegate.setId(delegate.getId());

        partialUpdatedDelegate
            .nationality(UPDATED_NATIONALITY)
            .citizenship(UPDATED_CITIZENSHIP)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactRelationship(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .countryCodeTwo(UPDATED_COUNTRY_CODE_TWO)
            .nationalityOne(UPDATED_NATIONALITY_ONE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .stateOfResidence(UPDATED_STATE_OF_RESIDENCE)
            .districtOfResidence(UPDATED_DISTRICT_OF_RESIDENCE)
            .departureDate(UPDATED_DEPARTURE_DATE)
            .arrivalDate(UPDATED_ARRIVAL_DATE)
            .arrivalPlace(UPDATED_ARRIVAL_PLACE)
            .status(UPDATED_STATUS)
            .isTaxReceipt(UPDATED_IS_TAX_RECEIPT)
            .passportImageContentType(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE)
            .conferenceName(UPDATED_CONFERENCE_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField8(UPDATED_FREE_FIELD_8);

        restDelegateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDelegate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDelegate))
            )
            .andExpect(status().isOk());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
        Delegate testDelegate = delegateList.get(delegateList.size() - 1);
        assertThat(testDelegate.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testDelegate.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testDelegate.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDelegate.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testDelegate.getCountryOfResidence()).isEqualTo(DEFAULT_COUNTRY_OF_RESIDENCE);
        assertThat(testDelegate.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testDelegate.getCitizenship()).isEqualTo(UPDATED_CITIZENSHIP);
        assertThat(testDelegate.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testDelegate.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testDelegate.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testDelegate.getEmergencyContactRelationship()).isEqualTo(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
        assertThat(testDelegate.getEmergencyContactNo()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NO);
        assertThat(testDelegate.getCountryCodeOne()).isEqualTo(DEFAULT_COUNTRY_CODE_ONE);
        assertThat(testDelegate.getCountryCodeTwo()).isEqualTo(UPDATED_COUNTRY_CODE_TWO);
        assertThat(testDelegate.getNationalityOne()).isEqualTo(UPDATED_NATIONALITY_ONE);
        assertThat(testDelegate.getCountryOfBirth()).isEqualTo(UPDATED_COUNTRY_OF_BIRTH);
        assertThat(testDelegate.getCityOfResidence()).isEqualTo(DEFAULT_CITY_OF_RESIDENCE);
        assertThat(testDelegate.getStateOfResidence()).isEqualTo(UPDATED_STATE_OF_RESIDENCE);
        assertThat(testDelegate.getDistrictOfResidence()).isEqualTo(UPDATED_DISTRICT_OF_RESIDENCE);
        assertThat(testDelegate.getDepartureDate()).isEqualTo(UPDATED_DEPARTURE_DATE);
        assertThat(testDelegate.getDeparturePlace()).isEqualTo(DEFAULT_DEPARTURE_PLACE);
        assertThat(testDelegate.getArrivalDate()).isEqualTo(UPDATED_ARRIVAL_DATE);
        assertThat(testDelegate.getArrivalPlace()).isEqualTo(UPDATED_ARRIVAL_PLACE);
        assertThat(testDelegate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDelegate.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testDelegate.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testDelegate.getIsTaxReceipt()).isEqualTo(UPDATED_IS_TAX_RECEIPT);
        assertThat(testDelegate.getProfilePhotoContentType()).isEqualTo(DEFAULT_PROFILE_PHOTO_CONTENT_TYPE);
        assertThat(testDelegate.getPassportImageContentType()).isEqualTo(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
        assertThat(testDelegate.getConferenceName()).isEqualTo(UPDATED_CONFERENCE_NAME);
        assertThat(testDelegate.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDelegate.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDelegate.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDelegate.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDelegate.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDelegate.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDelegate.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testDelegate.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDelegate.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDelegate.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void fullUpdateDelegateWithPatch() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();

        // Update the delegate using partial update
        Delegate partialUpdatedDelegate = new Delegate();
        partialUpdatedDelegate.setId(delegate.getId());

        partialUpdatedDelegate
            .title(UPDATED_TITLE)
            .fullName(UPDATED_FULL_NAME)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .countryOfResidence(UPDATED_COUNTRY_OF_RESIDENCE)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .citizenship(UPDATED_CITIZENSHIP)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactRelationship(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .countryCodeOne(UPDATED_COUNTRY_CODE_ONE)
            .countryCodeTwo(UPDATED_COUNTRY_CODE_TWO)
            .nationalityOne(UPDATED_NATIONALITY_ONE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .cityOfResidence(UPDATED_CITY_OF_RESIDENCE)
            .stateOfResidence(UPDATED_STATE_OF_RESIDENCE)
            .districtOfResidence(UPDATED_DISTRICT_OF_RESIDENCE)
            .departureDate(UPDATED_DEPARTURE_DATE)
            .departurePlace(UPDATED_DEPARTURE_PLACE)
            .arrivalDate(UPDATED_ARRIVAL_DATE)
            .arrivalPlace(UPDATED_ARRIVAL_PLACE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .isTaxReceipt(UPDATED_IS_TAX_RECEIPT)
            .profilePhotoContentType(UPDATED_PROFILE_PHOTO_CONTENT_TYPE)
            .passportImageContentType(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE)
            .conferenceName(UPDATED_CONFERENCE_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);

        restDelegateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDelegate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDelegate))
            )
            .andExpect(status().isOk());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
        Delegate testDelegate = delegateList.get(delegateList.size() - 1);
        assertThat(testDelegate.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testDelegate.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testDelegate.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDelegate.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testDelegate.getCountryOfResidence()).isEqualTo(UPDATED_COUNTRY_OF_RESIDENCE);
        assertThat(testDelegate.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testDelegate.getCitizenship()).isEqualTo(UPDATED_CITIZENSHIP);
        assertThat(testDelegate.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testDelegate.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testDelegate.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testDelegate.getEmergencyContactRelationship()).isEqualTo(UPDATED_EMERGENCY_CONTACT_RELATIONSHIP);
        assertThat(testDelegate.getEmergencyContactNo()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NO);
        assertThat(testDelegate.getCountryCodeOne()).isEqualTo(UPDATED_COUNTRY_CODE_ONE);
        assertThat(testDelegate.getCountryCodeTwo()).isEqualTo(UPDATED_COUNTRY_CODE_TWO);
        assertThat(testDelegate.getNationalityOne()).isEqualTo(UPDATED_NATIONALITY_ONE);
        assertThat(testDelegate.getCountryOfBirth()).isEqualTo(UPDATED_COUNTRY_OF_BIRTH);
        assertThat(testDelegate.getCityOfResidence()).isEqualTo(UPDATED_CITY_OF_RESIDENCE);
        assertThat(testDelegate.getStateOfResidence()).isEqualTo(UPDATED_STATE_OF_RESIDENCE);
        assertThat(testDelegate.getDistrictOfResidence()).isEqualTo(UPDATED_DISTRICT_OF_RESIDENCE);
        assertThat(testDelegate.getDepartureDate()).isEqualTo(UPDATED_DEPARTURE_DATE);
        assertThat(testDelegate.getDeparturePlace()).isEqualTo(UPDATED_DEPARTURE_PLACE);
        assertThat(testDelegate.getArrivalDate()).isEqualTo(UPDATED_ARRIVAL_DATE);
        assertThat(testDelegate.getArrivalPlace()).isEqualTo(UPDATED_ARRIVAL_PLACE);
        assertThat(testDelegate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDelegate.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testDelegate.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testDelegate.getIsTaxReceipt()).isEqualTo(UPDATED_IS_TAX_RECEIPT);
        assertThat(testDelegate.getProfilePhotoContentType()).isEqualTo(UPDATED_PROFILE_PHOTO_CONTENT_TYPE);
        assertThat(testDelegate.getPassportImageContentType()).isEqualTo(UPDATED_PASSPORT_IMAGE_CONTENT_TYPE);
        assertThat(testDelegate.getConferenceName()).isEqualTo(UPDATED_CONFERENCE_NAME);
        assertThat(testDelegate.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDelegate.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDelegate.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDelegate.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDelegate.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDelegate.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDelegate.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testDelegate.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDelegate.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDelegate.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void patchNonExistingDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, delegateDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDelegate() throws Exception {
        int databaseSizeBeforeUpdate = delegateRepository.findAll().size();
        delegate.setId(count.incrementAndGet());

        // Create the Delegate
        DelegateDTO delegateDTO = delegateMapper.toDto(delegate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDelegateMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(delegateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Delegate in the database
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDelegate() throws Exception {
        // Initialize the database
        delegateRepository.saveAndFlush(delegate);

        int databaseSizeBeforeDelete = delegateRepository.findAll().size();

        // Delete the delegate
        restDelegateMockMvc
            .perform(delete(ENTITY_API_URL_ID, delegate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Delegate> delegateList = delegateRepository.findAll();
        assertThat(delegateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
