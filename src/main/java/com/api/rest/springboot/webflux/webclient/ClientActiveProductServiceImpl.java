package com.api.rest.springboot.webflux.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.api.rest.springboot.webflux.exceptions.AccountNotFoundException;
import com.api.rest.springboot.webflux.webclient.dto.ActiveProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientActiveProductServiceImpl implements ClientActiveProductService{

  private static final String BASE_URL = "http://localhost:8002";
  
  @Autowired
  private WebClient.Builder webClient;
  
  @Override
  public Flux<ActiveProduct> getCreditCard(String idClient) {
    
    return webClient.baseUrl(BASE_URL).build().get().uri("/api/active/clientActive/{idClient}", idClient)
        .retrieve()
        .bodyToFlux(ActiveProduct.class)
        .onErrorResume(error -> {
          WebClientResponseException response = (WebClientResponseException) error;
          if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
              return Mono.error(new AccountNotFoundException());
          }
          return Mono.error(error);
      });
  }

}
