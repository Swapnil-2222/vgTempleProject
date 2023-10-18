package com.techvg.temple.service;

import com.techvg.temple.domain.*; // for static metamodels
import com.techvg.temple.domain.Delegate;
import com.techvg.temple.repository.DelegateRepository;
import com.techvg.temple.service.criteria.DelegateCriteria;
import com.techvg.temple.service.dto.DelegateDTO;
import com.techvg.temple.service.mapper.DelegateMapper;
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
 * Service for executing complex queries for {@link Delegate} entities in the database.
 * The main input is a {@link DelegateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DelegateDTO} or a {@link Page} of {@link DelegateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DelegateQueryService extends QueryService<Delegate> {

    private final Logger log = LoggerFactory.getLogger(DelegateQueryService.class);

    private final DelegateRepository delegateRepository;

    private final DelegateMapper delegateMapper;

    public DelegateQueryService(DelegateRepository delegateRepository, DelegateMapper delegateMapper) {
        this.delegateRepository = delegateRepository;
        this.delegateMapper = delegateMapper;
    }

    /**
     * Return a {@link List} of {@link DelegateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DelegateDTO> findByCriteria(DelegateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Delegate> specification = createSpecification(criteria);
        return delegateMapper.toDto(delegateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DelegateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DelegateDTO> findByCriteria(DelegateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Delegate> specification = createSpecification(criteria);
        return delegateRepository.findAll(specification, page).map(delegateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DelegateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Delegate> specification = createSpecification(criteria);
        return delegateRepository.count(specification);
    }

    /**
     * Function to convert {@link DelegateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Delegate> createSpecification(DelegateCriteria criteria) {
        Specification<Delegate> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Delegate_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Delegate_.title));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), Delegate_.fullName));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildSpecification(criteria.getGender(), Delegate_.gender));
            }
            if (criteria.getNationality() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationality(), Delegate_.nationality));
            }
            if (criteria.getCountryOfResidence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryOfResidence(), Delegate_.countryOfResidence));
            }
            if (criteria.getDateOfBirth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateOfBirth(), Delegate_.dateOfBirth));
            }
            if (criteria.getCitizenship() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCitizenship(), Delegate_.citizenship));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMobileNo(), Delegate_.mobileNo));
            }
            if (criteria.getEmailId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailId(), Delegate_.emailId));
            }
            if (criteria.getEmergencyContactName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getEmergencyContactName(), Delegate_.emergencyContactName));
            }
            if (criteria.getEmergencyContactRelationship() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getEmergencyContactRelationship(), Delegate_.emergencyContactRelationship)
                    );
            }
            if (criteria.getEmergencyContactNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmergencyContactNo(), Delegate_.emergencyContactNo));
            }
            if (criteria.getCountryCodeOne() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryCodeOne(), Delegate_.countryCodeOne));
            }
            if (criteria.getCountryCodeTwo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryCodeTwo(), Delegate_.countryCodeTwo));
            }
            if (criteria.getNationalityOne() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationalityOne(), Delegate_.nationalityOne));
            }
            if (criteria.getCountryOfBirth() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryOfBirth(), Delegate_.countryOfBirth));
            }
            if (criteria.getCityOfResidence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCityOfResidence(), Delegate_.cityOfResidence));
            }
            if (criteria.getStateOfResidence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStateOfResidence(), Delegate_.stateOfResidence));
            }
            if (criteria.getDistrictOfResidence() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getDistrictOfResidence(), Delegate_.districtOfResidence));
            }
            if (criteria.getDepartureDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepartureDate(), Delegate_.departureDate));
            }
            if (criteria.getDeparturePlace() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeparturePlace(), Delegate_.departurePlace));
            }
            if (criteria.getArrivalDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArrivalDate(), Delegate_.arrivalDate));
            }
            if (criteria.getArrivalPlace() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArrivalPlace(), Delegate_.arrivalPlace));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Delegate_.status));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModified(), Delegate_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Delegate_.lastModifiedBy));
            }
            if (criteria.getIsTaxReceipt() != null) {
                specification = specification.and(buildSpecification(criteria.getIsTaxReceipt(), Delegate_.isTaxReceipt));
            }
            if (criteria.getProfilePhotoContentType() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getProfilePhotoContentType(), Delegate_.profilePhotoContentType));
            }
            if (criteria.getPassportImageContentType() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPassportImageContentType(), Delegate_.passportImageContentType));
            }
            if (criteria.getConferenceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConferenceName(), Delegate_.conferenceName));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), Delegate_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), Delegate_.freeField2));
            }
            if (criteria.getFreeField3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField3(), Delegate_.freeField3));
            }
            if (criteria.getFreeField4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField4(), Delegate_.freeField4));
            }
            if (criteria.getFreeField5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField5(), Delegate_.freeField5));
            }
            if (criteria.getFreeField6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField6(), Delegate_.freeField6));
            }
            if (criteria.getFreeField7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField7(), Delegate_.freeField7));
            }
            if (criteria.getFreeField8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField8(), Delegate_.freeField8));
            }
            if (criteria.getFreeField9() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField9(), Delegate_.freeField9));
            }
            if (criteria.getFreeField10() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField10(), Delegate_.freeField10));
            }
        }
        return specification;
    }
}
