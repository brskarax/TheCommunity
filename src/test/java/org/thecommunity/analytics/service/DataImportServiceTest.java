package org.thecommunity.analytics.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thecommunity.analytics.exception.DataImportException;
import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataImportServiceTest {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private DataImportService dataImportService;

  @BeforeEach
  public void setUp() {
    dataImportService = new DataImportService();
  }

  @Test
  public void testImportMembers_shouldExecuteSuccessfully() {
    final List<CommunityMember> expected = createExpectedMembersList();
    final List<CommunityMember> result = dataImportService.importMembersFromLocal("AddressBookTest");
    assertEquals(expected, result);
  }

  @Test
  public void testImportMembers_shouldThrowException_whenFileNotExists() {
    final DataImportException thrown = Assertions.assertThrows(DataImportException.class,
        () ->  dataImportService.importMembersFromLocal( "FileNotExists"));
    assertEquals("Problem importing file with path: FileNotExists", thrown.getMessage());
  }

  private List<CommunityMember> createExpectedMembersList() {
    return Arrays.asList(
        new CommunityMember("Bill McKnight", Gender.MALE, LocalDate.parse("16/03/1977", DATE_TIME_FORMATTER)),
        new CommunityMember("Paul Robinson", Gender.MALE, LocalDate.parse("15/01/1985", DATE_TIME_FORMATTER)),
        new CommunityMember("Gemma Lane", Gender.FEMALE, LocalDate.parse("20/11/1991", DATE_TIME_FORMATTER)),
        new CommunityMember("Sarah Stone", Gender.FEMALE, LocalDate.parse("20/09/1980", DATE_TIME_FORMATTER)),
        new CommunityMember("Wes Jackson", Gender.MALE, LocalDate.parse("14/08/1974", DATE_TIME_FORMATTER))
    );
  }

}