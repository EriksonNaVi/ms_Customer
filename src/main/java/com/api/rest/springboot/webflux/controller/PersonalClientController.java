package com.api.rest.springboot.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.springboot.webflux.dto.PersonalClientDto;
import com.api.rest.springboot.webflux.resource.PersonalClientResource;
import com.api.rest.springboot.webflux.webclient.dto.ActiveProduct;
import com.api.rest.springboot.webflux.webclient.dto.PassiveProduct;
import com.api.rest.springboot.webflux.webclient.dto.Transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client/personal")
public class PersonalClientController {

  private static final Logger logger = LoggerFactory.getLogger(PersonalClientController.class);

  @Autowired
  private PersonalClientResource personalClientResource;

  @GetMapping
  public Flux<PersonalClientDto> findAll() {
    logger.debug("Getting PersonalClient");
    return personalClientResource.findAll();
  }

  @PostMapping
  public Mono<PersonalClientDto> register(@Valid @RequestBody PersonalClientDto personalClientDto) {
    logger.debug("Register PersonalClient");
    return personalClientResource.create(personalClientDto);
  }

  @GetMapping("/{id}")
  public Mono<PersonalClientDto> listById(@PathVariable String id) {
    logger.debug("Getting PersonalClientbyid");
    return personalClientResource.findById(id);
  }

  @PutMapping("/{id}")
  public Mono<PersonalClientDto> update(@RequestBody PersonalClientDto personalClientDto, @PathVariable String id) {
    logger.debug("Putting PersonalClientbyid");
    return personalClientResource.update(personalClientDto, id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> remove(@PathVariable String id) {
    return personalClientResource.delete(id);
  }

  // Se agrego para WebConfig
  @CircuitBreaker(name = "creditCardsCB", fallbackMethod = "fallBackGetCreditCards")
  @GetMapping("/clientActives/{idClient}")
  public Flux<ActiveProduct> getCreditCards(@PathVariable("idClient") String idClient) {
    logger.info("IdClient", idClient);
    return personalClientResource.getCreditCards(idClient);
  }

  @CircuitBreaker(name = "numberAccountsCB", fallbackMethod = "fallBackGetNumberAccounts")
  @GetMapping("/clientPassives/{idClient}")
  public Flux<PassiveProduct> getNumberAccounts(@PathVariable("idClient") String idClient) {
    logger.info("IdClient", idClient);
    return personalClientResource.getNumberAccounts(idClient);
  }

  @GetMapping("/transactions/{documentNumber}")
  public Flux<Transaction> getTransactions(@PathVariable("documentNumber") String documentNumber) {
    logger.info("documentNumber", documentNumber);
    return personalClientResource.getTransactions(documentNumber);
  }
  
  public Flux<ActiveProduct> fallBackGetCreditCards(@PathVariable("idClient") String idClient,  Throwable e){
    logger.error(e.getMessage() + " -- user with id " + idClient);
    ActiveProduct activeProduct = new ActiveProduct();
    activeProduct.setCreditCardNumber("Sorry not credit card number information.");
    return Flux.just(activeProduct);
  }
  
  public Flux<PassiveProduct> fallBackGetNumberAccounts(@PathVariable("idClient") String idClient, Throwable e) {
    logger.error(e.getMessage() + " -- user with id " + idClient);
    PassiveProduct passiveProduct = new PassiveProduct();
    passiveProduct.setAccountNumber("Sorry not account number information.");
    return Flux.just(passiveProduct);
  }

}
