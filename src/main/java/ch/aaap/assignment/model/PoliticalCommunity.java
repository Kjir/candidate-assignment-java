package ch.aaap.assignment.model;

import java.time.LocalDate;
import lombok.Value;

@Value
public class PoliticalCommunity {

  private String number;
  private String name;
  private String shortName;
  private LocalDate lastUpdate;

  // TODO add more features here representing the relations
}
