package com.pruebaindra.carrito.service;

import com.pruebaindra.carrito.model.Product;
import com.pruebaindra.carrito.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById_ProductExists() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Producto de prueba");
        product.setPrice(new BigDecimal("19.99"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Producto de prueba", foundProduct.get().getName());
        assertEquals(new BigDecimal("19.99"), foundProduct.get().getPrice());
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.getProductById(2L);

        assertFalse(foundProduct.isPresent());
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Nuevo Producto");
        product.setPrice(new BigDecimal("29.99"));

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Nuevo Producto", savedProduct.getName());
        assertEquals(new BigDecimal("29.99"), savedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}