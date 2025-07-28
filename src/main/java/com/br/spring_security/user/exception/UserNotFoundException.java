package com.br.spring_security.user.exception;

import com.br.spring_security.user.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends RuntimeException
{
  private final UUID userId;
  public UserNotFoundException(String message,UUID userId)
  {
      super(message);
      this.userId = userId;
  }
}
