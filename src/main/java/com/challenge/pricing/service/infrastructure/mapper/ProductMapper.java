package com.challenge.pricing.service.infrastructure.mapper;

import com.challenge.pricing.service.domain.model.Product;
import com.challenge.pricing.service.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductEntity brand);
}
