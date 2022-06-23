package org.thecommunity.analytics.exception;

public class MultipleRecordException extends RuntimeException {

  public MultipleRecordException(final String message) {
    super(message);
  }

  public MultipleRecordException(final String message, Throwable cause) {
    super(message, cause);
  }
}
