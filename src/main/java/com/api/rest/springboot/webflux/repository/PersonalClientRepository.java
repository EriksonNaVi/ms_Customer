package com.api.rest.springboot.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.springboot.webflux.model.PersonalClient;

import reactor.core.publisher.Mono;

@Repository
public interface PersonalClientRepository extends ReactiveMongoRepository<PersonalClient, String> {
  
  Mono<PersonalClient> findByDocumentNumber(String documentNumber);

}