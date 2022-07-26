package com.api.rest.springboot.webflux.webclient;

import com.api.rest.springboot.webflux.webclient.dto.PassiveProduct;

import reactor.core.publisher.Flux;

public interface ClientPassiveProductService {
  
  public Flux<PassiveProduct> getNumberAccount(String idClient);

}
