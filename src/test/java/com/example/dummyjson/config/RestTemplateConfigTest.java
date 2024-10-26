package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestTemplateConfigTest {

	@Autowired
    private WebClientConfig webClientConfig;

    @Test
    public void testWebClientConfig(){
        // Testando se o WebClient.Builder está configurado corretamente
    	WebClient.Builder builder = webClientConfig.webClientBuilder();
        assertNotNull(builder);
        
        // Testando se o WebClient pode ser construído a partir do builder
        WebClient webClient = builder.build();
        assertNotNull(webClient);
    }
}
