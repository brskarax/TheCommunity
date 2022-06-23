package org.thecommunity.analytics.exception;

public class NoRecordsFoundException extends RuntimeException {

  public NoRecordsFoundException(final String message) {
    super(message);
  }

  public NoRecordsFoundException(final String message, Throwable cause) {
    super(message, cause);
  }
}
