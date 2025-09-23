package com.challenge.pricing.service.infrastructure.config;

import com.challenge.pricing.service.application.port.in.GetAppliedPriceUseCase;
import com.challenge.pricing.service.application.port.out.UserRepositoryPort;
import com.challenge.pricing.service.application.service.PriceService;
import com.challenge.pricing.service.domain.service.PriceSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public GetAppliedPriceUseCase priceService(UserRepositoryPort userRepositoryPort,
                                               PriceSelector priceSelector) {
        return new PriceService(userRepositoryPort, priceSelector);
    }

    @Bean
    public PriceSelector pricingService() {
        return new PriceSelector();
    }

}
