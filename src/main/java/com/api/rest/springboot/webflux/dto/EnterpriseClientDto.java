package com.api.rest.springboot.webflux.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.rest.springboot.webflux.util.ValidationConstants;

import lombok.Data;

@Data
public class EnterpriseClientDto {
  
  private String id;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String name;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String documentType;
  @NotNull(message = ValidationConstants.NOT_EMPTY)
  @Size(min = 11, max = 11, message =ValidationConstants.SIZE)
  private String ruc;
  @NotEmpty(message = ValidationConstants.NOT_EMAIL)
  private String email;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String address;
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String phone;
  private boolean pyme;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}
