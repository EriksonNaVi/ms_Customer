package com.api.rest.springboot.webflux.resource;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.springboot.webflux.dto.EnterpriseClientDto;
import com.api.rest.springboot.webflux.model.EnterpriseClient;
import com.api.rest.springboot.webflux.service.EnterpriseClientService;
import com.api.rest.springboot.webflux.util.MapperUtil;
import com.api.rest.springboot.webflux.webclient.ClientActiveProductService;
import com.api.rest.springboot.webflux.webclient.ClientPassiveProductService;
import com.api.rest.springboot.webflux.webclient.dto.ActiveProduct;
import com.api.rest.springboot.webflux.webclient.dto.PassiveProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseClientResource extends MapperUtil{
  
  @Autowired
  private EnterpriseClientService enterpriseClientService;
  
  @Autowired
  private ClientActiveProductService clientActiveProductService;
  
  @Autowired
  private ClientPassiveProductService clientPassiveProductService;
  
  public Flux<EnterpriseClientDto> findAll() {
    return enterpriseClientService.findAll().map(x -> map(x, EnterpriseClientDto.class));
  }
  
  public Mono<EnterpriseClientDto> create(EnterpriseClientDto enterpriseClientDto) {
    EnterpriseClient enterpriseClient = map(enterpriseClientDto, EnterpriseClient.class);
    enterpriseClient.setId(new ObjectId().toString());
    enterpriseClient.setCreatedDate(LocalDateTime.now());
    Mono<EnterpriseClient> entity = enterpriseClientService.save(enterpriseClient);
    return entity.filter(x -> x.getDocumentType().equals("2")).map(x -> map(x, EnterpriseClientDto.class));
  }
  
  public Mono<EnterpriseClientDto> findById(String id) {
    return enterpriseClientService.findById(id).switchIfEmpty(Mono.error(new Exception()))
        .map(x -> map(x, EnterpriseClientDto.class));
  }
  
  public Mono<EnterpriseClientDto> update(EnterpriseClientDto enterpriseClientDto, String id) {

    return enterpriseClientService.findById(id)
        .switchIfEmpty(Mono.error(new Exception()))
        .flatMap(x ->{
          x.setName(enterpriseClientDto.getName());
          x.setDocumentType(enterpriseClientDto.getDocumentType());
          x.setRuc(enterpriseClientDto.getRuc());
          x.setEmail(enterpriseClientDto.getEmail());
          x.setAddress(enterpriseClientDto.getAddress());
          x.setPhone(enterpriseClientDto.getPhone());
          x.setPyme(enterpriseClientDto.isPyme());
          x.setUpdatedDate(LocalDateTime.now());
          
          return enterpriseClientService.save(x).map(y -> map(y, EnterpriseClientDto.class));
        });
  }
  
  public Mono<Void> delete(String id) {
    return enterpriseClientService.findById(id).switchIfEmpty(Mono.error(new Exception()))
        .flatMap(x -> enterpriseClientService.delete(x));
  }
  
  public Flux<ActiveProduct> getCreditCards(String idClient){
    return clientActiveProductService.getCreditCard(idClient);
  }
  
  public Flux<PassiveProduct> getNumberAccounts(String idClient){
    return clientPassiveProductService.getNumberAccount(idClient);
  }

}
