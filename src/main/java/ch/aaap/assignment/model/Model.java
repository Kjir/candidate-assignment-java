package ch.aaap.assignment.model;

import java.util.Map;
import java.util.Set;

public interface Model {

  public Set<PoliticalCommunity> getPoliticalCommunities();

  public Set<PostalCommunity> getPostalCommunities();

  public Set<Canton> getCantons();

  public Set<District> getDistricts();

  public Map<String, Set<PoliticalCommunity>> getPoliticalCommunitiesByCanton();

  public Map<String, Set<District>> getDistrictsByCanton();

  public Map<String, Set<PoliticalCommunity>> getPoliticalCommunitiesByDistrict();

  public String getDistrictsByZipCode(String zipCode);
}
