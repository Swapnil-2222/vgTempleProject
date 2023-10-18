package com.techvg.temple.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.temple.IntegrationTest;
import com.techvg.temple.domain.Shop;
import com.techvg.temple.repository.ShopRepository;
import com.techvg.temple.service.dto.ShopDTO;
import com.techvg.temple.service.mapper.ShopMapper;
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
 * Integration tests for the {@link ShopResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShopResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_SIZE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;
    private static final Double SMALLER_PRICE = 1D - 1D;

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

    private static final String ENTITY_API_URL = "/api/shops";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopMockMvc;

    private Shop shop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createEntity(EntityManager em) {
        Shop shop = new Shop()
            .date(DEFAULT_DATE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .product(DEFAULT_PRODUCT)
            .productSize(DEFAULT_PRODUCT_SIZE)
            .price(DEFAULT_PRICE)
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
        return shop;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createUpdatedEntity(EntityManager em) {
        Shop shop = new Shop()
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .product(UPDATED_PRODUCT)
            .productSize(UPDATED_PRODUCT_SIZE)
            .price(UPDATED_PRICE)
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
        return shop;
    }

    @BeforeEach
    public void initTest() {
        shop = createEntity(em);
    }

    @Test
    @Transactional
    void createShop() throws Exception {
        int databaseSizeBeforeCreate = shopRepository.findAll().size();
        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);
        restShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isCreated());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate + 1);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testShop.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShop.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShop.getProduct()).isEqualTo(DEFAULT_PRODUCT);
        assertThat(testShop.getProductSize()).isEqualTo(DEFAULT_PRODUCT_SIZE);
        assertThat(testShop.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testShop.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testShop.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testShop.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testShop.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testShop.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testShop.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testShop.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testShop.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testShop.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testShop.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testShop.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testShop.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void createShopWithExistingId() throws Exception {
        // Create the Shop with an existing ID
        shop.setId(1L);
        ShopDTO shopDTO = shopMapper.toDto(shop);

        int databaseSizeBeforeCreate = shopRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllShops() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shop.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT)))
            .andExpect(jsonPath("$.[*].productSize").value(hasItem(DEFAULT_PRODUCT_SIZE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
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
    void getShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get the shop
        restShopMockMvc
            .perform(get(ENTITY_API_URL_ID, shop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shop.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.product").value(DEFAULT_PRODUCT))
            .andExpect(jsonPath("$.productSize").value(DEFAULT_PRODUCT_SIZE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
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
    void getShopsByIdFiltering() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        Long id = shop.getId();

        defaultShopShouldBeFound("id.equals=" + id);
        defaultShopShouldNotBeFound("id.notEquals=" + id);

        defaultShopShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultShopShouldNotBeFound("id.greaterThan=" + id);

        defaultShopShouldBeFound("id.lessThanOrEqual=" + id);
        defaultShopShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllShopsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where date equals to DEFAULT_DATE
        defaultShopShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the shopList where date equals to UPDATED_DATE
        defaultShopShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllShopsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where date in DEFAULT_DATE or UPDATED_DATE
        defaultShopShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the shopList where date equals to UPDATED_DATE
        defaultShopShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllShopsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where date is not null
        defaultShopShouldBeFound("date.specified=true");

        // Get all the shopList where date is null
        defaultShopShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where name equals to DEFAULT_NAME
        defaultShopShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the shopList where name equals to UPDATED_NAME
        defaultShopShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllShopsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where name in DEFAULT_NAME or UPDATED_NAME
        defaultShopShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the shopList where name equals to UPDATED_NAME
        defaultShopShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllShopsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where name is not null
        defaultShopShouldBeFound("name.specified=true");

        // Get all the shopList where name is null
        defaultShopShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByNameContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where name contains DEFAULT_NAME
        defaultShopShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the shopList where name contains UPDATED_NAME
        defaultShopShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllShopsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where name does not contain DEFAULT_NAME
        defaultShopShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the shopList where name does not contain UPDATED_NAME
        defaultShopShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllShopsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where description equals to DEFAULT_DESCRIPTION
        defaultShopShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the shopList where description equals to UPDATED_DESCRIPTION
        defaultShopShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllShopsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultShopShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the shopList where description equals to UPDATED_DESCRIPTION
        defaultShopShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllShopsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where description is not null
        defaultShopShouldBeFound("description.specified=true");

        // Get all the shopList where description is null
        defaultShopShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where description contains DEFAULT_DESCRIPTION
        defaultShopShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the shopList where description contains UPDATED_DESCRIPTION
        defaultShopShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllShopsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where description does not contain DEFAULT_DESCRIPTION
        defaultShopShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the shopList where description does not contain UPDATED_DESCRIPTION
        defaultShopShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllShopsByProductIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where product equals to DEFAULT_PRODUCT
        defaultShopShouldBeFound("product.equals=" + DEFAULT_PRODUCT);

        // Get all the shopList where product equals to UPDATED_PRODUCT
        defaultShopShouldNotBeFound("product.equals=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    void getAllShopsByProductIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where product in DEFAULT_PRODUCT or UPDATED_PRODUCT
        defaultShopShouldBeFound("product.in=" + DEFAULT_PRODUCT + "," + UPDATED_PRODUCT);

        // Get all the shopList where product equals to UPDATED_PRODUCT
        defaultShopShouldNotBeFound("product.in=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    void getAllShopsByProductIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where product is not null
        defaultShopShouldBeFound("product.specified=true");

        // Get all the shopList where product is null
        defaultShopShouldNotBeFound("product.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByProductContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where product contains DEFAULT_PRODUCT
        defaultShopShouldBeFound("product.contains=" + DEFAULT_PRODUCT);

        // Get all the shopList where product contains UPDATED_PRODUCT
        defaultShopShouldNotBeFound("product.contains=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    void getAllShopsByProductNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where product does not contain DEFAULT_PRODUCT
        defaultShopShouldNotBeFound("product.doesNotContain=" + DEFAULT_PRODUCT);

        // Get all the shopList where product does not contain UPDATED_PRODUCT
        defaultShopShouldBeFound("product.doesNotContain=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    void getAllShopsByProductSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where productSize equals to DEFAULT_PRODUCT_SIZE
        defaultShopShouldBeFound("productSize.equals=" + DEFAULT_PRODUCT_SIZE);

        // Get all the shopList where productSize equals to UPDATED_PRODUCT_SIZE
        defaultShopShouldNotBeFound("productSize.equals=" + UPDATED_PRODUCT_SIZE);
    }

    @Test
    @Transactional
    void getAllShopsByProductSizeIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where productSize in DEFAULT_PRODUCT_SIZE or UPDATED_PRODUCT_SIZE
        defaultShopShouldBeFound("productSize.in=" + DEFAULT_PRODUCT_SIZE + "," + UPDATED_PRODUCT_SIZE);

        // Get all the shopList where productSize equals to UPDATED_PRODUCT_SIZE
        defaultShopShouldNotBeFound("productSize.in=" + UPDATED_PRODUCT_SIZE);
    }

    @Test
    @Transactional
    void getAllShopsByProductSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where productSize is not null
        defaultShopShouldBeFound("productSize.specified=true");

        // Get all the shopList where productSize is null
        defaultShopShouldNotBeFound("productSize.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByProductSizeContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where productSize contains DEFAULT_PRODUCT_SIZE
        defaultShopShouldBeFound("productSize.contains=" + DEFAULT_PRODUCT_SIZE);

        // Get all the shopList where productSize contains UPDATED_PRODUCT_SIZE
        defaultShopShouldNotBeFound("productSize.contains=" + UPDATED_PRODUCT_SIZE);
    }

    @Test
    @Transactional
    void getAllShopsByProductSizeNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where productSize does not contain DEFAULT_PRODUCT_SIZE
        defaultShopShouldNotBeFound("productSize.doesNotContain=" + DEFAULT_PRODUCT_SIZE);

        // Get all the shopList where productSize does not contain UPDATED_PRODUCT_SIZE
        defaultShopShouldBeFound("productSize.doesNotContain=" + UPDATED_PRODUCT_SIZE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price equals to DEFAULT_PRICE
        defaultShopShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the shopList where price equals to UPDATED_PRICE
        defaultShopShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultShopShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the shopList where price equals to UPDATED_PRICE
        defaultShopShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price is not null
        defaultShopShouldBeFound("price.specified=true");

        // Get all the shopList where price is null
        defaultShopShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price is greater than or equal to DEFAULT_PRICE
        defaultShopShouldBeFound("price.greaterThanOrEqual=" + DEFAULT_PRICE);

        // Get all the shopList where price is greater than or equal to UPDATED_PRICE
        defaultShopShouldNotBeFound("price.greaterThanOrEqual=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price is less than or equal to DEFAULT_PRICE
        defaultShopShouldBeFound("price.lessThanOrEqual=" + DEFAULT_PRICE);

        // Get all the shopList where price is less than or equal to SMALLER_PRICE
        defaultShopShouldNotBeFound("price.lessThanOrEqual=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price is less than DEFAULT_PRICE
        defaultShopShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the shopList where price is less than UPDATED_PRICE
        defaultShopShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where price is greater than DEFAULT_PRICE
        defaultShopShouldNotBeFound("price.greaterThan=" + DEFAULT_PRICE);

        // Get all the shopList where price is greater than SMALLER_PRICE
        defaultShopShouldBeFound("price.greaterThan=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllShopsByEmailIdIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where emailId equals to DEFAULT_EMAIL_ID
        defaultShopShouldBeFound("emailId.equals=" + DEFAULT_EMAIL_ID);

        // Get all the shopList where emailId equals to UPDATED_EMAIL_ID
        defaultShopShouldNotBeFound("emailId.equals=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllShopsByEmailIdIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where emailId in DEFAULT_EMAIL_ID or UPDATED_EMAIL_ID
        defaultShopShouldBeFound("emailId.in=" + DEFAULT_EMAIL_ID + "," + UPDATED_EMAIL_ID);

        // Get all the shopList where emailId equals to UPDATED_EMAIL_ID
        defaultShopShouldNotBeFound("emailId.in=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllShopsByEmailIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where emailId is not null
        defaultShopShouldBeFound("emailId.specified=true");

        // Get all the shopList where emailId is null
        defaultShopShouldNotBeFound("emailId.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByEmailIdContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where emailId contains DEFAULT_EMAIL_ID
        defaultShopShouldBeFound("emailId.contains=" + DEFAULT_EMAIL_ID);

        // Get all the shopList where emailId contains UPDATED_EMAIL_ID
        defaultShopShouldNotBeFound("emailId.contains=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllShopsByEmailIdNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where emailId does not contain DEFAULT_EMAIL_ID
        defaultShopShouldNotBeFound("emailId.doesNotContain=" + DEFAULT_EMAIL_ID);

        // Get all the shopList where emailId does not contain UPDATED_EMAIL_ID
        defaultShopShouldBeFound("emailId.doesNotContain=" + UPDATED_EMAIL_ID);
    }

    @Test
    @Transactional
    void getAllShopsByContactPersonIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where contactPerson equals to DEFAULT_CONTACT_PERSON
        defaultShopShouldBeFound("contactPerson.equals=" + DEFAULT_CONTACT_PERSON);

        // Get all the shopList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultShopShouldNotBeFound("contactPerson.equals=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllShopsByContactPersonIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where contactPerson in DEFAULT_CONTACT_PERSON or UPDATED_CONTACT_PERSON
        defaultShopShouldBeFound("contactPerson.in=" + DEFAULT_CONTACT_PERSON + "," + UPDATED_CONTACT_PERSON);

        // Get all the shopList where contactPerson equals to UPDATED_CONTACT_PERSON
        defaultShopShouldNotBeFound("contactPerson.in=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllShopsByContactPersonIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where contactPerson is not null
        defaultShopShouldBeFound("contactPerson.specified=true");

        // Get all the shopList where contactPerson is null
        defaultShopShouldNotBeFound("contactPerson.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByContactPersonContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where contactPerson contains DEFAULT_CONTACT_PERSON
        defaultShopShouldBeFound("contactPerson.contains=" + DEFAULT_CONTACT_PERSON);

        // Get all the shopList where contactPerson contains UPDATED_CONTACT_PERSON
        defaultShopShouldNotBeFound("contactPerson.contains=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllShopsByContactPersonNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where contactPerson does not contain DEFAULT_CONTACT_PERSON
        defaultShopShouldNotBeFound("contactPerson.doesNotContain=" + DEFAULT_CONTACT_PERSON);

        // Get all the shopList where contactPerson does not contain UPDATED_CONTACT_PERSON
        defaultShopShouldBeFound("contactPerson.doesNotContain=" + UPDATED_CONTACT_PERSON);
    }

    @Test
    @Transactional
    void getAllShopsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where status equals to DEFAULT_STATUS
        defaultShopShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the shopList where status equals to UPDATED_STATUS
        defaultShopShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllShopsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultShopShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the shopList where status equals to UPDATED_STATUS
        defaultShopShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllShopsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where status is not null
        defaultShopShouldBeFound("status.specified=true");

        // Get all the shopList where status is null
        defaultShopShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByStatusContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where status contains DEFAULT_STATUS
        defaultShopShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the shopList where status contains UPDATED_STATUS
        defaultShopShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllShopsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where status does not contain DEFAULT_STATUS
        defaultShopShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the shopList where status does not contain UPDATED_STATUS
        defaultShopShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultShopShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the shopList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultShopShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultShopShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the shopList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultShopShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModified is not null
        defaultShopShouldBeFound("lastModified.specified=true");

        // Get all the shopList where lastModified is null
        defaultShopShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultShopShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the shopList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultShopShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultShopShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the shopList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultShopShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModifiedBy is not null
        defaultShopShouldBeFound("lastModifiedBy.specified=true");

        // Get all the shopList where lastModifiedBy is null
        defaultShopShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultShopShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the shopList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultShopShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllShopsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultShopShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the shopList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultShopShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultShopShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the shopList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultShopShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultShopShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the shopList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultShopShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField1 is not null
        defaultShopShouldBeFound("freeField1.specified=true");

        // Get all the shopList where freeField1 is null
        defaultShopShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultShopShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the shopList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultShopShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultShopShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the shopList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultShopShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultShopShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the shopList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultShopShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultShopShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the shopList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultShopShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField2 is not null
        defaultShopShouldBeFound("freeField2.specified=true");

        // Get all the shopList where freeField2 is null
        defaultShopShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultShopShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the shopList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultShopShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultShopShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the shopList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultShopShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 equals to DEFAULT_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.equals=" + DEFAULT_FREE_FIELD_5);

        // Get all the shopList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.equals=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 in DEFAULT_FREE_FIELD_5 or UPDATED_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.in=" + DEFAULT_FREE_FIELD_5 + "," + UPDATED_FREE_FIELD_5);

        // Get all the shopList where freeField5 equals to UPDATED_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.in=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 is not null
        defaultShopShouldBeFound("freeField5.specified=true");

        // Get all the shopList where freeField5 is null
        defaultShopShouldNotBeFound("freeField5.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 is greater than or equal to DEFAULT_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the shopList where freeField5 is greater than or equal to UPDATED_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.greaterThanOrEqual=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 is less than or equal to DEFAULT_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.lessThanOrEqual=" + DEFAULT_FREE_FIELD_5);

        // Get all the shopList where freeField5 is less than or equal to SMALLER_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.lessThanOrEqual=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsLessThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 is less than DEFAULT_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.lessThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the shopList where freeField5 is less than UPDATED_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.lessThan=" + UPDATED_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField5IsGreaterThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField5 is greater than DEFAULT_FREE_FIELD_5
        defaultShopShouldNotBeFound("freeField5.greaterThan=" + DEFAULT_FREE_FIELD_5);

        // Get all the shopList where freeField5 is greater than SMALLER_FREE_FIELD_5
        defaultShopShouldBeFound("freeField5.greaterThan=" + SMALLER_FREE_FIELD_5);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 equals to DEFAULT_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.equals=" + DEFAULT_FREE_FIELD_6);

        // Get all the shopList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.equals=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 in DEFAULT_FREE_FIELD_6 or UPDATED_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.in=" + DEFAULT_FREE_FIELD_6 + "," + UPDATED_FREE_FIELD_6);

        // Get all the shopList where freeField6 equals to UPDATED_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.in=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 is not null
        defaultShopShouldBeFound("freeField6.specified=true");

        // Get all the shopList where freeField6 is null
        defaultShopShouldNotBeFound("freeField6.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 is greater than or equal to DEFAULT_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.greaterThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the shopList where freeField6 is greater than or equal to UPDATED_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.greaterThanOrEqual=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 is less than or equal to DEFAULT_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.lessThanOrEqual=" + DEFAULT_FREE_FIELD_6);

        // Get all the shopList where freeField6 is less than or equal to SMALLER_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.lessThanOrEqual=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsLessThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 is less than DEFAULT_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.lessThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the shopList where freeField6 is less than UPDATED_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.lessThan=" + UPDATED_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField6IsGreaterThanSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField6 is greater than DEFAULT_FREE_FIELD_6
        defaultShopShouldNotBeFound("freeField6.greaterThan=" + DEFAULT_FREE_FIELD_6);

        // Get all the shopList where freeField6 is greater than SMALLER_FREE_FIELD_6
        defaultShopShouldBeFound("freeField6.greaterThan=" + SMALLER_FREE_FIELD_6);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField7IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField7 equals to DEFAULT_FREE_FIELD_7
        defaultShopShouldBeFound("freeField7.equals=" + DEFAULT_FREE_FIELD_7);

        // Get all the shopList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultShopShouldNotBeFound("freeField7.equals=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField7IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField7 in DEFAULT_FREE_FIELD_7 or UPDATED_FREE_FIELD_7
        defaultShopShouldBeFound("freeField7.in=" + DEFAULT_FREE_FIELD_7 + "," + UPDATED_FREE_FIELD_7);

        // Get all the shopList where freeField7 equals to UPDATED_FREE_FIELD_7
        defaultShopShouldNotBeFound("freeField7.in=" + UPDATED_FREE_FIELD_7);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField7IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField7 is not null
        defaultShopShouldBeFound("freeField7.specified=true");

        // Get all the shopList where freeField7 is null
        defaultShopShouldNotBeFound("freeField7.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField8IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField8 equals to DEFAULT_FREE_FIELD_8
        defaultShopShouldBeFound("freeField8.equals=" + DEFAULT_FREE_FIELD_8);

        // Get all the shopList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultShopShouldNotBeFound("freeField8.equals=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField8IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField8 in DEFAULT_FREE_FIELD_8 or UPDATED_FREE_FIELD_8
        defaultShopShouldBeFound("freeField8.in=" + DEFAULT_FREE_FIELD_8 + "," + UPDATED_FREE_FIELD_8);

        // Get all the shopList where freeField8 equals to UPDATED_FREE_FIELD_8
        defaultShopShouldNotBeFound("freeField8.in=" + UPDATED_FREE_FIELD_8);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField8IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField8 is not null
        defaultShopShouldBeFound("freeField8.specified=true");

        // Get all the shopList where freeField8 is null
        defaultShopShouldNotBeFound("freeField8.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField9IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField9 equals to DEFAULT_FREE_FIELD_9
        defaultShopShouldBeFound("freeField9.equals=" + DEFAULT_FREE_FIELD_9);

        // Get all the shopList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultShopShouldNotBeFound("freeField9.equals=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField9IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField9 in DEFAULT_FREE_FIELD_9 or UPDATED_FREE_FIELD_9
        defaultShopShouldBeFound("freeField9.in=" + DEFAULT_FREE_FIELD_9 + "," + UPDATED_FREE_FIELD_9);

        // Get all the shopList where freeField9 equals to UPDATED_FREE_FIELD_9
        defaultShopShouldNotBeFound("freeField9.in=" + UPDATED_FREE_FIELD_9);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField9IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField9 is not null
        defaultShopShouldBeFound("freeField9.specified=true");

        // Get all the shopList where freeField9 is null
        defaultShopShouldNotBeFound("freeField9.specified=false");
    }

    @Test
    @Transactional
    void getAllShopsByFreeField10IsEqualToSomething() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField10 equals to DEFAULT_FREE_FIELD_10
        defaultShopShouldBeFound("freeField10.equals=" + DEFAULT_FREE_FIELD_10);

        // Get all the shopList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultShopShouldNotBeFound("freeField10.equals=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField10IsInShouldWork() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField10 in DEFAULT_FREE_FIELD_10 or UPDATED_FREE_FIELD_10
        defaultShopShouldBeFound("freeField10.in=" + DEFAULT_FREE_FIELD_10 + "," + UPDATED_FREE_FIELD_10);

        // Get all the shopList where freeField10 equals to UPDATED_FREE_FIELD_10
        defaultShopShouldNotBeFound("freeField10.in=" + UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void getAllShopsByFreeField10IsNullOrNotNull() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList where freeField10 is not null
        defaultShopShouldBeFound("freeField10.specified=true");

        // Get all the shopList where freeField10 is null
        defaultShopShouldNotBeFound("freeField10.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultShopShouldBeFound(String filter) throws Exception {
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shop.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT)))
            .andExpect(jsonPath("$.[*].productSize").value(hasItem(DEFAULT_PRODUCT_SIZE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
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
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultShopShouldNotBeFound(String filter) throws Exception {
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingShop() throws Exception {
        // Get the shop
        restShopMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop
        Shop updatedShop = shopRepository.findById(shop.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedShop are not directly saved in db
        em.detach(updatedShop);
        updatedShop
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .product(UPDATED_PRODUCT)
            .productSize(UPDATED_PRODUCT_SIZE)
            .price(UPDATED_PRICE)
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
        ShopDTO shopDTO = shopMapper.toDto(updatedShop);

        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, shopDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shopDTO))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testShop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShop.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShop.getProduct()).isEqualTo(UPDATED_PRODUCT);
        assertThat(testShop.getProductSize()).isEqualTo(UPDATED_PRODUCT_SIZE);
        assertThat(testShop.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShop.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testShop.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testShop.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testShop.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testShop.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testShop.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testShop.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testShop.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testShop.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testShop.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testShop.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testShop.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void putNonExistingShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, shopDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shopDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shopDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateShopWithPatch() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop using partial update
        Shop partialUpdatedShop = new Shop();
        partialUpdatedShop.setId(shop.getId());

        partialUpdatedShop
            .name(UPDATED_NAME)
            .productSize(UPDATED_PRODUCT_SIZE)
            .price(UPDATED_PRICE)
            .emailId(UPDATED_EMAIL_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .status(UPDATED_STATUS)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField9(UPDATED_FREE_FIELD_9);

        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShop))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testShop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShop.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShop.getProduct()).isEqualTo(DEFAULT_PRODUCT);
        assertThat(testShop.getProductSize()).isEqualTo(UPDATED_PRODUCT_SIZE);
        assertThat(testShop.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShop.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testShop.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testShop.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testShop.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testShop.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testShop.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testShop.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testShop.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testShop.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testShop.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testShop.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testShop.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void fullUpdateShopWithPatch() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop using partial update
        Shop partialUpdatedShop = new Shop();
        partialUpdatedShop.setId(shop.getId());

        partialUpdatedShop
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .product(UPDATED_PRODUCT)
            .productSize(UPDATED_PRODUCT_SIZE)
            .price(UPDATED_PRICE)
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

        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShop))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testShop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShop.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShop.getProduct()).isEqualTo(UPDATED_PRODUCT);
        assertThat(testShop.getProductSize()).isEqualTo(UPDATED_PRODUCT_SIZE);
        assertThat(testShop.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShop.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testShop.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testShop.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testShop.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testShop.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testShop.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testShop.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testShop.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testShop.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testShop.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testShop.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testShop.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
    }

    @Test
    @Transactional
    void patchNonExistingShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, shopDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shopDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shopDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeDelete = shopRepository.findAll().size();

        // Delete the shop
        restShopMockMvc
            .perform(delete(ENTITY_API_URL_ID, shop.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
