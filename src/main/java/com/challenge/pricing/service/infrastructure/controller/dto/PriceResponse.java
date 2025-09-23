package com.challenge.pricing.service.infrastructure.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class PriceResponse {
    private Long id;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long brandId;
    private Long productId;
    private String currency;
}
