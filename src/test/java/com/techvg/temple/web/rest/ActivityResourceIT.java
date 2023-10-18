package com.techvg.temple.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.temple.IntegrationTest;
import com.techvg.temple.domain.Activity;
import com.techvg.temple.repository.ActivityRepository;
import com.techvg.temple.service.dto.ActivityDTO;
import com.techvg.temple.service.mapper.ActivityMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
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
 * Integration tests for the {@link ActivityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ActivityResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_NO_OF_PARTICIPANTS = 1L;
    private static final Long UPDATED_NO_OF_PARTICIPANTS = 2L;
    private static final Long SMALLER_NO_OF_PARTICIPANTS = 1L - 1L;

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE_NO = 1L;
    private static final Long UPDATED_MOBILE_NO = 2L;
    private static final Long SMALLER_MOBILE_NO = 1L - 1L;

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILE_PHOTO_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_PHOTO_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_IMAGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

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

    private static final Boolean DEFAULT_FREE_FIELD_11 = false;
    private static final Boolean UPDATED_FREE_FIELD_11 = true;

    private static final Boolean DEFAULT_FREE_FIELD_12 = false;
    private static final Boolean UPDATED_FREE_FIELD_12 = true;

    private static final String ENTITY_API_URL = "/api/activities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActivityMockMvc;

    private Activity activity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Activity createEntity(EntityManager em) {
        Activity activity = new Activity()
            .date(DEFAULT_DATE)
            .organizationName(DEFAULT_ORGANIZATION_NAME)
            .location(DEFAULT_LOCATION)
            .activityName(DEFAULT_ACTIVITY_NAME)
            .description(DEFAULT_DESCRIPTION)
            .noOfParticipants(DEFAULT_NO_OF_PARTICIPANTS)
            .time(DEFAULT_TIME)
            .mobileNo(DEFAULT_MOBILE_NO)
            .emailId(DEFAULT_EMAIL_ID)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .comments(DEFAULT_COMMENTS)
            .profilePhotoType(DEFAULT_PROFILE_PHOTO_TYPE)
            .signatureImageType(DEFAULT_SIGNATURE_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10)
            .freeField11(DEFAULT_FREE_FIELD_11)
            .freeField12(DEFAULT_FREE_FIELD_12);
        return activity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Activity createUpdatedEntity(EntityManager em) {
        Activity activity = new Activity()
            .date(UPDATED_DATE)
            .organizationName(UPDATED_ORGANIZATION_NAME)
            .location(UPDATED_LOCATION)
            .activityName(UPDATED_ACTIVITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .noOfParticipants(UPDATED_NO_OF_PARTICIPANTS)
            .time(UPDATED_TIME)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .comments(UPDATED_COMMENTS)
            .profilePhotoType(UPDATED_PROFILE_PHOTO_TYPE)
            .signatureImageType(UPDATED_SIGNATURE_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12);
        return activity;
    }

    @BeforeEach
    public void initTest() {
        activity = createEntity(em);
    }

    @Test
    @Transactional
    void createActivity() throws Exception {
        int databaseSizeBeforeCreate = activityRepository.findAll().size();
        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);
        restActivityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isCreated());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate + 1);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testActivity.getOrganizationName()).isEqualTo(DEFAULT_ORGANIZATION_NAME);
        assertThat(testActivity.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testActivity.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
        assertThat(testActivity.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActivity.getNoOfParticipants()).isEqualTo(DEFAULT_NO_OF_PARTICIPANTS);
        assertThat(testActivity.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testActivity.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testActivity.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testActivity.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testActivity.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testActivity.getProfilePhotoType()).isEqualTo(DEFAULT_PROFILE_PHOTO_TYPE);
        assertThat(testActivity.getSignatureImageType()).isEqualTo(DEFAULT_SIGNATURE_IMAGE_TYPE);
        assertThat(testActivity.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testActivity.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testActivity.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testActivity.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testActivity.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testActivity.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testActivity.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testActivity.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testActivity.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testActivity.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testActivity.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testActivity.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testActivity.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testActivity.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void createActivityWithExistingId() throws Exception {
        // Create the Activity with an existing ID
        activity.setId(1L);
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        int databaseSizeBeforeCreate = activityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllActivities() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList
        restActivityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activity.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].organizationName").value(hasItem(DEFAULT_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].noOfParticipants").value(hasItem(DEFAULT_NO_OF_PARTICIPANTS.intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].profilePhotoType").value(hasItem(DEFAULT_PROFILE_PHOTO_TYPE)))
            .andExpect(jsonPath("$.[*].signatureImageType").value(hasItem(DEFAULT_SIGNATURE_IMAGE_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField11").value(hasItem(DEFAULT_FREE_FIELD_11.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField12").value(hasItem(DEFAULT_FREE_FIELD_12.booleanValue())));
    }

    @Test
    @Transactional
    void getActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get the activity
        restActivityMockMvc
            .perform(get(ENTITY_API_URL_ID, activity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(activity.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.organizationName").value(DEFAULT_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.activityName").value(DEFAULT_ACTIVITY_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.noOfParticipants").value(DEFAULT_NO_OF_PARTICIPANTS.intValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.intValue()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.profilePhotoType").value(DEFAULT_PROFILE_PHOTO_TYPE))
            .andExpect(jsonPath("$.signatureImageType").value(DEFAULT_SIGNATURE_IMAGE_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
            .andExpect(jsonPath("$.freeField5").value(DEFAULT_FREE_FIELD_5.intValue()))
            .andExpect(jsonPath("$.freeField6").value(DEFAULT_FREE_FIELD_6.intValue()))
            .andExpect(jsonPath("$.freeField7").value(DEFAULT_FREE_FIELD_7.toString()))
            .andExpect(jsonPath("$.freeField8").value(DEFAULT_FREE_FIELD_8.toString()))
            .andExpect(jsonPath("$.freeField9").value(DEFAULT_FREE_FIELD_9.booleanValue()))
            .andExpect(jsonPath("$.freeField10").value(DEFAULT_FREE_FIELD_10.booleanValue()))
            .andExpect(jsonPath("$.freeField11").value(DEFAULT_FREE_FIELD_11.booleanValue()))
            .andExpect(jsonPath("$.freeField12").value(DEFAULT_FREE_FIELD_12.booleanValue()));
    }

    @Test
    @Transactional
    void getActivitiesByIdFiltering() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        Long id = activity.getId();

        defaultActivityShouldBeFound("id.equals=" + id);
        defaultActivityShouldNotBeFound("id.notEquals=" + id);

        defaultActivityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultActivityShouldNotBeFound("id.greaterThan=" + id);

        defaultActivityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultActivityShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllActivitiesByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where date equals to DEFAULT_DATE
        defaultActivityShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the activityList where date equals to UPDATED_DATE
        defaultActivityShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllActivitiesByDateIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where date in DEFAULT_DATE or UPDATED_DATE
        defaultActivityShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the activityList where date equals to UPDATED_DATE
        defaultActivityShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllActivitiesByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where date is not null
        defaultActivityShouldBeFound("date.specified=true");

        // Get all the activityList where date is null
        defaultActivityShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByOrganizationNameIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where organizationName equals to DEFAULT_ORGANIZATION_NAME
        defaultActivityShouldBeFound("organizationName.equals=" + DEFAULT_ORGANIZATION_NAME);

        // Get all the activityList where organizationName equals to UPDATED_ORGANIZATION_NAME
        defaultActivityShouldNotBeFound("organizationName.equals=" + UPDATED_ORGANIZATION_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByOrganizationNameIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where organizationName in DEFAULT_ORGANIZATION_NAME or UPDATED_ORGANIZATION_NAME
        defaultActivityShouldBeFound("organizationName.in=" + DEFAULT_ORGANIZATION_NAME + "," + UPDATED_ORGANIZATION_NAME);

        // Get all the activityList where organizationName equals to UPDATED_ORGANIZATION_NAME
        defaultActivityShouldNotBeFound("organizationName.in=" + UPDATED_ORGANIZATION_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByOrganizationNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where organizationName is not null
        defaultActivityShouldBeFound("organizationName.specified=true");

        // Get all the activityList where organizationName is null
        defaultActivityShouldNotBeFound("organizationName.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByOrganizationNameContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where organizationName contains DEFAULT_ORGANIZATION_NAME
        defaultActivityShouldBeFound("organizationName.contains=" + DEFAULT_ORGANIZATION_NAME);

        // Get all the activityList where organizationName contains UPDATED_ORGANIZATION_NAME
        defaultActivityShouldNotBeFound("organizationName.contains=" + UPDATED_ORGANIZATION_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByOrganizationNameNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where organizationName does not contain DEFAULT_ORGANIZATION_NAME
        defaultActivityShouldNotBeFound("organizationName.doesNotContain=" + DEFAULT_ORGANIZATION_NAME);

        // Get all the activityList where organizationName does not contain UPDATED_ORGANIZATION_NAME
        defaultActivityShouldBeFound("organizationName.doesNotContain=" + UPDATED_ORGANIZATION_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where location equals to DEFAULT_LOCATION
        defaultActivityShouldBeFound("location.equals=" + DEFAULT_LOCATION);

        // Get all the activityList where location equals to UPDATED_LOCATION
        defaultActivityShouldNotBeFound("location.equals=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllActivitiesByLocationIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where location in DEFAULT_LOCATION or UPDATED_LOCATION
        defaultActivityShouldBeFound("location.in=" + DEFAULT_LOCATION + "," + UPDATED_LOCATION);

        // Get all the activityList where location equals to UPDATED_LOCATION
        defaultActivityShouldNotBeFound("location.in=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllActivitiesByLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where location is not null
        defaultActivityShouldBeFound("location.specified=true");

        // Get all the activityList where location is null
        defaultActivityShouldNotBeFound("location.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByLocationContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where location contains DEFAULT_LOCATION
        defaultActivityShouldBeFound("location.contains=" + DEFAULT_LOCATION);

        // Get all the activityList where location contains UPDATED_LOCATION
        defaultActivityShouldNotBeFound("location.contains=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllActivitiesByLocationNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where location does not contain DEFAULT_LOCATION
        defaultActivityShouldNotBeFound("location.doesNotContain=" + DEFAULT_LOCATION);

        // Get all the activityList where location does not contain UPDATED_LOCATION
        defaultActivityShouldBeFound("location.doesNotContain=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllActivitiesByActivityNameIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityName equals to DEFAULT_ACTIVITY_NAME
        defaultActivityShouldBeFound("activityName.equals=" + DEFAULT_ACTIVITY_NAME);

        // Get all the activityList where activityName equals to UPDATED_ACTIVITY_NAME
        defaultActivityShouldNotBeFound("activityName.equals=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByActivityNameIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityName in DEFAULT_ACTIVITY_NAME or UPDATED_ACTIVITY_NAME
        defaultActivityShouldBeFound("activityName.in=" + DEFAULT_ACTIVITY_NAME + "," + UPDATED_ACTIVITY_NAME);

        // Get all the activityList where activityName equals to UPDATED_ACTIVITY_NAME
        defaultActivityShouldNotBeFound("activityName.in=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByActivityNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityName is not null
        defaultActivityShouldBeFound("activityName.specified=true");

        // Get all the activityList where activityName is null
        defaultActivityShouldNotBeFound("activityName.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByActivityNameContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityName contains DEFAULT_ACTIVITY_NAME
        defaultActivityShouldBeFound("activityName.contains=" + DEFAULT_ACTIVITY_NAME);

        // Get all the activityList where activityName contains UPDATED_ACTIVITY_NAME
        defaultActivityShouldNotBeFound("activityName.contains=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByActivityNameNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where activityName does not contain DEFAULT_ACTIVITY_NAME
        defaultActivityShouldNotBeFound("activityName.doesNotContain=" + DEFAULT_ACTIVITY_NAME);

        // Get all the activityList where activityName does not contain UPDATED_ACTIVITY_NAME
        defaultActivityShouldBeFound("activityName.doesNotContain=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    void getAllActivitiesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where description equals to DEFAULT_DESCRIPTION
        defaultActivityShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the activityList where description equals to UPDATED_DESCRIPTION
        defaultActivityShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllActivitiesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultActivityShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the activityList where description equals to UPDATED_DESCRIPTION
        defaultActivityShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllActivitiesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where description is not null
        defaultActivityShouldBeFound("description.specified=true");

        // Get all the activityList where description is null
        defaultActivityShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where description contains DEFAULT_DESCRIPTION
        defaultActivityShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the activityList where description contains UPDATED_DESCRIPTION
        defaultActivityShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllActivitiesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where description does not contain DEFAULT_DESCRIPTION
        defaultActivityShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the activityList where description does not contain UPDATED_DESCRIPTION
        defaultActivityShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants equals to DEFAULT_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.equals=" + DEFAULT_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants equals to UPDATED_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.equals=" + UPDATED_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants in DEFAULT_NO_OF_PARTICIPANTS or UPDATED_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.in=" + DEFAULT_NO_OF_PARTICIPANTS + "," + UPDATED_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants equals to UPDATED_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.in=" + UPDATED_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants is not null
        defaultActivityShouldBeFound("noOfParticipants.specified=true");

        // Get all the activityList where noOfParticipants is null
        defaultActivityShouldNotBeFound("noOfParticipants.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants is greater than or equal to DEFAULT_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.greaterThanOrEqual=" + DEFAULT_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants is greater than or equal to UPDATED_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.greaterThanOrEqual=" + UPDATED_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants is less than or equal to DEFAULT_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.lessThanOrEqual=" + DEFAULT_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants is less than or equal to SMALLER_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.lessThanOrEqual=" + SMALLER_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsLessThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants is less than DEFAULT_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.lessThan=" + DEFAULT_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants is less than UPDATED_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.lessThan=" + UPDATED_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByNoOfParticipantsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where noOfParticipants is greater than DEFAULT_NO_OF_PARTICIPANTS
        defaultActivityShouldNotBeFound("noOfParticipants.greaterThan=" + DEFAULT_NO_OF_PARTICIPANTS);

        // Get all the activityList where noOfParticipants is greater than SMALLER_NO_OF_PARTICIPANTS
        defaultActivityShouldBeFound("noOfParticipants.greaterThan=" + SMALLER_NO_OF_PARTICIPANTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where time equals to DEFAULT_TIME
        defaultActivityShouldBeFound("time.equals=" + DEFAULT_TIME);

        // Get all the activityList where time equals to UPDATED_TIME
        defaultActivityShouldNotBeFound("time.equals=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    void getAllActivitiesByTimeIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where time in DEFAULT_TIME or UPDATED_TIME
        defaultActivityShouldBeFound("time.in=" + DEFAULT_TIME + "," + UPDATED_TIME);

        // Get all the activityList where time equals to UPDATED_TIME
        defaultActivityShouldNotBeFound("time.in=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    void getAllActivitiesByTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where time is not null
        defaultActivityShouldBeFound("time.specified=true");

        // Get all the activityList where time is null
        defaultActivityShouldNotBeFound("time.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByTimeContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where time contains DEFAULT_TIME
        defaultActivityShouldBeFound("time.contains=" + DEFAULT_TIME);

        // Get all the activityList where time contains UPDATED_TIME
        defaultActivityShouldNotBeFound("time.contains=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    void getAllActivitiesByTimeNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where time does not contain DEFAULT_TIME
        defaultActivityShouldNotBeFound("time.doesNotContain=" + DEFAULT_TIME);

        // Get all the activityList where time does not contain UPDATED_TIME
        defaultActivityShouldBeFound("time.doesNotContain=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo equals to DEFAULT_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.equals=" + DEFAULT_MOBILE_NO);

        // Get all the activityList where mobileNo equals to UPDATED_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.equals=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo in DEFAULT_MOBILE_NO or UPDATED_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.in=" + DEFAULT_MOBILE_NO + "," + UPDATED_MOBILE_NO);

        // Get all the activityList where mobileNo equals to UPDATED_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.in=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo is not null
        defaultActivityShouldBeFound("mobileNo.specified=true");

        // Get all the activityList where mobileNo is null
        defaultActivityShouldNotBeFound("mobileNo.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo is greater than or equal to DEFAULT_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.greaterThanOrEqual=" + DEFAULT_MOBILE_NO);

        // Get all the activityList where mobileNo is greater than or equal to UPDATED_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.greaterThanOrEqual=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo is less than or equal to DEFAULT_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.lessThanOrEqual=" + DEFAULT_MOBILE_NO);

        // Get all the activityList where mobileNo is less than or equal to SMALLER_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.lessThanOrEqual=" + SMALLER_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsLessThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo is less than DEFAULT_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.lessThan=" + DEFAULT_MOBILE_NO);

        // Get all the activityList where mobileNo is less than UPDATED_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.lessThan=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByMobileNoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where mobileNo is greater than DEFAULT_MOBILE_NO
        defaultActivityShouldNotBeFound("mobileNo.greaterThan=" + DEFAULT_MOBILE_NO);

        // Get all the activityList where mobileNo is greater than SMALLER_MOBILE_NO
        defaultActivityShouldBeFound("mobileNo.greaterThan=" + SMALLER_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllActivitiesByEmailIdIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where emailId equals to DEFAULT_EMAIL_ID
        defaultActivityShouldBeFound("emailId.equals=" + DEFAULT_EMAIL_ID);

        // Get all the activityList where emailId equals to UPDATED_EMAIL_ID
        defaultActivityShouldNotBeFound("emailId.equals=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllActivitiesByEmailIdIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where emailId in DEFAULT_EMAIL_ID or UPDATED_EMAIL_ID
        defaultActivityShouldBeFound("emailId.in=" + DEFAULT_EMAIL_ID + "," + UPDATED_EMAIL_ID);

        // Get all the activityList where emailId equals to UPDATED_EMAIL_ID
        defaultActivityShouldNotBeFound("emailId.in=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllActivitiesByEmailIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where emailId is not null
        defaultActivityShouldBeFound("emailId.specified=true");

        // Get all the activityList where emailId is null
        defaultActivityShouldNotBeFound("emailId.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByEmailIdContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where emailId contains DEFAULT_EMAIL_ID
        defaultActivityShouldBeFound("emailId.contains=" + DEFAULT_EMAIL_ID);

        // Get all the activityList where emailId contains UPDATED_EMAIL_ID
        defaultActivityShouldNotBeFound("emailId.contains=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllActivitiesByEmailIdNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where emailId does not contain DEFAULT_EMAIL_ID
        defaultActivityShouldNotBeFound("emailId.doesNotContain=" + DEFAULT_EMAIL_ID);

        // Get all the activityList where emailId does not contain UPDATED_EMAIL_ID
        defaultActivityShouldBeFound("emailId.doesNotContain=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllActivitiesByContactPersonIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where contactPerson equals to DEFAULT_CONTACT_PERSON
        defaultActivityShouldBeFound("contactPerson.equals=" + DEFAULT_CONTACT_PERSON);

        // Get all the activityList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultActivityShouldNotBeFound("contactPerson.equals=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllActivitiesByContactPersonIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where contactPerson in DEFAULT_CONTACT_PERSON or UPDATED_CONTACT_PERSON
        defaultActivityShouldBeFound("contactPerson.in=" + DEFAULT_CONTACT_PERSON + "," + UPDATED_CONTACT_PERSON);

        // Get all the activityList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultActivityShouldNotBeFound("contactPerson.in=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllActivitiesByContactPersonIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where contactPerson is not null
        defaultActivityShouldBeFound("contactPerson.specified=true");

        // Get all the activityList where contactPerson is null
        defaultActivityShouldNotBeFound("contactPerson.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByContactPersonContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where contactPerson contains DEFAULT_CONTACT_PERSON
        defaultActivityShouldBeFound("contactPerson.contains=" + DEFAULT_CONTACT_PERSON);

        // Get all the activityList where contactPerson contains UPDATED_CONTACT_PERSON
        defaultActivityShouldNotBeFound("contactPerson.contains=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllActivitiesByContactPersonNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where contactPerson does not contain DEFAULT_CONTACT_PERSON
        defaultActivityShouldNotBeFound("contactPerson.doesNotContain=" + DEFAULT_CONTACT_PERSON);

        // Get all the activityList where contactPerson does not contain UPDATED_CONTACT_PERSON
        defaultActivityShouldBeFound("contactPerson.doesNotContain=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllActivitiesByCommentsIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where comments equals to DEFAULT_COMMENTS
        defaultActivityShouldBeFound("comments.equals=" + DEFAULT_COMMENTS);

        // Get all the activityList where comments equals to UPDATED_COMMENTS
        defaultActivityShouldNotBeFound("comments.equals=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByCommentsIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where comments in DEFAULT_COMMENTS or UPDATED_COMMENTS
        defaultActivityShouldBeFound("comments.in=" + DEFAULT_COMMENTS + "," + UPDATED_COMMENTS);

        // Get all the activityList where comments equals to UPDATED_COMMENTS
        defaultActivityShouldNotBeFound("comments.in=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByCommentsIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where comments is not null
        defaultActivityShouldBeFound("comments.specified=true");

        // Get all the activityList where comments is null
        defaultActivityShouldNotBeFound("comments.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByCommentsContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where comments contains DEFAULT_COMMENTS
        defaultActivityShouldBeFound("comments.contains=" + DEFAULT_COMMENTS);

        // Get all the activityList where comments contains UPDATED_COMMENTS
        defaultActivityShouldNotBeFound("comments.contains=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByCommentsNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where comments does not contain DEFAULT_COMMENTS
        defaultActivityShouldNotBeFound("comments.doesNotContain=" + DEFAULT_COMMENTS);

        // Get all the activityList where comments does not contain UPDATED_COMMENTS
        defaultActivityShouldBeFound("comments.doesNotContain=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void getAllActivitiesByProfilePhotoTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where profilePhotoType equals to DEFAULT_PROFILE_PHOTO_TYPE
        defaultActivityShouldBeFound("profilePhotoType.equals=" + DEFAULT_PROFILE_PHOTO_TYPE);

        // Get all the activityList where profilePhotoType equals to UPDATED_PROFILE_PHOTO_TYPE
        defaultActivityShouldNotBeFound("profilePhotoType.equals=" + UPDATED_PROFILE_PHOTO_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesByProfilePhotoTypeIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where profilePhotoType in DEFAULT_PROFILE_PHOTO_TYPE or UPDATED_PROFILE_PHOTO_TYPE
        defaultActivityShouldBeFound("profilePhotoType.in=" + DEFAULT_PROFILE_PHOTO_TYPE + "," + UPDATED_PROFILE_PHOTO_TYPE);

        // Get all the activityList where profilePhotoType equals to UPDATED_PROFILE_PHOTO_TYPE
        defaultActivityShouldNotBeFound("profilePhotoType.in=" + UPDATED_PROFILE_PHOTO_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesByProfilePhotoTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where profilePhotoType is not null
        defaultActivityShouldBeFound("profilePhotoType.specified=true");

        // Get all the activityList where profilePhotoType is null
        defaultActivityShouldNotBeFound("profilePhotoType.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByProfilePhotoTypeContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where profilePhotoType contains DEFAULT_PROFILE_PHOTO_TYPE
        defaultActivityShouldBeFound("profilePhotoType.contains=" + DEFAULT_PROFILE_PHOTO_TYPE);

        // Get all the activityList where profilePhotoType contains UPDATED_PROFILE_PHOTO_TYPE
        defaultActivityShouldNotBeFound("profilePhotoType.contains=" + UPDATED_PROFILE_PHOTO_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesByProfilePhotoTypeNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where profilePhotoType does not contain DEFAULT_PROFILE_PHOTO_TYPE
        defaultActivityShouldNotBeFound("profilePhotoType.doesNotContain=" + DEFAULT_PROFILE_PHOTO_TYPE);

        // Get all the activityList where profilePhotoType does not contain UPDATED_PROFILE_PHOTO_TYPE
        defaultActivityShouldBeFound("profilePhotoType.doesNotContain=" + UPDATED_PROFILE_PHOTO_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesBySignatureImageTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where signatureImageType equals to DEFAULT_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldBeFound("signatureImageType.equals=" + DEFAULT_SIGNATURE_IMAGE_TYPE);

        // Get all the activityList where signatureImageType equals to UPDATED_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldNotBeFound("signatureImageType.equals=" + UPDATED_SIGNATURE_IMAGE_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesBySignatureImageTypeIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where signatureImageType in DEFAULT_SIGNATURE_IMAGE_TYPE or UPDATED_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldBeFound("signatureImageType.in=" + DEFAULT_SIGNATURE_IMAGE_TYPE + "," + UPDATED_SIGNATURE_IMAGE_TYPE);

        // Get all the activityList where signatureImageType equals to UPDATED_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldNotBeFound("signatureImageType.in=" + UPDATED_SIGNATURE_IMAGE_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesBySignatureImageTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where signatureImageType is not null
        defaultActivityShouldBeFound("signatureImageType.specified=true");

        // Get all the activityList where signatureImageType is null
        defaultActivityShouldNotBeFound("signatureImageType.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesBySignatureImageTypeContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where signatureImageType contains DEFAULT_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldBeFound("signatureImageType.contains=" + DEFAULT_SIGNATURE_IMAGE_TYPE);

        // Get all the activityList where signatureImageType contains UPDATED_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldNotBeFound("signatureImageType.contains=" + UPDATED_SIGNATURE_IMAGE_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesBySignatureImageTypeNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where signatureImageType does not contain DEFAULT_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldNotBeFound("signatureImageType.doesNotContain=" + DEFAULT_SIGNATURE_IMAGE_TYPE);

        // Get all the activityList where signatureImageType does not contain UPDATED_SIGNATURE_IMAGE_TYPE
        defaultActivityShouldBeFound("signatureImageType.doesNotContain=" + UPDATED_SIGNATURE_IMAGE_TYPE);
    }

    @Test
    @Transactional
    void getAllActivitiesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where status equals to DEFAULT_STATUS
        defaultActivityShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the activityList where status equals to UPDATED_STATUS
        defaultActivityShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllActivitiesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultActivityShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the activityList where status equals to UPDATED_STATUS
        defaultActivityShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllActivitiesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where status is not null
        defaultActivityShouldBeFound("status.specified=true");

        // Get all the activityList where status is null
        defaultActivityShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByStatusContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where status contains DEFAULT_STATUS
        defaultActivityShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the activityList where status contains UPDATED_STATUS
        defaultActivityShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllActivitiesByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where status does not contain DEFAULT_STATUS
        defaultActivityShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the activityList where status does not contain UPDATED_STATUS
        defaultActivityShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultActivityShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the activityList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultActivityShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultActivityShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the activityList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultActivityShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModified is not null
        defaultActivityShouldBeFound("lastModified.specified=true");

        // Get all the activityList where lastModified is null
        defaultActivityShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultActivityShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the activityList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultActivityShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultActivityShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the activityList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultActivityShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModifiedBy is not null
        defaultActivityShouldBeFound("lastModifiedBy.specified=true");

        // Get all the activityList where lastModifiedBy is null
        defaultActivityShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultActivityShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the activityList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultActivityShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllActivitiesByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultActivityShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the activityList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultActivityShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultActivityShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the activityList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultActivityShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultActivityShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the activityList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultActivityShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField1 is not null
        defaultActivityShouldBeFound("freeField1.specified=true");

        // Get all the activityList where freeField1 is null
        defaultActivityShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultActivityShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the activityList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultActivityShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultActivityShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the activityList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultActivityShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultActivityShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the activityList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultActivityShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultActivityShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the activityList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultActivityShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField2 is not null
        defaultActivityShouldBeFound("freeField2.specified=true");

        // Get all the activityList where freeField2 is null
        defaultActivityShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultActivityShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the activityList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultActivityShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultActivityShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the activityList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultActivityShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField3IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField3 equals to DEFAULT_FREE_FIELD_3
        defaultActivityShouldBeFound("freeField3.equals=" + DEFAULT_FREE_FIELD_3);

        // Get all the activityList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultActivityShouldNotBeFound("freeField3.equals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField3IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
        defaultActivityShouldBeFound("freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3);

        // Get all the activityList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultActivityShouldNotBeFound("freeField3.in=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField3IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField3 is not null
        defaultActivityShouldBeFound("freeField3.specified=true");

        // Get all the activityList where freeField3 is null
        defaultActivityShouldNotBeFound("freeField3.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField3ContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField3 contains DEFAULT_FREE_FIELD_3
        defaultActivityShouldBeFound("freeField3.contains=" + DEFAULT_FREE_FIELD_3);

        // Get all the activityList where freeField3 contains UPDATED_FREE_FIELD_3
        defaultActivityShouldNotBeFound("freeField3.contains=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField3NotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField3 does not contain DEFAULT_FREE_FIELD_3
        defaultActivityShouldNotBeFound("freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3);

        // Get all the activityList where freeField3 does not contain UPDATED_FREE_FIELD_3
        defaultActivityShouldBeFound("freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField4IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField4 equals to DEFAULT_FREE_FIELD_4
        defaultActivityShouldBeFound("freeField4.equals=" + DEFAULT_FREE_FIELD_4);

        // Get all the activityList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultActivityShouldNotBeFound("freeField4.equals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField4IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
        defaultActivityShouldBeFound("freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4);

        // Get all the activityList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultActivityShouldNotBeFound("freeField4.in=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField4IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField4 is not null
        defaultActivityShouldBeFound("freeField4.specified=true");

        // Get all the activityList where freeField4 is null
        defaultActivityShouldNotBeFound("freeField4.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField4ContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField4 contains DEFAULT_FREE_FIELD_4
        defaultActivityShouldBeFound("freeField4.contains=" + DEFAULT_FREE_FIELD_4);

        // Get all the activityList where freeField4 contains UPDATED_FREE_FIELD_4
        defaultActivityShouldNotBeFound("freeField4.contains=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField4NotContainsSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField4 does not contain DEFAULT_FREE_FIELD_4
        defaultActivityShouldNotBeFound("freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4);

        // Get all the activityList where freeField4 does not contain UPDATED_FREE_FIELD_4
        defaultActivityShouldBeFound("freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 equals to DEFAULT_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.equals=" + DEFAULT_FREE_FIELD_5);

        // Get all the activityList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.equals=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 in DEFAULT_FREE_FIELD_5 or UPDATED_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.in=" + DEFAULT_FREE_FIELD_5 + "," + UPDATED_FREE_FIELD_5);

        // Get all the activityList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.in=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 is not null
        defaultActivityShouldBeFound("freeField5.specified=true");

        // Get all the activityList where freeField5 is null
        defaultActivityShouldNotBeFound("freeField5.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 is greater than or equal to DEFAULT_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the activityList where freeField5 is greater than or equal to UPDATED_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.greaterThanOrEqual=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 is less than or equal to DEFAULT_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.lessThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the activityList where freeField5 is less than or equal to SMALLER_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.lessThanOrEqual=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsLessThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 is less than DEFAULT_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.lessThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the activityList where freeField5 is less than UPDATED_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.lessThan=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField5IsGreaterThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField5 is greater than DEFAULT_FREE_FIELD_5
        defaultActivityShouldNotBeFound("freeField5.greaterThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the activityList where freeField5 is greater than SMALLER_FREE_FIELD_5
        defaultActivityShouldBeFound("freeField5.greaterThan=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 equals to DEFAULT_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.equals=" + DEFAULT_FREE_FIELD_6);

        // Get all the activityList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.equals=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 in DEFAULT_FREE_FIELD_6 or UPDATED_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.in=" + DEFAULT_FREE_FIELD_6 + "," + UPDATED_FREE_FIELD_6);

        // Get all the activityList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.in=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 is not null
        defaultActivityShouldBeFound("freeField6.specified=true");

        // Get all the activityList where freeField6 is null
        defaultActivityShouldNotBeFound("freeField6.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 is greater than or equal to DEFAULT_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the activityList where freeField6 is greater than or equal to UPDATED_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.greaterThanOrEqual=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 is less than or equal to DEFAULT_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.lessThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the activityList where freeField6 is less than or equal to SMALLER_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.lessThanOrEqual=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsLessThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 is less than DEFAULT_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.lessThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the activityList where freeField6 is less than UPDATED_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.lessThan=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField6IsGreaterThanSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField6 is greater than DEFAULT_FREE_FIELD_6
        defaultActivityShouldNotBeFound("freeField6.greaterThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the activityList where freeField6 is greater than SMALLER_FREE_FIELD_6
        defaultActivityShouldBeFound("freeField6.greaterThan=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField7IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField7 equals to DEFAULT_FREE_FIELD_7
        defaultActivityShouldBeFound("freeField7.equals=" + DEFAULT_FREE_FIELD_7);

        // Get all the activityList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultActivityShouldNotBeFound("freeField7.equals=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField7IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField7 in DEFAULT_FREE_FIELD_7 or UPDATED_FREE_FIELD_7
        defaultActivityShouldBeFound("freeField7.in=" + DEFAULT_FREE_FIELD_7 + "," + UPDATED_FREE_FIELD_7);

        // Get all the activityList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultActivityShouldNotBeFound("freeField7.in=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField7IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField7 is not null
        defaultActivityShouldBeFound("freeField7.specified=true");

        // Get all the activityList where freeField7 is null
        defaultActivityShouldNotBeFound("freeField7.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField8IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField8 equals to DEFAULT_FREE_FIELD_8
        defaultActivityShouldBeFound("freeField8.equals=" + DEFAULT_FREE_FIELD_8);

        // Get all the activityList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultActivityShouldNotBeFound("freeField8.equals=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField8IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField8 in DEFAULT_FREE_FIELD_8 or UPDATED_FREE_FIELD_8
        defaultActivityShouldBeFound("freeField8.in=" + DEFAULT_FREE_FIELD_8 + "," + UPDATED_FREE_FIELD_8);

        // Get all the activityList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultActivityShouldNotBeFound("freeField8.in=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField8IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField8 is not null
        defaultActivityShouldBeFound("freeField8.specified=true");

        // Get all the activityList where freeField8 is null
        defaultActivityShouldNotBeFound("freeField8.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField9IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField9 equals to DEFAULT_FREE_FIELD_9
        defaultActivityShouldBeFound("freeField9.equals=" + DEFAULT_FREE_FIELD_9);

        // Get all the activityList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultActivityShouldNotBeFound("freeField9.equals=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField9IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField9 in DEFAULT_FREE_FIELD_9 or UPDATED_FREE_FIELD_9
        defaultActivityShouldBeFound("freeField9.in=" + DEFAULT_FREE_FIELD_9 + "," + UPDATED_FREE_FIELD_9);

        // Get all the activityList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultActivityShouldNotBeFound("freeField9.in=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField9IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField9 is not null
        defaultActivityShouldBeFound("freeField9.specified=true");

        // Get all the activityList where freeField9 is null
        defaultActivityShouldNotBeFound("freeField9.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField10IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField10 equals to DEFAULT_FREE_FIELD_10
        defaultActivityShouldBeFound("freeField10.equals=" + DEFAULT_FREE_FIELD_10);

        // Get all the activityList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultActivityShouldNotBeFound("freeField10.equals=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField10IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField10 in DEFAULT_FREE_FIELD_10 or UPDATED_FREE_FIELD_10
        defaultActivityShouldBeFound("freeField10.in=" + DEFAULT_FREE_FIELD_10 + "," + UPDATED_FREE_FIELD_10);

        // Get all the activityList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultActivityShouldNotBeFound("freeField10.in=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField10IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField10 is not null
        defaultActivityShouldBeFound("freeField10.specified=true");

        // Get all the activityList where freeField10 is null
        defaultActivityShouldNotBeFound("freeField10.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField11IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField11 equals to DEFAULT_FREE_FIELD_11
        defaultActivityShouldBeFound("freeField11.equals=" + DEFAULT_FREE_FIELD_11);

        // Get all the activityList where freeField11 equals to UPDATED_FREE_FIELD_11
        defaultActivityShouldNotBeFound("freeField11.equals=" + UPDATED_FREE_FIELD_11);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField11IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField11 in DEFAULT_FREE_FIELD_11 or UPDATED_FREE_FIELD_11
        defaultActivityShouldBeFound("freeField11.in=" + DEFAULT_FREE_FIELD_11 + "," + UPDATED_FREE_FIELD_11);

        // Get all the activityList where freeField11 equals to UPDATED_FREE_FIELD_11
        defaultActivityShouldNotBeFound("freeField11.in=" + UPDATED_FREE_FIELD_11);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField11IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField11 is not null
        defaultActivityShouldBeFound("freeField11.specified=true");

        // Get all the activityList where freeField11 is null
        defaultActivityShouldNotBeFound("freeField11.specified=false");
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField12IsEqualToSomething() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField12 equals to DEFAULT_FREE_FIELD_12
        defaultActivityShouldBeFound("freeField12.equals=" + DEFAULT_FREE_FIELD_12);

        // Get all the activityList where freeField12 equals to UPDATED_FREE_FIELD_12
        defaultActivityShouldNotBeFound("freeField12.equals=" + UPDATED_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField12IsInShouldWork() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField12 in DEFAULT_FREE_FIELD_12 or UPDATED_FREE_FIELD_12
        defaultActivityShouldBeFound("freeField12.in=" + DEFAULT_FREE_FIELD_12 + "," + UPDATED_FREE_FIELD_12);

        // Get all the activityList where freeField12 equals to UPDATED_FREE_FIELD_12
        defaultActivityShouldNotBeFound("freeField12.in=" + UPDATED_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void getAllActivitiesByFreeField12IsNullOrNotNull() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList where freeField12 is not null
        defaultActivityShouldBeFound("freeField12.specified=true");

        // Get all the activityList where freeField12 is null
        defaultActivityShouldNotBeFound("freeField12.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultActivityShouldBeFound(String filter) throws Exception {
        restActivityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activity.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].organizationName").value(hasItem(DEFAULT_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].noOfParticipants").value(hasItem(DEFAULT_NO_OF_PARTICIPANTS.intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].profilePhotoType").value(hasItem(DEFAULT_PROFILE_PHOTO_TYPE)))
            .andExpect(jsonPath("$.[*].signatureImageType").value(hasItem(DEFAULT_SIGNATURE_IMAGE_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField11").value(hasItem(DEFAULT_FREE_FIELD_11.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField12").value(hasItem(DEFAULT_FREE_FIELD_12.booleanValue())));

        // Check, that the count call also returns 1
        restActivityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultActivityShouldNotBeFound(String filter) throws Exception {
        restActivityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restActivityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingActivity() throws Exception {
        // Get the activity
        restActivityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Update the activity
        Activity updatedActivity = activityRepository.findById(activity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedActivity are not directly saved in db
        em.detach(updatedActivity);
        updatedActivity
            .date(UPDATED_DATE)
            .organizationName(UPDATED_ORGANIZATION_NAME)
            .location(UPDATED_LOCATION)
            .activityName(UPDATED_ACTIVITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .noOfParticipants(UPDATED_NO_OF_PARTICIPANTS)
            .time(UPDATED_TIME)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .comments(UPDATED_COMMENTS)
            .profilePhotoType(UPDATED_PROFILE_PHOTO_TYPE)
            .signatureImageType(UPDATED_SIGNATURE_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12);
        ActivityDTO activityDTO = activityMapper.toDto(updatedActivity);

        restActivityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, activityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isOk());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testActivity.getOrganizationName()).isEqualTo(UPDATED_ORGANIZATION_NAME);
        assertThat(testActivity.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testActivity.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
        assertThat(testActivity.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActivity.getNoOfParticipants()).isEqualTo(UPDATED_NO_OF_PARTICIPANTS);
        assertThat(testActivity.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testActivity.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testActivity.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testActivity.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testActivity.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testActivity.getProfilePhotoType()).isEqualTo(UPDATED_PROFILE_PHOTO_TYPE);
        assertThat(testActivity.getSignatureImageType()).isEqualTo(UPDATED_SIGNATURE_IMAGE_TYPE);
        assertThat(testActivity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testActivity.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testActivity.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testActivity.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testActivity.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testActivity.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testActivity.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testActivity.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testActivity.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testActivity.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testActivity.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testActivity.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testActivity.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testActivity.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void putNonExistingActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, activityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(activityDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateActivityWithPatch() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Update the activity using partial update
        Activity partialUpdatedActivity = new Activity();
        partialUpdatedActivity.setId(activity.getId());

        partialUpdatedActivity
            .date(UPDATED_DATE)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .comments(UPDATED_COMMENTS)
            .signatureImageType(UPDATED_SIGNATURE_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);

        restActivityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedActivity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedActivity))
            )
            .andExpect(status().isOk());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testActivity.getOrganizationName()).isEqualTo(DEFAULT_ORGANIZATION_NAME);
        assertThat(testActivity.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testActivity.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
        assertThat(testActivity.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActivity.getNoOfParticipants()).isEqualTo(DEFAULT_NO_OF_PARTICIPANTS);
        assertThat(testActivity.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testActivity.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testActivity.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testActivity.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testActivity.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testActivity.getProfilePhotoType()).isEqualTo(DEFAULT_PROFILE_PHOTO_TYPE);
        assertThat(testActivity.getSignatureImageType()).isEqualTo(UPDATED_SIGNATURE_IMAGE_TYPE);
        assertThat(testActivity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testActivity.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testActivity.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testActivity.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testActivity.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testActivity.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testActivity.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testActivity.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testActivity.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testActivity.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testActivity.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testActivity.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testActivity.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testActivity.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void fullUpdateActivityWithPatch() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Update the activity using partial update
        Activity partialUpdatedActivity = new Activity();
        partialUpdatedActivity.setId(activity.getId());

        partialUpdatedActivity
            .date(UPDATED_DATE)
            .organizationName(UPDATED_ORGANIZATION_NAME)
            .location(UPDATED_LOCATION)
            .activityName(UPDATED_ACTIVITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .noOfParticipants(UPDATED_NO_OF_PARTICIPANTS)
            .time(UPDATED_TIME)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .comments(UPDATED_COMMENTS)
            .profilePhotoType(UPDATED_PROFILE_PHOTO_TYPE)
            .signatureImageType(UPDATED_SIGNATURE_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12);

        restActivityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedActivity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedActivity))
            )
            .andExpect(status().isOk());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testActivity.getOrganizationName()).isEqualTo(UPDATED_ORGANIZATION_NAME);
        assertThat(testActivity.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testActivity.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
        assertThat(testActivity.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActivity.getNoOfParticipants()).isEqualTo(UPDATED_NO_OF_PARTICIPANTS);
        assertThat(testActivity.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testActivity.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testActivity.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testActivity.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testActivity.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testActivity.getProfilePhotoType()).isEqualTo(UPDATED_PROFILE_PHOTO_TYPE);
        assertThat(testActivity.getSignatureImageType()).isEqualTo(UPDATED_SIGNATURE_IMAGE_TYPE);
        assertThat(testActivity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testActivity.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testActivity.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testActivity.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testActivity.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testActivity.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testActivity.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testActivity.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testActivity.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testActivity.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testActivity.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testActivity.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testActivity.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testActivity.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
    }

    @Test
    @Transactional
    void patchNonExistingActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, activityDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();
        activity.setId(count.incrementAndGet());

        // Create the Activity
        ActivityDTO activityDTO = activityMapper.toDto(activity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActivityMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(activityDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        int databaseSizeBeforeDelete = activityRepository.findAll().size();

        // Delete the activity
        restActivityMockMvc
            .perform(delete(ENTITY_API_URL_ID, activity.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
