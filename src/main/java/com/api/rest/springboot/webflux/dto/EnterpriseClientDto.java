package com.api.rest.springboot.webflux.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EnterpriseClientDto {
  
  private String id;
  private String name;
  private String documentType;
  private String ruc;
  private String email;
  private String address;
  private String phone;
  private boolean pyme;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}
