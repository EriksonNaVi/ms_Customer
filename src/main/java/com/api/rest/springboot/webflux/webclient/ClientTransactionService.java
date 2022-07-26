package com.api.rest.springboot.webflux.webclient;

import com.api.rest.springboot.webflux.webclient.dto.Transaction;

import reactor.core.publisher.Flux;

public interface ClientTransactionService {
  
  public Flux<Transaction> getTransactions(String documentNumber);

}
