package com.techvg.temple.service;

import com.techvg.temple.domain.*; // for static metamodels
import com.techvg.temple.domain.Shop;
import com.techvg.temple.repository.ShopRepository;
import com.techvg.temple.service.criteria.ShopCriteria;
import com.techvg.temple.service.dto.ShopDTO;
import com.techvg.temple.service.mapper.ShopMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Shop} entities in the database.
 * The main input is a {@link ShopCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ShopDTO} or a {@link Page} of {@link ShopDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ShopQueryService extends QueryService<Shop> {

    private final Logger log = LoggerFactory.getLogger(ShopQueryService.class);

    private final ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    public ShopQueryService(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    /**
     * Return a {@link List} of {@link ShopDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ShopDTO> findByCriteria(ShopCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Shop> specification = createSpecification(criteria);
        return shopMapper.toDto(shopRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ShopDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ShopDTO> findByCriteria(ShopCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Shop> specification = createSpecification(criteria);
        return shopRepository.findAll(specification, page).map(shopMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ShopCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Shop> specification = createSpecification(criteria);
        return shopRepository.count(specification);
    }

    /**
     * Function to convert {@link ShopCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Shop> createSpecification(ShopCriteria criteria) {
        Specification<Shop> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Shop_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Shop_.date));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Shop_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Shop_.description));
            }
            if (criteria.getProduct() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProduct(), Shop_.product));
            }
            if (criteria.getProductSize() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductSize(), Shop_.productSize));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Shop_.price));
            }
            if (criteria.getEmailId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailId(), Shop_.emailId));
            }
            if (criteria.getContactPerson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactPerson(), Shop_.contactPerson));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Shop_.status));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModified(), Shop_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Shop_.lastModifiedBy));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), Shop_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), Shop_.freeField2));
            }
            if (criteria.getFreeField5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField5(), Shop_.freeField5));
            }
            if (criteria.getFreeField6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField6(), Shop_.freeField6));
            }
            if (criteria.getFreeField7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField7(), Shop_.freeField7));
            }
            if (criteria.getFreeField8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField8(), Shop_.freeField8));
            }
            if (criteria.getFreeField9() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField9(), Shop_.freeField9));
            }
            if (criteria.getFreeField10() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField10(), Shop_.freeField10));
            }
        }
        return specification;
    }
}
