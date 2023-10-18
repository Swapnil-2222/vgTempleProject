package com.techvg.temple.service;

import com.techvg.temple.domain.*; // for static metamodels
import com.techvg.temple.domain.Hotel;
import com.techvg.temple.repository.HotelRepository;
import com.techvg.temple.service.criteria.HotelCriteria;
import com.techvg.temple.service.dto.HotelDTO;
import com.techvg.temple.service.mapper.HotelMapper;
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
 * Service for executing complex queries for {@link Hotel} entities in the database.
 * The main input is a {@link HotelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HotelDTO} or a {@link Page} of {@link HotelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HotelQueryService extends QueryService<Hotel> {

    private final Logger log = LoggerFactory.getLogger(HotelQueryService.class);

    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    public HotelQueryService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    /**
     * Return a {@link List} of {@link HotelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HotelDTO> findByCriteria(HotelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Hotel> specification = createSpecification(criteria);
        return hotelMapper.toDto(hotelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HotelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HotelDTO> findByCriteria(HotelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Hotel> specification = createSpecification(criteria);
        return hotelRepository.findAll(specification, page).map(hotelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HotelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Hotel> specification = createSpecification(criteria);
        return hotelRepository.count(specification);
    }

    /**
     * Function to convert {@link HotelCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Hotel> createSpecification(HotelCriteria criteria) {
        Specification<Hotel> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Hotel_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Hotel_.date));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Hotel_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Hotel_.description));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), Hotel_.location));
            }
            if (criteria.getRent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRent(), Hotel_.rent));
            }
            if (criteria.getEmailId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailId(), Hotel_.emailId));
            }
            if (criteria.getContactPerson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactPerson(), Hotel_.contactPerson));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Hotel_.status));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModified(), Hotel_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Hotel_.lastModifiedBy));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), Hotel_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), Hotel_.freeField2));
            }
            if (criteria.getFreeField5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField5(), Hotel_.freeField5));
            }
            if (criteria.getFreeField6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField6(), Hotel_.freeField6));
            }
            if (criteria.getFreeField7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField7(), Hotel_.freeField7));
            }
            if (criteria.getFreeField8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField8(), Hotel_.freeField8));
            }
            if (criteria.getFreeField9() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField9(), Hotel_.freeField9));
            }
            if (criteria.getFreeField10() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField10(), Hotel_.freeField10));
            }
        }
        return specification;
    }
}
