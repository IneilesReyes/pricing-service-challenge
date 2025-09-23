package com.challenge.pricing.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Brand {
    private Long id;
    private BrandName name;

    public enum BrandName {
        ZARA, BERSHKA, STRADIVARIUS, OYSHO, MASSIMO_DUTTI
    }

}

