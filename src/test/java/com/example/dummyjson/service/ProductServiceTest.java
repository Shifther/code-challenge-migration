package com.example.dummyjson.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dummyjson.dto.Product;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private WebClient.Builder webClientBuilder;

    @MockBean
    private WebClient webClient;
    
    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        Product[] products = {product1, product2};
        
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
        when(webClient.get().uri(anyString())).thenReturn(Mockito.mock(WebClient.RequestHeadersSpec.class));
        when(webClient.get().uri(anyString()).retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClient.get().uri(anyString()).retrieve().bodyToMono(Product[].class)).thenReturn(Mono.just(products));
        
        List<Product> result = productService.getAllProducts();
        assertThat(result.size()).isEqualTo(2);
        assertThat((result.get(0)).getTitle()).isEqualTo("Product 1");
    }

   @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
        when(webClient.get().uri(anyString())).thenReturn(Mockito.mock(WebClient.RequestHeadersSpec.class));
        when(webClient.get().uri(anyString()).retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClient.get().uri(anyString()).retrieve().bodyToMono(Product.class)).thenReturn(Mono.just(product));

        Product result = productService.getProductById(1L);
        assertThat(result.getTitle()).isEqualTo("Product 1");
    }
}
       
        