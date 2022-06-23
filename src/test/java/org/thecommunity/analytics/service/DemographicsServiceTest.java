package org.thecommunity.analytics.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemographicsServiceTest {

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

}