package com.api.rest.springboot.webflux.model;


import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import com.api.rest.springboot.webflux.util.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "enterprise_client")
public class EnterpriseClient extends Client{
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private String ruc;
  
  @NotEmpty(message = ValidationConstants.NOT_EMPTY)
  private boolean pyme;

}
