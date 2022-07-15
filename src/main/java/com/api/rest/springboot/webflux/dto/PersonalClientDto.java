package com.api.rest.springboot.webflux.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PersonalClientDto {
  
  private String id;
  private String name;
  private String lastName;
  private String documentType;
  private String documentNumber;
  private String email;
  private String address;
  private String phone;
  private boolean vip;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate; 

}
