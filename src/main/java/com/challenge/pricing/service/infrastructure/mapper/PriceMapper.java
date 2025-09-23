package com.challenge.pricing.service.infrastructure.mapper;

import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.infrastructure.controller.dto.PriceResponse;
import com.challenge.pricing.service.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, BrandMapper.class, DateTimeMapper.class})
public interface PriceMapper {

    @Mapping(target = "productId", source = "price.product.id")
    @Mapping(target = "brandId", source = "price.brand.id")
    PriceResponse toResponse(Price price);

    @Mapping(target = "currency", source = "entity.currencyCode")
    Price toModel(PriceEntity entity);

}
