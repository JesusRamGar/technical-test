package com.kairosds.domain.exception;

public class OfferNotFoundException extends RuntimeException {

  public OfferNotFoundException(String details) {
    super(details);
  }
}
