package ch.aaap.assignment.model;

import ch.aaap.assignment.raw.CSVPoliticalCommunity;
import ch.aaap.assignment.raw.CSVPostalCommunity;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ApplicationModel implements Model {
  Map<String, CSVPoliticalCommunity> politicalCommunities;
  Map<String, Set<CSVPostalCommunity>> postalCommunities;

  public ApplicationModel(
      Set<CSVPoliticalCommunity> politicalCommunities, Set<CSVPostalCommunity> postalCommunities) {
    this.politicalCommunities =
        politicalCommunities.stream()
            .collect(Collectors.toMap(CSVPoliticalCommunity::getNumber, Function.identity()));
    this.postalCommunities =
        postalCommunities.stream()
            .collect(Collectors.groupingBy(CSVPostalCommunity::getZipCode, Collectors.toSet()));
  }

  @Override
  public Set<PoliticalCommunity> getPoliticalCommunities() {
    return politicalCommunities.values().stream()
        .map(pc -> pc.getPoliticalCommunity())
        .collect(Collectors.toSet());
  }

  @Override
  public Set<PostalCommunity> getPostalCommunities() {
    return postalCommunities.values().stream()
        .flatMap(pcSet -> pcSet.stream().map(pc -> pc.getPostalCommunity()))
        .collect(Collectors.toSet());
  }

  @Override
  public Set<Canton> getCantons() {
    return politicalCommunities.values().stream()
        .map(pc -> pc.getCanton())
        .collect(Collectors.toSet());
  }

  @Override
  public Set<District> getDistricts() {
    return politicalCommunities.values().stream()
        .map(pc -> pc.getDistrict())
        .collect(Collectors.toSet());
  }

  public Map<String, Set<PoliticalCommunity>> getPoliticalCommunitiesByCanton() {
    return this.politicalCommunities.values().stream()
        .collect(
            Collectors.groupingBy(
                CSVPoliticalCommunity::getCantonCode,
                Collectors.mapping(
                    CSVPoliticalCommunity::getPoliticalCommunity, Collectors.toSet())));
  }

  public Map<String, Set<District>> getDistrictsByCanton() {
    return this.politicalCommunities.values().stream()
        .collect(
            Collectors.groupingBy(
                CSVPoliticalCommunity::getCantonCode,
                Collectors.mapping(CSVPoliticalCommunity::getDistrict, Collectors.toSet())));
  }

  public Map<String, Set<PoliticalCommunity>> getPoliticalCommunitiesByDistrict() {
    return this.politicalCommunities.values().stream()
        .collect(
            Collectors.groupingBy(
                CSVPoliticalCommunity::getDistrictNumber,
                Collectors.mapping(
                    CSVPoliticalCommunity::getPoliticalCommunity, Collectors.toSet())));
  }
}
