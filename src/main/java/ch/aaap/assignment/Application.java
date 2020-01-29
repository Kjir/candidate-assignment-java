package ch.aaap.assignment;

import ch.aaap.assignment.model.Model;
import ch.aaap.assignment.raw.CSVUtil;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;

public class Application {

  private @Getter Model model = null;

  public Application() {
    initModel();
  }

  public static void main(String[] args) {
    new Application();
  }

  /** Reads the CSVs and initializes a in memory model */
  private void initModel() {
    this.model = CSVUtil.getApplicationModel();
  }

  /**
   * @param canton code of a canton (e.g. ZH)
   * @return amount of political communities in given canton
   */
  public long getAmountOfPoliticalCommunitiesInCanton(String cantonCode) {
    Set<?> politicalCommunities = this.model.getPoliticalCommunitiesByCanton().get(cantonCode);
    if(politicalCommunities == null) {
      throw new IllegalArgumentException("Invalid canton code");
    }
    return politicalCommunities.size();

  }

  /**
   * @param canton code of a canton (e.g. ZH)
   * @return amount of districts in given canton
   */
  public long getAmountOfDistrictsInCanton(String cantonCode) {
    Set<?> districts = this.model.getDistrictsByCanton().get(cantonCode);
    if(districts == null) {
      throw new IllegalArgumentException("Invalid canton code");
    }
    return districts.size();
  }

  /**
   * @param district number of a district (e.g. 101)
   * @return amount of districts in given canton
   */
  public long getAmountOfPoliticalCommunitiesInDistict(String districtNumber) {
    Set<?> politicalCommunities = this.model.getPoliticalCommunitiesByDistrict().get(districtNumber);
    if (politicalCommunities == null) {
      throw new IllegalArgumentException("Invalid district number");
    }
    return politicalCommunities.size();
  }

  /**
   * FIXME: This function does not work for all ZIP codes. A zip code can belong to more than one district,
   *  making it thus impossible to provide a correct implementation with these return types.
   *  Example zip code: 8866
   * 
   * @param zip code 4 digit zip code
   * @return district that belongs to specified zip code
   */
  public String getDistrictForZipCode(String zipCode) {
    return this.model.getDistrictsByZipCode(zipCode);
  }

  /**
   * @param postal community name
   * @return lastUpdate of the political community by a given postal community name
   */
  public LocalDate getLastUpdateOfPoliticalCommunityByPostalCommunityName(
      String postalCommunityName) {
    // TODO implementation
    throw new RuntimeException("Not yet implemented");
  }

  /**
   * https://de.wikipedia.org/wiki/Kanton_(Schweiz)
   *
   * @return amount of canton
   */
  public long getAmountOfCantons() {
    return model.getCantons().size();
  }

  /**
   * https://de.wikipedia.org/wiki/Kommunanz
   *
   * @return amount of political communities without postal communities
   */
  public long getAmountOfPoliticalCommunityWithoutPostalCommunities() {
    // TODO implementation
    throw new RuntimeException("Not yet implemented");
  }
}
