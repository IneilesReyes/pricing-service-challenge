package com.challenge.pricing.service.application.port.out;

import com.challenge.pricing.service.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {

    List<Price> findPricebyProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate);
}
