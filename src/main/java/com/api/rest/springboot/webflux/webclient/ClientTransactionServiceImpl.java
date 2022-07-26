package com.api.rest.springboot.webflux.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.api.rest.springboot.webflux.exceptions.AccountNotFoundException;
import com.api.rest.springboot.webflux.webclient.dto.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientTransactionServiceImpl implements ClientTransactionService{
  
private static final String BASE_URL = "http://localhost:8004";
  
  @Autowired
  private WebClient.Builder webClient;

  @Override
  public Flux<Transaction> getTransactions(String documentNumber) {
    
    return webClient.baseUrl(BASE_URL).build().get().uri("/api/transaction/client/{documentNumber}", documentNumber)
        .retrieve()
        .bodyToFlux(Transaction.class)
        .onErrorResume(error -> {
          WebClientResponseException response = (WebClientResponseException) error;
          if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
              return Mono.error(new AccountNotFoundException());
          }
          return Mono.error(error);
      });
  }

}
