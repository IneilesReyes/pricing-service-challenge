package com.challenge.pricing.service.application.service;

import com.challenge.pricing.service.application.port.in.GetAppliedPriceUseCase;
import com.challenge.pricing.service.application.port.out.PriceRepositoryPort;
import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.application.exception.RepositoryException;
import com.challenge.pricing.service.application.exception.ResourceNotFoundException;
import com.challenge.pricing.service.domain.service.PriceSelector;

import java.time.LocalDateTime;
import java.util.List;

public class PriceService implements GetAppliedPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceSelector priceSelector;

    public PriceService(PriceRepositoryPort priceRepositoryPort, PriceSelector priceSelector) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceSelector = priceSelector;
    }

    @Override
    public Price getAppliedPrice(Long productId, LocalDateTime applicationDate, Long brandId) {
        List<Price> prices;
        try {
            prices = priceRepositoryPort.findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate);
            return priceSelector.getAppliedPrice(prices);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("There are no prices found - productId: %s - brandId: %s - date: %s".formatted(productId, brandId, applicationDate));
        } catch (Exception e) {
            throw new RepositoryException("Encountered error while retrieving prices - productId: %s - brandId: %s - date: %s - error: %s".formatted(productId, brandId, applicationDate, e.getMessage()), e);
        }
    }

}

