package com.api.rest.springboot.webflux.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "personal_client")
public class PersonalClient extends Client{
  
  private String lastName;
  
  private String documentNumber;
  
  private boolean vip;
  
}
