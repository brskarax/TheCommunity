package org.thecommunity.analytics.service;

import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.util.Comparator;

public class DemographicsService {

  private static final String RESOURCE_FILE = "AddressBook";

  private final DataImportService dataImportService;

  public DemographicsService(final DataImportService dataImportService) {
    this.dataImportService = dataImportService;
  }

  public long findNumberOfMales(){
    return findGenderCount(Gender.MALE);
  }

  public CommunityMember findOldestMember(){
    return dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .sorted(Comparator.comparing(CommunityMember::dob))
        .findFirst()
        .orElseGet(null);
  }

  private long findGenderCount(final Gender gender) {
    return dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .filter(e -> gender.equals(e.gender()))
        .count();
  }


}
