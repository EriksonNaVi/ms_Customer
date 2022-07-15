package com.api.rest.springboot.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.springboot.webflux.model.EnterpriseClient;

@Repository
public interface EnterpriseClientRepository extends ReactiveMongoRepository<EnterpriseClient, String>{

}
