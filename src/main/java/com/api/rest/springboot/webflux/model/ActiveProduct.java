package com.api.rest.springboot.webflux.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ActiveProduct {
  
  @NotEmpty
  private String id;
  
  @NotEmpty
  private String creditCardNumber;
  
  @NotEmpty
  private int status;
  
  @NotEmpty
  private String expirationDate;
  
  @NotEmpty
  private String idClient;

}
