package com.api.rest.springboot.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.springboot.webflux.model.EnterpriseClient;
import com.api.rest.springboot.webflux.repository.EnterpriseClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseClientServiceImpl implements EnterpriseClientService{
  
  @Autowired
  private EnterpriseClientRepository enterpriseClientRepository;

  @Override
  public Flux<EnterpriseClient> findAll() {
    
    return enterpriseClientRepository.findAll();
  }

  @Override
  public Mono<EnterpriseClient> findById(String id) {
    
    return enterpriseClientRepository.findById(id);
  }

  @Override
  public Mono<EnterpriseClient> save(EnterpriseClient enterpriseClient) {
    
    return enterpriseClientRepository.save(enterpriseClient);
  }

  @Override
  public Mono<Void> delete(EnterpriseClient enterpriseClient) {
    
    return enterpriseClientRepository.delete(enterpriseClient);
  }

}
