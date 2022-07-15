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
  public Flux<PersonalClientDto> findAll(){
      return personalClientResource.findAll();
  }
  
  @PostMapping
  public Mono<PersonalClientDto> register(@RequestBody PersonalClientDto personalClientDto){
      return personalClientResource.create(personalClientDto);
  }
  
  @GetMapping("/{id}")
  public Mono<PersonalClientDto> listById(@PathVariable String id){
    return personalClientResource.findById(id);
  }
  
  @PutMapping("/{id}")
  public Mono<PersonalClientDto> update(@RequestBody PersonalClientDto personalClientDto, @PathVariable String id){
      return personalClientResource.update(personalClientDto, id);
  }
  
  @DeleteMapping("/{id}")
  public Mono<Void> remove(@PathVariable String id){
    return personalClientResource.delete(id);
  }
  
  //Se agrego para WebConfig
  @GetMapping("/clientActives/{idClient}")
  public Flux<ActiveProduct> getCreditCards(@PathVariable("idClient") String idClient){
    logger.info("IdClient", idClient);
    return personalClientResource.getCreditCards(idClient);
  }
  
  @GetMapping("/clientPassives/{idClient}")
  public Flux<PassiveProduct> getNumberAccounts(@PathVariable("idClient") String idClient){
    logger.info("IdClient", idClient);
    return personalClientResource.getNumberAccounts(idClient);
  }
  
}
