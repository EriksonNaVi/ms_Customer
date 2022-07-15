package com.api.rest.springboot.webflux.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Crud <T,S>{

  Flux<T> findAll();
  Mono<T> findById(S s);
  Mono<T> save(T t);
  Mono<Void>delete(T t);
  
  
}
