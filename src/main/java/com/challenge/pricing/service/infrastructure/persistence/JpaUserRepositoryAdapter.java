package com.challenge.pricing.service.infrastructure.persistence;

import com.challenge.pricing.service.application.port.out.UserRepositoryPort;
import com.challenge.pricing.service.domain.model.Price;
import com.challenge.pricing.service.infrastructure.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final PriceDao priceDao;
    private final PriceMapper priceMapper;

    public JpaUserRepositoryAdapter(PriceDao priceDao, PriceMapper priceMapper) {
        this.priceDao = priceDao;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> findPricebyProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceDao.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter
                        (productId, brandId, applicationDate.atZone(ZoneId.systemDefault()).toInstant()).stream()
                .map(priceMapper::toModel)
                .toList();
    }
}

