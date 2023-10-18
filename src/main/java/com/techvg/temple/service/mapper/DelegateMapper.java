package com.techvg.temple.service.mapper;

import com.techvg.temple.domain.Delegate;
import com.techvg.temple.service.dto.DelegateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Delegate} and its DTO {@link DelegateDTO}.
 */
@Mapper(componentModel = "spring")
public interface DelegateMapper extends EntityMapper<DelegateDTO, Delegate> {}
