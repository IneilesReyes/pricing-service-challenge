package com.challenge.pricing.service.application.service;

import com.challenge.pricing.service.application.port.in.GetAppliedPriceUseCase;
import com.challenge.pricing.service.application.port.out.UserRepositoryPort;
import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.application.exception.RepositoryException;
import com.challenge.pricing.service.application.exception.ResourceNotFoundException;
import com.challenge.pricing.service.domain.service.PriceSelector;

import java.time.LocalDateTime;
import java.util.List;

public class PriceService implements GetAppliedPriceUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PriceSelector priceSelector;

    public PriceService(UserRepositoryPort userRepositoryPort, PriceSelector priceSelector) {
        this.userRepositoryPort = userRepositoryPort;
        this.priceSelector = priceSelector;
    }

    @Override
    public Price getAppliedPrice(Long productId, LocalDateTime applicationDate, Long brandId) {
        List<Price> prices;
        try {
            prices = userRepositoryPort.findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate);
            return priceSelector.getAppliedPrice(prices);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("There are no prices found - productId: %s - brandId: %s - date: %s".formatted(productId, brandId, applicationDate));
        } catch (Exception e) {
            throw new RepositoryException("Encountered error while retrieving prices - productId: %s - brandId: %s - date: %s - error: %s".formatted(productId, brandId, applicationDate, e.getMessage()), e);
        }
    }

}

