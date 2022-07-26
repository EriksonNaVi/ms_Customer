package com.api.rest.springboot.webflux.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import com.api.rest.springboot.webflux.util.ValidationConstants;

import lombok.Data;

@Data
public class PersonalClientDto {
  
  private String id;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String name;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String lastName;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String documentType;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String documentNumber;
  @NotEmpty(message = ValidationConstants.NOT_EMAIL)
  private String email;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String address;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String phone;
  private boolean vip;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate; 

}
