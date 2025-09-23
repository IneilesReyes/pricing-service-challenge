package com.challenge.pricing.service.unit.domain.service;

import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.domain.service.PriceSelector;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceSelectorTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private PriceSelector priceSelector;

    @Test
    @DisplayName("Test getAppliedPrice WHEN receives more than one element THEN usecase return the element with the highest priority.")
    void getAppliedPrice_returnHighestPriorityPrice() {

        Price lowPriority = easyRandom.nextObject(Price.class)
                .toBuilder()
                .priority(0)
                .build();

        Price highPriority = easyRandom.nextObject(Price.class)
                .toBuilder()
                .priority(1)
                .build();

        List<Price> prices = Arrays.asList(lowPriority, highPriority);

        Price result = priceSelector.getAppliedPrice(prices);

        Assertions.assertEquals(highPriority, result);
    }

    @Test
    @DisplayName("Test getAppliedPrice WHEN UserRepositoryPort returns an empty list THEN throws IllegalArgumentException.")
    void getAppliedPrice_throwsIllegalArgumentException() {

        List<Price> prices = Collections.emptyList();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> priceSelector.getAppliedPrice(prices));

        Assertions.assertEquals("Prices list cannot be empty", exception.getMessage());
    }

}
