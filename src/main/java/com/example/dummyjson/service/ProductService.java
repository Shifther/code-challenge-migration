package com.example.dummyjson.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dummyjson.dto.Product;
@Service
public class ProductService {

    private final WebClient.Builder webClientBuilder;

         @Value("${dummyjson.api.url}")
        private String baseUrl;

    @Autowired
    public ProductService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    
    public List<Product> getAllProducts() {
        Product[] products = webClientBuilder.build()
            .get()
            .uri(baseUrl)
            .retrieve()
            .bodyToMono(Product[].class)
            .block();
        return Arrays.asList(products);
    }
    public Product getProductById(Long id) {
        return webClientBuilder.build()
            .get()
            .uri(baseUrl + "/" + id)
            .retrieve()
            .bodyToMono(Product.class)
            .block();
    }
}
