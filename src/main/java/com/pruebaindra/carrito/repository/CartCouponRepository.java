package com.pruebaindra.carrito.repository;

import com.pruebaindra.carrito.model.CartCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCouponRepository extends JpaRepository<CartCoupon, Long> {
}
