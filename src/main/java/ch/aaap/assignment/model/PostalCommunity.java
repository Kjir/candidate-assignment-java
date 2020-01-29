package ch.aaap.assignment.model;

import lombok.Value;

@Value
public class PostalCommunity {
  private final String zipCode;
  private final String zipCodeAddition;
  private final String name;
  private final String politicalCommunityNumber;

  // TODO add more features here representing the relations
}
