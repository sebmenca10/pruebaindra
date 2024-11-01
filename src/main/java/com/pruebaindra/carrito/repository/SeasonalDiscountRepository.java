package com.pruebaindra.carrito.repository;

import com.pruebaindra.carrito.model.SeasonalDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonalDiscountRepository extends JpaRepository<SeasonalDiscount, Long> {
}