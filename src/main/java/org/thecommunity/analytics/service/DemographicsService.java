package org.thecommunity.analytics.service;

import org.thecommunity.analytics.exception.MultipleRecordException;
import org.thecommunity.analytics.exception.NoRecordsFoundException;
import org.thecommunity.analytics.model.CommunityMember;
import org.thecommunity.analytics.model.Gender;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
    final CommunityMember firstMember = findMember(firstMemberName);
    final CommunityMember secondMember = findMember(secondMemberName);
    return DAYS.between(firstMember.dob(), secondMember.dob());
  }

  private CommunityMember findMember(final String name) {
    final List<CommunityMember> members = dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .filter(e -> e.name().equals(name))
        .collect(Collectors.toList());
    if(members.isEmpty()) {
      throw new NoRecordsFoundException(String.format("No records found for the given name: %s", name));
    } else if(members.size() > 1) {
      throw new MultipleRecordException(String.format("Multiple records found for the given name: %s", name));
    } else {
      return members.get(0);
    }
  }

  private long findGenderCount(final Gender gender) {
    return dataImportService.importMembersFromLocal(RESOURCE_FILE).stream()
        .filter(e -> gender.equals(e.gender()))
        .count();
  }


}
