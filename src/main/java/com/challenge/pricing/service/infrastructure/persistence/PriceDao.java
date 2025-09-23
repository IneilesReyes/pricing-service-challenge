package com.challenge.pricing.service.infrastructure.persistence;

import com.challenge.pricing.service.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PriceDao extends JpaRepository<PriceEntity, Long> {

    @Query("""
            SELECT price FROM PriceEntity price
            WHERE :date BETWEEN price.startDate AND price.endDate
              AND price.brand.id = :brandId
              AND price.product.id = :productId
            """)
    List<PriceEntity> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(Long productId, Long brandId, Instant date);

}

