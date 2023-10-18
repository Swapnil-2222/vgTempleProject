package com.techvg.temple.service.mapper;

import com.techvg.temple.domain.Hotel;
import com.techvg.temple.service.dto.HotelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hotel} and its DTO {@link HotelDTO}.
 */
@Mapper(componentModel = "spring")
public interface HotelMapper extends EntityMapper<HotelDTO, Hotel> {}
