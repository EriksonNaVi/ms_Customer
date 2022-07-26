package com.api.rest.springboot.webflux.resource;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.springboot.webflux.dto.PersonalClientDto;
import com.api.rest.springboot.webflux.model.PersonalClient;
import com.api.rest.springboot.webflux.service.PersonalClientService;
import com.api.rest.springboot.webflux.util.MapperUtil;
import com.api.rest.springboot.webflux.webclient.ClientActiveProductService;
import com.api.rest.springboot.webflux.webclient.ClientPassiveProductService;
import com.api.rest.springboot.webflux.webclient.ClientTransactionService;
import com.api.rest.springboot.webflux.webclient.dto.ActiveProduct;
import com.api.rest.springboot.webflux.webclient.dto.PassiveProduct;
import com.api.rest.springboot.webflux.webclient.dto.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClientResource extends MapperUtil {

  @Autowired
  private PersonalClientService personalClientService;
  
  @Autowired
  private ClientActiveProductService clientActiveProductService;
  
  @Autowired
  private ClientPassiveProductService clientPassiveProductService;
  
  @Autowired
  private ClientTransactionService clientTransactionService;

  public Flux<PersonalClientDto> findAll() {
    return personalClientService.findAll().map(x -> map(x, PersonalClientDto.class));
  }

  public Mono<PersonalClientDto> create(PersonalClientDto personalClientDto) {
    PersonalClient personClient = map(personalClientDto, PersonalClient.class);
    personClient.setId(new ObjectId().toString());
    personClient.setCreatedDate(LocalDateTime.now());
    Mono<PersonalClient> entity = personalClientService.save(personClient);
    return entity.map(x -> map(x, PersonalClientDto.class));
  }

  public Mono<PersonalClientDto> findById(String id) {
    return personalClientService.findById(id).switchIfEmpty(Mono.error(new Exception()))
        .map(x -> map(x, PersonalClientDto.class));
  }

  public Mono<PersonalClientDto> update(PersonalClientDto personalClientDto, String id) {

    return personalClientService.findById(id)
        .switchIfEmpty(Mono.error(new Exception()))
        .flatMap(x ->{
          x.setName(personalClientDto.getName());
          x.setLastName(personalClientDto.getLastName());
          x.setDocumentType(personalClientDto.getDocumentType());
          x.setDocumentNumber(personalClientDto.getDocumentNumber());
          x.setEmail(personalClientDto.getEmail());
          x.setAddress(personalClientDto.getAddress());
          x.setPhone(personalClientDto.getPhone());
          x.setVip(personalClientDto.isVip());
          x.setUpdatedDate(LocalDateTime.now());
          
          return personalClientService.save(x).map(y -> map(y, PersonalClientDto.class));
        });
  }

  public Mono<Void> delete(String id) {
    return personalClientService.findById(id).switchIfEmpty(Mono.error(new Exception()))
        .flatMap(x -> personalClientService.delete(x));
  }
  
  public Flux<ActiveProduct> getCreditCards(String idClient){
    return clientActiveProductService.getCreditCard(idClient);
  }
  
  public Flux<PassiveProduct> getNumberAccounts(String idClient){
    return clientPassiveProductService.getNumberAccount(idClient);
  }
  
  public Flux<Transaction> getTransactions(String documentNumber){
    return clientTransactionService.getTransactions(documentNumber);
  }
}
