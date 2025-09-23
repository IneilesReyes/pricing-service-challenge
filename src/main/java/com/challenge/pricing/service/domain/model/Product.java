package com.challenge.pricing.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String category;
}
