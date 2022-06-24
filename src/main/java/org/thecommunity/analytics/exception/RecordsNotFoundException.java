package org.thecommunity.analytics.exception;

public class RecordsNotFoundException extends RuntimeException {

  public RecordsNotFoundException(final String message) {
    super(message);
  }

  public RecordsNotFoundException(final String message, Throwable cause) {
    super(message, cause);
  }
}
