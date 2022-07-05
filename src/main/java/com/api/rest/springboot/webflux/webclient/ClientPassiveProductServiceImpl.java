package com.api.rest.springboot.webflux.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.rest.springboot.webflux.model.PassiveProduct;

import reactor.core.publisher.Flux;

@Service
public class ClientPassiveProductServiceImpl implements ClientPassiveProductService{

  private static final String BASE_URL = "http://localhost:8003";
  
  @Autowired
  private WebClient.Builder webClient;
  
  @Override
  public Flux<PassiveProduct> getNumberAccount(String idClient) {
    
    return webClient.baseUrl(BASE_URL).build().get().uri("/api/passive/clientPassive/{idClient}", idClient)
        .retrieve()
        .bodyToFlux(PassiveProduct.class);
  }

}
