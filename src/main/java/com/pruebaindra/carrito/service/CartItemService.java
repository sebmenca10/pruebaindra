package com.pruebaindra.carrito.service;

import com.pruebaindra.carrito.model.Cart;
import com.pruebaindra.carrito.model.CartItem;
import com.pruebaindra.carrito.model.Product;
import com.pruebaindra.carrito.repository.CartItemRepository;
import com.pruebaindra.carrito.repository.CartRepository;
import com.pruebaindra.carrito.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItem addItemToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setSubtotal(subtotal);

        cartItemRepository.save(cartItem);

        // Actualiza el total del carrito
        cart.setTotalPrice(cart.getTotalPrice().add(subtotal));
        cartRepository.save(cart);

        return cartItem;
    }

    public void removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        Cart cart = cartItem.getCart();

        // Resta el subtotal del producto al total del carrito
        cart.setTotalPrice(cart.getTotalPrice().subtract(cartItem.getSubtotal()));
        cartRepository.save(cart);

        cartItemRepository.delete(cartItem);
    }
}