package com.techcommunityperu.techcommunityperu.exceptions;

public class RoleNotFoundException extends RuntimeException {

  public RoleNotFoundException() {
    super("Role not found for the user.");
  }

  public RoleNotFoundException(String message) {
    super(message);
  }
}