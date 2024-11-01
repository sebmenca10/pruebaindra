package com.pruebaindra.carrito.service;

import com.pruebaindra.carrito.model.Cart;
import com.pruebaindra.carrito.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart createCart(Cart cart) {
        cart.setTotalPrice(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public Cart updateTotalPrice(Long cartId, BigDecimal newTotal) {
        return cartRepository.findById(cartId).map(cart -> {
            cart.setTotalPrice(newTotal);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
