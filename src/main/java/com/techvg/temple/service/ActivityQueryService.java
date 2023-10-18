package com.techvg.temple.service;

import com.techvg.temple.domain.*; // for static metamodels
import com.techvg.temple.domain.Activity;
import com.techvg.temple.repository.ActivityRepository;
import com.techvg.temple.service.criteria.ActivityCriteria;
import com.techvg.temple.service.dto.ActivityDTO;
import com.techvg.temple.service.mapper.ActivityMapper;
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
 * Service for executing complex queries for {@link Activity} entities in the database.
 * The main input is a {@link ActivityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActivityDTO} or a {@link Page} of {@link ActivityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActivityQueryService extends QueryService<Activity> {

    private final Logger log = LoggerFactory.getLogger(ActivityQueryService.class);

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    public ActivityQueryService(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    /**
     * Return a {@link List} of {@link ActivityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActivityDTO> findByCriteria(ActivityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Activity> specification = createSpecification(criteria);
        return activityMapper.toDto(activityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActivityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivityDTO> findByCriteria(ActivityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Activity> specification = createSpecification(criteria);
        return activityRepository.findAll(specification, page).map(activityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActivityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Activity> specification = createSpecification(criteria);
        return activityRepository.count(specification);
    }

    /**
     * Function to convert {@link ActivityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Activity> createSpecification(ActivityCriteria criteria) {
        Specification<Activity> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Activity_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Activity_.date));
            }
            if (criteria.getOrganizationName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrganizationName(), Activity_.organizationName));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), Activity_.location));
            }
            if (criteria.getActivityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActivityName(), Activity_.activityName));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Activity_.description));
            }
            if (criteria.getNoOfParticipants() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNoOfParticipants(), Activity_.noOfParticipants));
            }
            if (criteria.getTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTime(), Activity_.time));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMobileNo(), Activity_.mobileNo));
            }
            if (criteria.getEmailId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailId(), Activity_.emailId));
            }
            if (criteria.getContactPerson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactPerson(), Activity_.contactPerson));
            }
            if (criteria.getComments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComments(), Activity_.comments));
            }
            if (criteria.getProfilePhotoType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfilePhotoType(), Activity_.profilePhotoType));
            }
            if (criteria.getSignatureImageType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignatureImageType(), Activity_.signatureImageType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Activity_.status));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModified(), Activity_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Activity_.lastModifiedBy));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), Activity_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), Activity_.freeField2));
            }
            if (criteria.getFreeField3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField3(), Activity_.freeField3));
            }
            if (criteria.getFreeField4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField4(), Activity_.freeField4));
            }
            if (criteria.getFreeField5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField5(), Activity_.freeField5));
            }
            if (criteria.getFreeField6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField6(), Activity_.freeField6));
            }
            if (criteria.getFreeField7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField7(), Activity_.freeField7));
            }
            if (criteria.getFreeField8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreeField8(), Activity_.freeField8));
            }
            if (criteria.getFreeField9() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField9(), Activity_.freeField9));
            }
            if (criteria.getFreeField10() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField10(), Activity_.freeField10));
            }
            if (criteria.getFreeField11() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField11(), Activity_.freeField11));
            }
            if (criteria.getFreeField12() != null) {
                specification = specification.and(buildSpecification(criteria.getFreeField12(), Activity_.freeField12));
            }
        }
        return specification;
    }
}
