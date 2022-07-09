package com.api.rest.springboot.webflux.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import com.api.rest.springboot.webflux.util.ValidationConstants;

import lombok.Data;

@Data
public class Client {
  
  @Id
  private String id;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String name;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String documentType;
  
  @NotEmpty(message = ValidationConstants.NOT_EMAIL)
  private String email;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String address;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String phone;

}
