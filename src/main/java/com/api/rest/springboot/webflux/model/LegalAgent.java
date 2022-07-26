package com.api.rest.springboot.webflux.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class LegalAgent {
  
  private String id;
  private String nombre;
  private String tipoDocumento;
  private String numeroDocumento;
  private String telefono;

}
