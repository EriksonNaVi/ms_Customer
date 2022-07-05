package com.api.rest.springboot.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.rest.springboot.webflux.model.ActiveProduct;
import com.api.rest.springboot.webflux.model.Client;
import com.api.rest.springboot.webflux.model.PassiveProduct;
import com.api.rest.springboot.webflux.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Override
  public Flux<Client> findAll() {
    
    return clientRepository.findAll();
  }

  @Override
  public Mono<Client> findById(String id) {
    
    return clientRepository.findById(id);
  }

  @Override
  public Mono<Client> save(Client client) {
    
    return clientRepository.save(client);
  }

  @Override
  public Mono<Client> update(Client client) {
    
    return clientRepository.save(client);
  }

  @Override
  public Mono<Void> delete(Client client) {
    
    return clientRepository.delete(client);
  }

}
