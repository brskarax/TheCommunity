package org.thecommunity.analytics.service;

import org.thecommunity.analytics.exception.MultipleRecordsException;
import org.thecommunity.analytics.exception.RecordsNotFoundException;
import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;

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

  public long findMembersAgeDifferenceInDays(final String firstMemberName, final String secondMemberName){
    final List<CommunityMember> members = findMembers(asList(firstMemberName, secondMemberName));
    return DAYS.between(members.get(0).dob(), members.get(1).dob());
  }

  private List<CommunityMember> findMembers(final List<String> names) {
    final List<CommunityMember> members = dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .filter(e -> names.contains(e.name()))
        .collect(Collectors.toList());
    if(names.size() < members.size()) {
      throw new RecordsNotFoundException(String.format("Some of the records cannot be found: %s", names));
    } else if(names.size() > members.size()) {
      throw new MultipleRecordsException(String.format("Multiple records found for some of the given names: %s", names));
    } else {
      return members;
    }
  }

  private long findGenderCount(final Gender gender) {
    return dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .filter(e -> gender.equals(e.gender()))
        .count();
  }

}
