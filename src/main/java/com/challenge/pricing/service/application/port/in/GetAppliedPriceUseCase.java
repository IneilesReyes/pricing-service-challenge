package com.challenge.pricing.service.application.port.in;

import com.challenge.pricing.service.domain.model.Price;

import java.time.LocalDateTime;

public interface GetAppliedPriceUseCase {

    Price getAppliedPrice(Long productId, LocalDateTime applicationDate, Long brandId);

}
