package com.techvg.temple.service;

import com.techvg.temple.domain.Shop;
import com.techvg.temple.repository.ShopRepository;
import com.techvg.temple.service.dto.ShopDTO;
import com.techvg.temple.service.mapper.ShopMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.techvg.temple.domain.Shop}.
 */
@Service
@Transactional
public class ShopService {

    private final Logger log = LoggerFactory.getLogger(ShopService.class);

    private final ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    public ShopService(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    /**
     * Save a shop.
     *
     * @param shopDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopDTO save(ShopDTO shopDTO) {
        log.debug("Request to save Shop : {}", shopDTO);
        Shop shop = shopMapper.toEntity(shopDTO);
        shop = shopRepository.save(shop);
        return shopMapper.toDto(shop);
    }

    /**
     * Update a shop.
     *
     * @param shopDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopDTO update(ShopDTO shopDTO) {
        log.debug("Request to update Shop : {}", shopDTO);
        Shop shop = shopMapper.toEntity(shopDTO);
        shop = shopRepository.save(shop);
        return shopMapper.toDto(shop);
    }

    /**
     * Partially update a shop.
     *
     * @param shopDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ShopDTO> partialUpdate(ShopDTO shopDTO) {
        log.debug("Request to partially update Shop : {}", shopDTO);

        return shopRepository
            .findById(shopDTO.getId())
            .map(existingShop -> {
                shopMapper.partialUpdate(existingShop, shopDTO);

                return existingShop;
            })
            .map(shopRepository::save)
            .map(shopMapper::toDto);
    }

    /**
     * Get all the shops.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShopDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Shops");
        return shopRepository.findAll(pageable).map(shopMapper::toDto);
    }

    /**
     * Get one shop by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShopDTO> findOne(Long id) {
        log.debug("Request to get Shop : {}", id);
        return shopRepository.findById(id).map(shopMapper::toDto);
    }

    /**
     * Delete the shop by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Shop : {}", id);
        shopRepository.deleteById(id);
    }
}
