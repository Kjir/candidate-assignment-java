package ch.aaap.assignment.model;

import ch.aaap.assignment.raw.CSVPoliticalCommunity;
import ch.aaap.assignment.raw.CSVPostalCommunity;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationModel implements Model {
  Set<CSVPoliticalCommunity> politicalCommunities;
  Set<CSVPostalCommunity> postalCommunities;

  public ApplicationModel(
      Set<CSVPoliticalCommunity> politicalCommunities, Set<CSVPostalCommunity> postalCommunities) {
    this.politicalCommunities = politicalCommunities;
    this.postalCommunities = postalCommunities;
  }

  @Override
  public Set<PoliticalCommunity> getPoliticalCommunities() {
    return politicalCommunities.stream()
        .map(pc -> pc.getPoliticalCommunity())
        .collect(Collectors.toSet());
  }

  @Override
  public Set<PostalCommunity> getPostalCommunities() {
    return postalCommunities.stream()
        .map(pc -> pc.getPostalCommunity())
        .collect(Collectors.toSet());
  }

  @Override
  public Set<Canton> getCantons() {
    return politicalCommunities.stream().map(pc -> pc.getCanton()).collect(Collectors.toSet());
  }

  @Override
  public Set<District> getDistricts() {
    return politicalCommunities.stream().map(pc -> pc.getDistrict()).collect(Collectors.toSet());
  }

  public Map<String, Set<PoliticalCommunity>> getPoliticalCommunitiesByCanton() {
    return this.politicalCommunities.stream()
        .collect(
            Collectors.groupingBy(
                CSVPoliticalCommunity::getCantonCode,
                Collectors.mapping(
                    CSVPoliticalCommunity::getPoliticalCommunity, Collectors.toSet())));
  }

  public Map<String, Set<District>> getDistrictsByCanton() {
    return this.politicalCommunities.stream()
        .collect(
            Collectors.groupingBy(
                CSVPoliticalCommunity::getCantonCode,
                Collectors.mapping(CSVPoliticalCommunity::getDistrict, Collectors.toSet())));
  }
}
