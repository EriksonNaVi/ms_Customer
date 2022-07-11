package com.api.rest.springboot.webflux.webclient;

import com.api.rest.springboot.webflux.model.ActiveProduct;

import reactor.core.publisher.Flux;

public interface ClientActiveProductService {
  
  public Flux<ActiveProduct> getCreditCard(String idClient);

}
