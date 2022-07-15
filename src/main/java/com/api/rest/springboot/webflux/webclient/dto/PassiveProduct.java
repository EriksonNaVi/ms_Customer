package com.api.rest.springboot.webflux.webclient.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class PassiveProduct {
  
  @NotEmpty
  private String id;
  
  @NotEmpty
  private String typeAccount;
  
  @NotEmpty
  private String accountNumber;
  
  @NotEmpty
  private String status;
  
  @NotEmpty
  private String idClient;

}
