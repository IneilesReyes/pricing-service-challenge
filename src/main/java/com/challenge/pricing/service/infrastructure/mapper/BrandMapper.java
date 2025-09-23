package com.challenge.pricing.service.infrastructure.mapper;

import com.challenge.pricing.service.domain.model.Brand;
import com.challenge.pricing.service.infrastructure.persistence.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toModel(BrandEntity brand);
}
