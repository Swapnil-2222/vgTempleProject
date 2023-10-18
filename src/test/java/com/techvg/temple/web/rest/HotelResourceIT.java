package com.techvg.temple.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.temple.IntegrationTest;
import com.techvg.temple.domain.Hotel;
import com.techvg.temple.repository.HotelRepository;
import com.techvg.temple.service.dto.HotelDTO;
import com.techvg.temple.service.mapper.HotelMapper;
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
 * Integration tests for the {@link HotelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HotelResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final Double DEFAULT_RENT = 1D;
    private static final Double UPDATED_RENT = 2D;
    private static final Double SMALLER_RENT = 1D - 1D;

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/hotels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHotelMockMvc;

    private Hotel hotel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hotel createEntity(EntityManager em) {
        Hotel hotel = new Hotel()
            .date(DEFAULT_DATE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .location(DEFAULT_LOCATION)
            .rent(DEFAULT_RENT)
            .emailId(DEFAULT_EMAIL_ID)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .status(DEFAULT_STATUS)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10);
        return hotel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hotel createUpdatedEntity(EntityManager em) {
        Hotel hotel = new Hotel()
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .location(UPDATED_LOCATION)
            .rent(UPDATED_RENT)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);
        return hotel;
    }

    @BeforeEach
    public void initTest() {
        hotel = createEntity(em);
    }

    @Test
    @Transactional
    void createHotel() throws Exception {
        int databaseSizeBeforeCreate = hotelRepository.findAll().size();
        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);
        restHotelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isCreated());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeCreate + 1);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testHotel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHotel.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHotel.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testHotel.getRent()).isEqualTo(DEFAULT_RENT);
        assertThat(testHotel.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testHotel.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testHotel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHotel.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testHotel.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testHotel.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testHotel.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testHotel.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testHotel.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testHotel.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testHotel.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testHotel.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testHotel.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void createHotelWithExistingId() throws Exception {
        // Create the Hotel with an existing ID
        hotel.setId(1L);
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        int databaseSizeBeforeCreate = hotelRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHotelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHotels() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList
        restHotelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hotel.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].rent").value(hasItem(DEFAULT_RENT.doubleValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())));
    }

    @Test
    @Transactional
    void getHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get the hotel
        restHotelMockMvc
            .perform(get(ENTITY_API_URL_ID, hotel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hotel.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.rent").value(DEFAULT_RENT.doubleValue()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField5").value(DEFAULT_FREE_FIELD_5.intValue()))
            .andExpect(jsonPath("$.freeField6").value(DEFAULT_FREE_FIELD_6.intValue()))
            .andExpect(jsonPath("$.freeField7").value(DEFAULT_FREE_FIELD_7.toString()))
            .andExpect(jsonPath("$.freeField8").value(DEFAULT_FREE_FIELD_8.toString()))
            .andExpect(jsonPath("$.freeField9").value(DEFAULT_FREE_FIELD_9.booleanValue()))
            .andExpect(jsonPath("$.freeField10").value(DEFAULT_FREE_FIELD_10.booleanValue()));
    }

    @Test
    @Transactional
    void getHotelsByIdFiltering() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        Long id = hotel.getId();

        defaultHotelShouldBeFound("id.equals=" + id);
        defaultHotelShouldNotBeFound("id.notEquals=" + id);

        defaultHotelShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHotelShouldNotBeFound("id.greaterThan=" + id);

        defaultHotelShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHotelShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllHotelsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where date equals to DEFAULT_DATE
        defaultHotelShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the hotelList where date equals to UPDATED_DATE
        defaultHotelShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllHotelsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where date in DEFAULT_DATE or UPDATED_DATE
        defaultHotelShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the hotelList where date equals to UPDATED_DATE
        defaultHotelShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllHotelsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where date is not null
        defaultHotelShouldBeFound("date.specified=true");

        // Get all the hotelList where date is null
        defaultHotelShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where name equals to DEFAULT_NAME
        defaultHotelShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the hotelList where name equals to UPDATED_NAME
        defaultHotelShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllHotelsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where name in DEFAULT_NAME or UPDATED_NAME
        defaultHotelShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the hotelList where name equals to UPDATED_NAME
        defaultHotelShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllHotelsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where name is not null
        defaultHotelShouldBeFound("name.specified=true");

        // Get all the hotelList where name is null
        defaultHotelShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByNameContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where name contains DEFAULT_NAME
        defaultHotelShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the hotelList where name contains UPDATED_NAME
        defaultHotelShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllHotelsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where name does not contain DEFAULT_NAME
        defaultHotelShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the hotelList where name does not contain UPDATED_NAME
        defaultHotelShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllHotelsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where description equals to DEFAULT_DESCRIPTION
        defaultHotelShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the hotelList where description equals to UPDATED_DESCRIPTION
        defaultHotelShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllHotelsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultHotelShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the hotelList where description equals to UPDATED_DESCRIPTION
        defaultHotelShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllHotelsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where description is not null
        defaultHotelShouldBeFound("description.specified=true");

        // Get all the hotelList where description is null
        defaultHotelShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where description contains DEFAULT_DESCRIPTION
        defaultHotelShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the hotelList where description contains UPDATED_DESCRIPTION
        defaultHotelShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllHotelsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where description does not contain DEFAULT_DESCRIPTION
        defaultHotelShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the hotelList where description does not contain UPDATED_DESCRIPTION
        defaultHotelShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllHotelsByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where location equals to DEFAULT_LOCATION
        defaultHotelShouldBeFound("location.equals=" + DEFAULT_LOCATION);

        // Get all the hotelList where location equals to UPDATED_LOCATION
        defaultHotelShouldNotBeFound("location.equals=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllHotelsByLocationIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where location in DEFAULT_LOCATION or UPDATED_LOCATION
        defaultHotelShouldBeFound("location.in=" + DEFAULT_LOCATION + "," + UPDATED_LOCATION);

        // Get all the hotelList where location equals to UPDATED_LOCATION
        defaultHotelShouldNotBeFound("location.in=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllHotelsByLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where location is not null
        defaultHotelShouldBeFound("location.specified=true");

        // Get all the hotelList where location is null
        defaultHotelShouldNotBeFound("location.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByLocationContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where location contains DEFAULT_LOCATION
        defaultHotelShouldBeFound("location.contains=" + DEFAULT_LOCATION);

        // Get all the hotelList where location contains UPDATED_LOCATION
        defaultHotelShouldNotBeFound("location.contains=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllHotelsByLocationNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where location does not contain DEFAULT_LOCATION
        defaultHotelShouldNotBeFound("location.doesNotContain=" + DEFAULT_LOCATION);

        // Get all the hotelList where location does not contain UPDATED_LOCATION
        defaultHotelShouldBeFound("location.doesNotContain=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent equals to DEFAULT_RENT
        defaultHotelShouldBeFound("rent.equals=" + DEFAULT_RENT);

        // Get all the hotelList where rent equals to UPDATED_RENT
        defaultHotelShouldNotBeFound("rent.equals=" + UPDATED_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent in DEFAULT_RENT or UPDATED_RENT
        defaultHotelShouldBeFound("rent.in=" + DEFAULT_RENT + "," + UPDATED_RENT);

        // Get all the hotelList where rent equals to UPDATED_RENT
        defaultHotelShouldNotBeFound("rent.in=" + UPDATED_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent is not null
        defaultHotelShouldBeFound("rent.specified=true");

        // Get all the hotelList where rent is null
        defaultHotelShouldNotBeFound("rent.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent is greater than or equal to DEFAULT_RENT
        defaultHotelShouldBeFound("rent.greaterThanOrEqual=" + DEFAULT_RENT);

        // Get all the hotelList where rent is greater than or equal to UPDATED_RENT
        defaultHotelShouldNotBeFound("rent.greaterThanOrEqual=" + UPDATED_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent is less than or equal to DEFAULT_RENT
        defaultHotelShouldBeFound("rent.lessThanOrEqual=" + DEFAULT_RENT);

        // Get all the hotelList where rent is less than or equal to SMALLER_RENT
        defaultHotelShouldNotBeFound("rent.lessThanOrEqual=" + SMALLER_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsLessThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent is less than DEFAULT_RENT
        defaultHotelShouldNotBeFound("rent.lessThan=" + DEFAULT_RENT);

        // Get all the hotelList where rent is less than UPDATED_RENT
        defaultHotelShouldBeFound("rent.lessThan=" + UPDATED_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByRentIsGreaterThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where rent is greater than DEFAULT_RENT
        defaultHotelShouldNotBeFound("rent.greaterThan=" + DEFAULT_RENT);

        // Get all the hotelList where rent is greater than SMALLER_RENT
        defaultHotelShouldBeFound("rent.greaterThan=" + SMALLER_RENT);
    }

    @Test
    @Transactional
    void getAllHotelsByEmailIdIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where emailId equals to DEFAULT_EMAIL_ID
        defaultHotelShouldBeFound("emailId.equals=" + DEFAULT_EMAIL_ID);

        // Get all the hotelList where emailId equals to UPDATED_EMAIL_ID
        defaultHotelShouldNotBeFound("emailId.equals=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllHotelsByEmailIdIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where emailId in DEFAULT_EMAIL_ID or UPDATED_EMAIL_ID
        defaultHotelShouldBeFound("emailId.in=" + DEFAULT_EMAIL_ID + "," + UPDATED_EMAIL_ID);

        // Get all the hotelList where emailId equals to UPDATED_EMAIL_ID
        defaultHotelShouldNotBeFound("emailId.in=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllHotelsByEmailIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where emailId is not null
        defaultHotelShouldBeFound("emailId.specified=true");

        // Get all the hotelList where emailId is null
        defaultHotelShouldNotBeFound("emailId.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByEmailIdContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where emailId contains DEFAULT_EMAIL_ID
        defaultHotelShouldBeFound("emailId.contains=" + DEFAULT_EMAIL_ID);

        // Get all the hotelList where emailId contains UPDATED_EMAIL_ID
        defaultHotelShouldNotBeFound("emailId.contains=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllHotelsByEmailIdNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where emailId does not contain DEFAULT_EMAIL_ID
        defaultHotelShouldNotBeFound("emailId.doesNotContain=" + DEFAULT_EMAIL_ID);

        // Get all the hotelList where emailId does not contain UPDATED_EMAIL_ID
        defaultHotelShouldBeFound("emailId.doesNotContain=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllHotelsByContactPersonIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where contactPerson equals to DEFAULT_CONTACT_PERSON
        defaultHotelShouldBeFound("contactPerson.equals=" + DEFAULT_CONTACT_PERSON);

        // Get all the hotelList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultHotelShouldNotBeFound("contactPerson.equals=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllHotelsByContactPersonIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where contactPerson in DEFAULT_CONTACT_PERSON or UPDATED_CONTACT_PERSON
        defaultHotelShouldBeFound("contactPerson.in=" + DEFAULT_CONTACT_PERSON + "," + UPDATED_CONTACT_PERSON);

        // Get all the hotelList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultHotelShouldNotBeFound("contactPerson.in=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllHotelsByContactPersonIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where contactPerson is not null
        defaultHotelShouldBeFound("contactPerson.specified=true");

        // Get all the hotelList where contactPerson is null
        defaultHotelShouldNotBeFound("contactPerson.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByContactPersonContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where contactPerson contains DEFAULT_CONTACT_PERSON
        defaultHotelShouldBeFound("contactPerson.contains=" + DEFAULT_CONTACT_PERSON);

        // Get all the hotelList where contactPerson contains UPDATED_CONTACT_PERSON
        defaultHotelShouldNotBeFound("contactPerson.contains=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllHotelsByContactPersonNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where contactPerson does not contain DEFAULT_CONTACT_PERSON
        defaultHotelShouldNotBeFound("contactPerson.doesNotContain=" + DEFAULT_CONTACT_PERSON);

        // Get all the hotelList where contactPerson does not contain UPDATED_CONTACT_PERSON
        defaultHotelShouldBeFound("contactPerson.doesNotContain=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllHotelsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where status equals to DEFAULT_STATUS
        defaultHotelShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the hotelList where status equals to UPDATED_STATUS
        defaultHotelShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllHotelsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultHotelShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the hotelList where status equals to UPDATED_STATUS
        defaultHotelShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllHotelsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where status is not null
        defaultHotelShouldBeFound("status.specified=true");

        // Get all the hotelList where status is null
        defaultHotelShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByStatusContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where status contains DEFAULT_STATUS
        defaultHotelShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the hotelList where status contains UPDATED_STATUS
        defaultHotelShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllHotelsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where status does not contain DEFAULT_STATUS
        defaultHotelShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the hotelList where status does not contain UPDATED_STATUS
        defaultHotelShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultHotelShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the hotelList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultHotelShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultHotelShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the hotelList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultHotelShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModified is not null
        defaultHotelShouldBeFound("lastModified.specified=true");

        // Get all the hotelList where lastModified is null
        defaultHotelShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultHotelShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the hotelList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultHotelShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultHotelShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the hotelList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultHotelShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModifiedBy is not null
        defaultHotelShouldBeFound("lastModifiedBy.specified=true");

        // Get all the hotelList where lastModifiedBy is null
        defaultHotelShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultHotelShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the hotelList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultHotelShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllHotelsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultHotelShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the hotelList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultHotelShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultHotelShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the hotelList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultHotelShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultHotelShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the hotelList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultHotelShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField1 is not null
        defaultHotelShouldBeFound("freeField1.specified=true");

        // Get all the hotelList where freeField1 is null
        defaultHotelShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultHotelShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the hotelList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultHotelShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultHotelShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the hotelList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultHotelShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultHotelShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the hotelList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultHotelShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultHotelShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the hotelList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultHotelShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField2 is not null
        defaultHotelShouldBeFound("freeField2.specified=true");

        // Get all the hotelList where freeField2 is null
        defaultHotelShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultHotelShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the hotelList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultHotelShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultHotelShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the hotelList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultHotelShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 equals to DEFAULT_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.equals=" + DEFAULT_FREE_FIELD_5);

        // Get all the hotelList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.equals=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 in DEFAULT_FREE_FIELD_5 or UPDATED_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.in=" + DEFAULT_FREE_FIELD_5 + "," + UPDATED_FREE_FIELD_5);

        // Get all the hotelList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.in=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 is not null
        defaultHotelShouldBeFound("freeField5.specified=true");

        // Get all the hotelList where freeField5 is null
        defaultHotelShouldNotBeFound("freeField5.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 is greater than or equal to DEFAULT_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the hotelList where freeField5 is greater than or equal to UPDATED_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.greaterThanOrEqual=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 is less than or equal to DEFAULT_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.lessThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the hotelList where freeField5 is less than or equal to SMALLER_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.lessThanOrEqual=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsLessThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 is less than DEFAULT_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.lessThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the hotelList where freeField5 is less than UPDATED_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.lessThan=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField5IsGreaterThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField5 is greater than DEFAULT_FREE_FIELD_5
        defaultHotelShouldNotBeFound("freeField5.greaterThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the hotelList where freeField5 is greater than SMALLER_FREE_FIELD_5
        defaultHotelShouldBeFound("freeField5.greaterThan=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 equals to DEFAULT_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.equals=" + DEFAULT_FREE_FIELD_6);

        // Get all the hotelList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.equals=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 in DEFAULT_FREE_FIELD_6 or UPDATED_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.in=" + DEFAULT_FREE_FIELD_6 + "," + UPDATED_FREE_FIELD_6);

        // Get all the hotelList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.in=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 is not null
        defaultHotelShouldBeFound("freeField6.specified=true");

        // Get all the hotelList where freeField6 is null
        defaultHotelShouldNotBeFound("freeField6.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 is greater than or equal to DEFAULT_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the hotelList where freeField6 is greater than or equal to UPDATED_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.greaterThanOrEqual=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 is less than or equal to DEFAULT_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.lessThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the hotelList where freeField6 is less than or equal to SMALLER_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.lessThanOrEqual=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsLessThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 is less than DEFAULT_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.lessThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the hotelList where freeField6 is less than UPDATED_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.lessThan=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField6IsGreaterThanSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField6 is greater than DEFAULT_FREE_FIELD_6
        defaultHotelShouldNotBeFound("freeField6.greaterThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the hotelList where freeField6 is greater than SMALLER_FREE_FIELD_6
        defaultHotelShouldBeFound("freeField6.greaterThan=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField7IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField7 equals to DEFAULT_FREE_FIELD_7
        defaultHotelShouldBeFound("freeField7.equals=" + DEFAULT_FREE_FIELD_7);

        // Get all the hotelList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultHotelShouldNotBeFound("freeField7.equals=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField7IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField7 in DEFAULT_FREE_FIELD_7 or UPDATED_FREE_FIELD_7
        defaultHotelShouldBeFound("freeField7.in=" + DEFAULT_FREE_FIELD_7 + "," + UPDATED_FREE_FIELD_7);

        // Get all the hotelList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultHotelShouldNotBeFound("freeField7.in=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField7IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField7 is not null
        defaultHotelShouldBeFound("freeField7.specified=true");

        // Get all the hotelList where freeField7 is null
        defaultHotelShouldNotBeFound("freeField7.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField8IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField8 equals to DEFAULT_FREE_FIELD_8
        defaultHotelShouldBeFound("freeField8.equals=" + DEFAULT_FREE_FIELD_8);

        // Get all the hotelList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultHotelShouldNotBeFound("freeField8.equals=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField8IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField8 in DEFAULT_FREE_FIELD_8 or UPDATED_FREE_FIELD_8
        defaultHotelShouldBeFound("freeField8.in=" + DEFAULT_FREE_FIELD_8 + "," + UPDATED_FREE_FIELD_8);

        // Get all the hotelList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultHotelShouldNotBeFound("freeField8.in=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField8IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField8 is not null
        defaultHotelShouldBeFound("freeField8.specified=true");

        // Get all the hotelList where freeField8 is null
        defaultHotelShouldNotBeFound("freeField8.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField9IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField9 equals to DEFAULT_FREE_FIELD_9
        defaultHotelShouldBeFound("freeField9.equals=" + DEFAULT_FREE_FIELD_9);

        // Get all the hotelList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultHotelShouldNotBeFound("freeField9.equals=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField9IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField9 in DEFAULT_FREE_FIELD_9 or UPDATED_FREE_FIELD_9
        defaultHotelShouldBeFound("freeField9.in=" + DEFAULT_FREE_FIELD_9 + "," + UPDATED_FREE_FIELD_9);

        // Get all the hotelList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultHotelShouldNotBeFound("freeField9.in=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField9IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField9 is not null
        defaultHotelShouldBeFound("freeField9.specified=true");

        // Get all the hotelList where freeField9 is null
        defaultHotelShouldNotBeFound("freeField9.specified=false");
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField10IsEqualToSomething() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField10 equals to DEFAULT_FREE_FIELD_10
        defaultHotelShouldBeFound("freeField10.equals=" + DEFAULT_FREE_FIELD_10);

        // Get all the hotelList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultHotelShouldNotBeFound("freeField10.equals=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField10IsInShouldWork() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField10 in DEFAULT_FREE_FIELD_10 or UPDATED_FREE_FIELD_10
        defaultHotelShouldBeFound("freeField10.in=" + DEFAULT_FREE_FIELD_10 + "," + UPDATED_FREE_FIELD_10);

        // Get all the hotelList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultHotelShouldNotBeFound("freeField10.in=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllHotelsByFreeField10IsNullOrNotNull() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList where freeField10 is not null
        defaultHotelShouldBeFound("freeField10.specified=true");

        // Get all the hotelList where freeField10 is null
        defaultHotelShouldNotBeFound("freeField10.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHotelShouldBeFound(String filter) throws Exception {
        restHotelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hotel.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].rent").value(hasItem(DEFAULT_RENT.doubleValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField5").value(hasItem(DEFAULT_FREE_FIELD_5.intValue())))
            .andExpect(jsonPath("$.[*].freeField6").value(hasItem(DEFAULT_FREE_FIELD_6.intValue())))
            .andExpect(jsonPath("$.[*].freeField7").value(hasItem(DEFAULT_FREE_FIELD_7.toString())))
            .andExpect(jsonPath("$.[*].freeField8").value(hasItem(DEFAULT_FREE_FIELD_8.toString())))
            .andExpect(jsonPath("$.[*].freeField9").value(hasItem(DEFAULT_FREE_FIELD_9.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField10").value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue())));

        // Check, that the count call also returns 1
        restHotelMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHotelShouldNotBeFound(String filter) throws Exception {
        restHotelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHotelMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingHotel() throws Exception {
        // Get the hotel
        restHotelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();

        // Update the hotel
        Hotel updatedHotel = hotelRepository.findById(hotel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHotel are not directly saved in db
        em.detach(updatedHotel);
        updatedHotel
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .location(UPDATED_LOCATION)
            .rent(UPDATED_RENT)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);
        HotelDTO hotelDTO = hotelMapper.toDto(updatedHotel);

        restHotelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hotelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hotelDTO))
            )
            .andExpect(status().isOk());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHotel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHotel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHotel.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testHotel.getRent()).isEqualTo(UPDATED_RENT);
        assertThat(testHotel.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testHotel.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testHotel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHotel.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testHotel.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHotel.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testHotel.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testHotel.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testHotel.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testHotel.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testHotel.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testHotel.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testHotel.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void putNonExistingHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hotelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hotelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hotelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHotelWithPatch() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();

        // Update the hotel using partial update
        Hotel partialUpdatedHotel = new Hotel();
        partialUpdatedHotel.setId(hotel.getId());

        partialUpdatedHotel
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .lastModified(UPDATED_LAST_MODIFIED)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField10(UPDATED_FREE_FIELD_10);

        restHotelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHotel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHotel))
            )
            .andExpect(status().isOk());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHotel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHotel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHotel.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testHotel.getRent()).isEqualTo(DEFAULT_RENT);
        assertThat(testHotel.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testHotel.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testHotel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHotel.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testHotel.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testHotel.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testHotel.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testHotel.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testHotel.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testHotel.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testHotel.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testHotel.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testHotel.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void fullUpdateHotelWithPatch() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();

        // Update the hotel using partial update
        Hotel partialUpdatedHotel = new Hotel();
        partialUpdatedHotel.setId(hotel.getId());

        partialUpdatedHotel
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .location(UPDATED_LOCATION)
            .rent(UPDATED_RENT)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .status(UPDATED_STATUS)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10);

        restHotelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHotel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHotel))
            )
            .andExpect(status().isOk());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHotel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHotel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHotel.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testHotel.getRent()).isEqualTo(UPDATED_RENT);
        assertThat(testHotel.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testHotel.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testHotel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHotel.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testHotel.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHotel.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testHotel.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testHotel.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testHotel.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testHotel.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testHotel.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testHotel.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testHotel.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void patchNonExistingHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hotelDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hotelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hotelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();
        hotel.setId(count.incrementAndGet());

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHotelMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeDelete = hotelRepository.findAll().size();

        // Delete the hotel
        restHotelMockMvc
            .perform(delete(ENTITY_API_URL_ID, hotel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
