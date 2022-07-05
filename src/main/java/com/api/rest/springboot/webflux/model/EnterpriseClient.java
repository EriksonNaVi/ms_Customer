package com.api.rest.springboot.webflux.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EnterpriseClient extends Client{
  
  private String ruc;
  private List<LegalAgent> legalAgent;

}
