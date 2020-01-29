package ch.aaap.assignment.raw;

import ch.aaap.assignment.model.PostalCommunity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CSVPostalCommunity {

  // PLZ4
  private String zipCode;

  // PLZZ
  private String zipCodeAddition;

  // PLZNAMK
  private String name;

  // KTKZ
  private String cantonCode;

  // GDENR
  private String politicalCommunityNumber;

  // GDENAMK
  private String politicalCommunityShortName;

  public PostalCommunity getPostalCommunity() {
    return new PostalCommunity(
        getZipCode(), getZipCodeAddition(), getName(), getPoliticalCommunityNumber());
  }
}
