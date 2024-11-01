package com.pruebaindra.carrito.controller;

import com.pruebaindra.carrito.model.CartItem;
import com.pruebaindra.carrito.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/{cartId}/{productId}")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam Integer quantity) {
        CartItem cartItem = cartItemService.addItemToCart(cartId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
