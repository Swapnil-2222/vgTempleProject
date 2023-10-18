package com.techvg.temple.service.mapper;

import com.techvg.temple.domain.Shop;
import com.techvg.temple.service.dto.ShopDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shop} and its DTO {@link ShopDTO}.
 */
@Mapper(componentModel = "spring")
public interface ShopMapper extends EntityMapper<ShopDTO, Shop> {}
