package com.api.rest.springboot.webflux.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.springboot.webflux.model.ActiveProduct;
import com.api.rest.springboot.webflux.model.Client;
import com.api.rest.springboot.webflux.model.PassiveProduct;
import com.api.rest.springboot.webflux.service.ClientService;
import com.api.rest.springboot.webflux.webclient.ClientActiveProductService;
import com.api.rest.springboot.webflux.webclient.ClientPassiveProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
  
  private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

  @Autowired
  private ClientService clientService;
  
  @Autowired
  private ClientActiveProductService clientActiveProductService;
  
  @Autowired
  private ClientPassiveProductService clientPassiveProductService;
  
  @GetMapping
  public Flux<Client> toList(){
      return clientService.findAll();
  }
  
  @PostMapping
  public Mono<Client> register(@RequestBody Client client){
      return clientService.save(client);
  }
  
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Client>> listById(@PathVariable String id){
    return clientService.findById(id).map(c -> ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(c))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Client>> edit(@RequestBody Client client, @PathVariable String id) {
    return clientService.findById(id).flatMap(c -> {
      c.setName(client.getName());
      c.setDocumentType(client.getDocumentType());
      c.setDocumentNumber(client.getDocumentNumber());
      c.setClientType(client.getClientType());
      c.setEmail(client.getEmail());
      c.setAddress(client.getAddress());
      c.setPhone(client.getPhone());

      return clientService.update(c);
    }).map(c -> ResponseEntity.created(URI.create("/api/client/".concat(c.getId())))
        .contentType(MediaType.APPLICATION_JSON_UTF8).body(c)).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> remove(@PathVariable String id){
    return clientService.findById(id).flatMap(c ->{
      return clientService.delete(c).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }
  
  //Se agrego para WebConfig
  @GetMapping("/clientActives/{idClient}")
  public Flux<ActiveProduct> getCreditCards(@PathVariable("idClient") String idClient){
    logger.info("IdClient", idClient);
    return clientActiveProductService.getCreditCard(idClient);
  }
  
  @GetMapping("/clientPassives/{idClient}")
  public Flux<PassiveProduct> getNumberAccounts(@PathVariable("idClient") String idClient){
    logger.info("IdClient", idClient);
    return clientPassiveProductService.getNumberAccount(idClient);
  }
  

}
