package com.api.rest.springboot.webflux.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "client")
public class Client {
  
  @Id
  private String id;
  
  @NotEmpty
  private String name;
  
  @NotEmpty
  private int documentType;
  
  @NotEmpty
  private String documentNumber;
  
  @NotEmpty
  private String clientType;
  
  @NotEmpty
  private String email;
  
  @NotEmpty
  private String address;
  
  @NotEmpty
  private String phone;

}
