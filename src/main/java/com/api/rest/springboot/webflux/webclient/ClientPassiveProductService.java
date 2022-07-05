package com.api.rest.springboot.webflux.webclient;

import com.api.rest.springboot.webflux.model.PassiveProduct;

import reactor.core.publisher.Flux;

public interface ClientPassiveProductService {
  
  public Flux<PassiveProduct> getNumberAccount(String idClient);

}
