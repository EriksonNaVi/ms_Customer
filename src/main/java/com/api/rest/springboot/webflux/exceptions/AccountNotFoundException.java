package com.api.rest.springboot.webflux.exceptions;

public class AccountNotFoundException extends RuntimeException{

  private static final String MESSAGE = "Account not found";

  public AccountNotFoundException() {
      super(MESSAGE);
  }
  
}
