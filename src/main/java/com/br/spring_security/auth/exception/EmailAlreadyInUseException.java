package com.br.spring_security.auth.exception;

import lombok.Getter;

@Getter
public class EmailAlreadyInUseException extends RuntimeException {
  private final String email;

  public EmailAlreadyInUseException(String message, String email) {
    super(message);
    this.email = email;
  }
}
