package com.challenge.pricing.service.domain.service;

import com.challenge.pricing.service.domain.model.Price;

import java.util.Comparator;
import java.util.List;

public class PriceSelector {

    public Price getAppliedPrice(List<Price> prices) {
       return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
               .orElseThrow(()-> new IllegalArgumentException("Prices list cannot be empty"));
    }
}
