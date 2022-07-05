package com.api.rest.springboot.webflux.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.rest.springboot.webflux.model.ActiveProduct;

import reactor.core.publisher.Flux;

@Service
public class ClientActiveProductServiceImpl implements ClientActiveProductService{

  private static final String BASE_URL = "http://localhost:8002";
  
  @Autowired
  private WebClient.Builder webClient;
  
  @Override
  public Flux<ActiveProduct> getCreditCard(String idClient) {
    
    return webClient.baseUrl(BASE_URL).build().get().uri("/api/active/clientActive/{idClient}", idClient)
        .retrieve()
        .bodyToFlux(ActiveProduct.class);
  }

}
