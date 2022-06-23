package org.thecommunity.analytics.exception;

public class DataImportException extends RuntimeException {

  public DataImportException(final String message) {
    super(message);
  }

  public DataImportException(final String message, Throwable cause) {
    super(message, cause);
  }
}
