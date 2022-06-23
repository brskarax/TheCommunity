package org.thecommunity.analytics.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemographicsServiceTest {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private DataImportService dataImportService;
  private DemographicsService demographicsService;

  @BeforeEach
  public void setUp() {
    dataImportService = new DataImportService();
    demographicsService = new DemographicsService(dataImportService);
  }

  @Test
  public void testFindNumberOfMales_shouldExecuteSuccessfully(){
    assertEquals(3, demographicsService.findNumberOfMales());
  }

  @Test
  public void testFindOldestMember_shouldExecuteSuccessfully(){
    final CommunityMember expected = new CommunityMember("Wes Jackson", Gender.MALE, LocalDate.parse("14/08/1974", DATE_TIME_FORMATTER));
    assertEquals(expected, demographicsService.findOldestMember());
  }

}