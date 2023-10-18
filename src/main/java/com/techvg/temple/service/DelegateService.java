package com.techvg.temple.service;

import com.techvg.temple.domain.Delegate;
import com.techvg.temple.repository.DelegateRepository;
import com.techvg.temple.service.dto.DelegateDTO;
import com.techvg.temple.service.mapper.DelegateMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.techvg.temple.domain.Delegate}.
 */
@Service
@Transactional
public class DelegateService {

    private final Logger log = LoggerFactory.getLogger(DelegateService.class);

    private final DelegateRepository delegateRepository;

    private final DelegateMapper delegateMapper;

    public DelegateService(DelegateRepository delegateRepository, DelegateMapper delegateMapper) {
        this.delegateRepository = delegateRepository;
        this.delegateMapper = delegateMapper;
    }

    /**
     * Save a delegate.
     *
     * @param delegateDTO the entity to save.
     * @return the persisted entity.
     */
    public DelegateDTO save(DelegateDTO delegateDTO) {
        log.debug("Request to save Delegate : {}", delegateDTO);
        Delegate delegate = delegateMapper.toEntity(delegateDTO);
        delegate = delegateRepository.save(delegate);
        return delegateMapper.toDto(delegate);
    }

    /**
     * Update a delegate.
     *
     * @param delegateDTO the entity to save.
     * @return the persisted entity.
     */
    public DelegateDTO update(DelegateDTO delegateDTO) {
        log.debug("Request to update Delegate : {}", delegateDTO);
        Delegate delegate = delegateMapper.toEntity(delegateDTO);
        delegate = delegateRepository.save(delegate);
        return delegateMapper.toDto(delegate);
    }

    /**
     * Partially update a delegate.
     *
     * @param delegateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DelegateDTO> partialUpdate(DelegateDTO delegateDTO) {
        log.debug("Request to partially update Delegate : {}", delegateDTO);

        return delegateRepository
            .findById(delegateDTO.getId())
            .map(existingDelegate -> {
                delegateMapper.partialUpdate(existingDelegate, delegateDTO);

                return existingDelegate;
            })
            .map(delegateRepository::save)
            .map(delegateMapper::toDto);
    }

    /**
     * Get all the delegates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DelegateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Delegates");
        return delegateRepository.findAll(pageable).map(delegateMapper::toDto);
    }

    /**
     * Get one delegate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DelegateDTO> findOne(Long id) {
        log.debug("Request to get Delegate : {}", id);
        return delegateRepository.findById(id).map(delegateMapper::toDto);
    }

    /**
     * Delete the delegate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Delegate : {}", id);
        delegateRepository.deleteById(id);
    }
}
