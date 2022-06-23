package org.thecommunity.analytics.model;

import java.util.Arrays;

public enum Gender {

  MALE,
  FEMALE;

  public static Gender valueOfIgnoreCase(final String value) {
    return Arrays.stream(Gender.values())
        .filter(e -> e.name().equalsIgnoreCase(value))
        .findAny()
        .orElse(null);
  }

}
