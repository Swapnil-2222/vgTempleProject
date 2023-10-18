package com.techvg.temple.web.rest;

import com.techvg.temple.repository.DelegateRepository;
import com.techvg.temple.service.DelegateQueryService;
import com.techvg.temple.service.DelegateService;
import com.techvg.temple.service.criteria.DelegateCriteria;
import com.techvg.temple.service.dto.DelegateDTO;
import com.techvg.temple.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.techvg.temple.domain.Delegate}.
 */
@RestController
@RequestMapping("/api")
public class DelegateResource {

    private final Logger log = LoggerFactory.getLogger(DelegateResource.class);

    private static final String ENTITY_NAME = "delegate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DelegateService delegateService;

    private final DelegateRepository delegateRepository;

    private final DelegateQueryService delegateQueryService;

    public DelegateResource(
        DelegateService delegateService,
        DelegateRepository delegateRepository,
        DelegateQueryService delegateQueryService
    ) {
        this.delegateService = delegateService;
        this.delegateRepository = delegateRepository;
        this.delegateQueryService = delegateQueryService;
    }

    /**
     * {@code POST  /delegates} : Create a new delegate.
     *
     * @param delegateDTO the delegateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new delegateDTO, or with status {@code 400 (Bad Request)} if the delegate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delegates")
    public ResponseEntity<DelegateDTO> createDelegate(@RequestBody DelegateDTO delegateDTO) throws URISyntaxException {
        log.debug("REST request to save Delegate : {}", delegateDTO);
        if (delegateDTO.getId() != null) {
            throw new BadRequestAlertException("A new delegate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DelegateDTO result = delegateService.save(delegateDTO);
        return ResponseEntity
            .created(new URI("/api/delegates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delegates/:id} : Updates an existing delegate.
     *
     * @param id the id of the delegateDTO to save.
     * @param delegateDTO the delegateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated delegateDTO,
     * or with status {@code 400 (Bad Request)} if the delegateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the delegateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delegates/{id}")
    public ResponseEntity<DelegateDTO> updateDelegate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DelegateDTO delegateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Delegate : {}, {}", id, delegateDTO);
        if (delegateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, delegateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!delegateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DelegateDTO result = delegateService.update(delegateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, delegateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /delegates/:id} : Partial updates given fields of an existing delegate, field will ignore if it is null
     *
     * @param id the id of the delegateDTO to save.
     * @param delegateDTO the delegateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated delegateDTO,
     * or with status {@code 400 (Bad Request)} if the delegateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the delegateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the delegateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delegates/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DelegateDTO> partialUpdateDelegate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DelegateDTO delegateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Delegate partially : {}, {}", id, delegateDTO);
        if (delegateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, delegateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!delegateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DelegateDTO> result = delegateService.partialUpdate(delegateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, delegateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /delegates} : get all the delegates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of delegates in body.
     */
    @GetMapping("/delegates")
    public ResponseEntity<List<DelegateDTO>> getAllDelegates(
        DelegateCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Delegates by criteria: {}", criteria);

        Page<DelegateDTO> page = delegateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delegates/count} : count all the delegates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/delegates/count")
    public ResponseEntity<Long> countDelegates(DelegateCriteria criteria) {
        log.debug("REST request to count Delegates by criteria: {}", criteria);
        return ResponseEntity.ok().body(delegateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /delegates/:id} : get the "id" delegate.
     *
     * @param id the id of the delegateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the delegateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delegates/{id}")
    public ResponseEntity<DelegateDTO> getDelegate(@PathVariable Long id) {
        log.debug("REST request to get Delegate : {}", id);
        Optional<DelegateDTO> delegateDTO = delegateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(delegateDTO);
    }

    /**
     * {@code DELETE  /delegates/:id} : delete the "id" delegate.
     *
     * @param id the id of the delegateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delegates/{id}")
    public ResponseEntity<Void> deleteDelegate(@PathVariable Long id) {
        log.debug("REST request to delete Delegate : {}", id);
        delegateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
