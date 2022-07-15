package com.api.rest.springboot.webflux.model;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import com.api.rest.springboot.webflux.util.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "personal_client")
public class PersonalClient extends Client{
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String lastName;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String documentNumber;
  
  private boolean vip;
  
}
