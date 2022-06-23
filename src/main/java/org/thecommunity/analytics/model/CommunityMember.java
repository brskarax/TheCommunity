package org.thecommunity.analytics.model;

import java.time.LocalDate;

public record CommunityMember(String name, Gender gender, LocalDate dob) {}
