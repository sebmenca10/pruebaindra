package com.pruebaindra.carrito.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "seasonal_discounts")
public class SeasonalDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonalDiscountId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal discountPercentage;
    private LocalDate validFrom;
    private LocalDate validUntil;

    public Long getSeasonalDiscountId() {
        return seasonalDiscountId;
    }

    public void setSeasonalDiscountId(Long seasonalDiscountId) {
        this.seasonalDiscountId = seasonalDiscountId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    // Getters y Setters
}