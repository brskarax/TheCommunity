package org.thecommunity.analytics.exception;

public class MultipleRecordsException extends RuntimeException {

  public MultipleRecordsException(final String message) {
    super(message);
  }

  public MultipleRecordsException(final String message, Throwable cause) {
    super(message, cause);
  }
}
