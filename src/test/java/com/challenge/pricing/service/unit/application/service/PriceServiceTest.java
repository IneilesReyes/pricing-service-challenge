package com.challenge.pricing.service.unit.application.service;

import com.challenge.pricing.service.application.exception.RepositoryException;
import com.challenge.pricing.service.application.exception.ResourceNotFoundException;
import com.challenge.pricing.service.application.port.out.PriceRepositoryPort;
import com.challenge.pricing.service.application.service.PriceService;
import com.challenge.pricing.service.domain.service.PriceSelector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private PriceSelector priceSelectorMock;

    @Test
    @DisplayName("Test getAppliedPrice WHEN UserRepositoryPort returns an empty list THEN expects ResourceNotFoundException.")
    void getPrice_throwsResourceNotFoundException() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);

        when(priceRepositoryPort.findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate)).thenReturn(Collections.emptyList());
        when(priceSelectorMock.getAppliedPrice(Collections.emptyList())).thenThrow(new IllegalArgumentException());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            priceService.getAppliedPrice(productId, applicationDate, brandId);
        });

        assertTrue(exception.getMessage().contains("There are no prices found"));
        verify(priceRepositoryPort, times(1)).findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate);
        verify(priceSelectorMock, times(1)).getAppliedPrice(Collections.emptyList());
    }

    @Test
    @DisplayName("Test getAppliedPrice WHEN database conection is down THEN throws RepositoryException")
    void getAppliedPrice_throwsRepositoryException() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        String errorMessage = "Simulated DB error";
        String expectedMessage = "Encountered error while retrieving prices - productId: %s - brandId: %s - date: %s - error: %s".formatted(productId, brandId, applicationDate, errorMessage);

        when(priceRepositoryPort.findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate))
                .thenThrow(new RuntimeException(errorMessage));

        RepositoryException exception = assertThrows(RepositoryException.class, () -> {
            priceService.getAppliedPrice(productId, applicationDate, brandId);
        });

        assertEquals(expectedMessage, exception.getMessage());
        verify(priceRepositoryPort, times(1)).findPricebyProductIdBrandIdAndDate(productId, brandId, applicationDate);
        verify(priceSelectorMock, never()).getAppliedPrice(any());
    }
}
