package com.challenge.pricing.service.infrastructure.controller;

import com.challenge.pricing.service.application.port.in.GetAppliedPriceUseCase;
import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.infrastructure.controller.dto.PriceResponse;
import com.challenge.pricing.service.infrastructure.mapper.PriceMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/v1/prices")
@Validated
public class PriceController {

    private final GetAppliedPriceUseCase getAppliedPriceUseCase;
    private final PriceMapper priceMapper;

    public PriceController(GetAppliedPriceUseCase getAppliedPriceUseCase, PriceMapper priceMapper) {
        this.getAppliedPriceUseCase = getAppliedPriceUseCase;
        this.priceMapper = priceMapper;
    }

    @GetMapping("/applied")
    PriceResponse getPriceList(@RequestParam("productId") Long productId,
                               @RequestParam("brandId") Long brandId,
                               @RequestParam("applicationDate")
                               @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime applicationDate) {

        Price price = getAppliedPriceUseCase.getAppliedPrice(productId, applicationDate, brandId);
        return priceMapper.toResponse(price);

    }

}
