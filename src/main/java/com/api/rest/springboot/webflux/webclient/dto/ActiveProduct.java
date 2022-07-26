package com.api.rest.springboot.webflux.webclient.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ActiveProduct {
  
  @NotEmpty
  private String id;
  
  @NotEmpty
  private String creditCardNumber;
  
  @NotEmpty
  private String status;
  
  @NotEmpty
  private LocalDate expirationDate;
  
  @NotEmpty
  private String idClient;

}
