package com.api.rest.springboot.webflux.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "enterprise_client")
public class EnterpriseClient extends Client{
  
  private String ruc;
  
  private boolean pyme;

}
