package org.thecommunity.analytics.service;

import org.apache.commons.lang3.StringUtils;
import org.thecommunity.analytics.exception.DataImportException;
import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

public class DataImportService {

  //this is to evaluate two digit years with a base date of 1900 rather than 2000.
  private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
      .appendPattern("dd/MM/")
      .appendValueReduced(ChronoField.YEAR, 2, 2, 1900)
      .toFormatter();

  private static final Character DELIMITER = ',';

  public List<CommunityMember> importMembersFromLocal(final String uri) {
    try {
      return  Files.lines(Paths.get(ClassLoader.getSystemResource(uri).toURI()))
          .filter(this::filterValidLine)
          .map(this::getCommunityMember)
          .collect(Collectors.toList());
    } catch (Exception e) {
     throw new DataImportException(String.format("Problem importing file with path: %s", uri), e);
    }
  }

  private CommunityMember getCommunityMember(final String line) {
    final String[] parts = line.split(DELIMITER.toString());
    return new CommunityMember(parts[0].trim(),
        Gender.valueOfIgnoreCase(parts[1].trim()),
        LocalDate.parse(parts[2].trim(), DATE_TIME_FORMATTER));
  }

  private boolean filterValidLine(final String line){
    return StringUtils.isNotBlank(line) && StringUtils.countMatches(line, DELIMITER) == 2;
  }

}
