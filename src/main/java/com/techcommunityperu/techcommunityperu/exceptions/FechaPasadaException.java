package com.techcommunityperu.techcommunityperu.exceptions;

public class FechaPasadaException extends RuntimeException {
  public FechaPasadaException(String message) {
    super(message);
  }

    public static class MissingFieldsException extends RuntimeException {
        public MissingFieldsException(String message) {
            super(message);
        }
    }
}
