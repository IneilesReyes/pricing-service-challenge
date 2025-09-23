package com.challenge.pricing.service.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class Price {
    private Long id;
    private BigDecimal price;
    private Currency currency;
    private Integer priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Brand brand;
    private Product product;
}
