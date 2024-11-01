package com.pruebaindra.carrito.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_coupons")
public class CartCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartCouponId;

    public Long getCartCouponId() {
        return cartCouponId;
    }

    public void setCartCouponId(Long cartCouponId) {
        this.cartCouponId = cartCouponId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    // Getters y Setters
}