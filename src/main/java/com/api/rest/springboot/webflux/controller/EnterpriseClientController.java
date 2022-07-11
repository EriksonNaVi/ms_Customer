package com.api.rest.springboot.webflux.controller;

import java.net.URI;

import javax.validation.Valid;

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

import com.api.rest.springboot.webflux.model.EnterpriseClient;
import com.api.rest.springboot.webflux.service.EnterpriseClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client/enterprise")
public class EnterpriseClientController {
  
  @Autowired
  private EnterpriseClientService enterpriseClientService;
  
  @GetMapping
  public Flux<EnterpriseClient> toList(){
      return enterpriseClientService.findAll();
  }
  
  @PostMapping
  public Mono<EnterpriseClient> register(@Valid @RequestBody EnterpriseClient enterpriseClient){
      return enterpriseClientService.save(enterpriseClient);
  }
  
  @GetMapping("/{id}")
  public Mono<ResponseEntity<EnterpriseClient>> listById(@PathVariable String id){
    return enterpriseClientService.findById(id).map(c -> ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(c))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  @PutMapping("/{id}")
  public Mono<ResponseEntity<EnterpriseClient>> edit(@RequestBody EnterpriseClient enterpriseClient, @PathVariable String id) {
    return enterpriseClientService.findById(id).flatMap(c -> {
      c.setName(enterpriseClient.getName());
      c.setDocumentType(enterpriseClient.getDocumentType());
      c.setRuc(enterpriseClient.getRuc());
      c.setEnterpriseType(enterpriseClient.getEnterpriseType());
      c.setEmail(enterpriseClient.getEmail());
      c.setAddress(enterpriseClient.getAddress());
      c.setPhone(enterpriseClient.getPhone());

      return enterpriseClientService.save(c);
    }).map(c -> ResponseEntity.created(URI.create("/api/client/enterprise".concat(c.getId())))
        .contentType(MediaType.APPLICATION_JSON_UTF8).body(c)).defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> remove(@PathVariable String id){
    return enterpriseClientService.findById(id).flatMap(c ->{
      return enterpriseClientService.delete(c).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

}
