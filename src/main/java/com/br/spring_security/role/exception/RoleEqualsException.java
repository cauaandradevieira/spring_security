package com.br.spring_security.role.exception;

import lombok.Getter;

@Getter
public class RoleEqualsException extends RuntimeException
{
  private final Integer firstRoleId;
  private final Integer secondRoleId;

  public RoleEqualsException(String message, Integer firstRoleId, Integer secondRoleId)
  {
      super(message);
      this.firstRoleId = firstRoleId;
      this.secondRoleId = secondRoleId;
  }
}
