package com.api.rest.springboot.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.rest.springboot.webflux.model.PersonalClient;
import com.api.rest.springboot.webflux.repository.PersonalClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClientServiceImpl implements PersonalClientService{
  
  @Autowired
  private PersonalClientRepository clientRepository;
  
  @Override
  public Flux<PersonalClient> findAll() {
    
    return clientRepository.findAll();
  }

  @Override
  public Mono<PersonalClient> findById(String id) {
    
    return clientRepository.findById(id);
  }

  @Override
  public Mono<PersonalClient> save(PersonalClient client) {
    
    return clientRepository.save(client);
  }

  @Override
  public Mono<Void> delete(PersonalClient client) {
    
    return clientRepository.delete(client);
  }

}
