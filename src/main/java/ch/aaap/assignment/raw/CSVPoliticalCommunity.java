package ch.aaap.assignment.raw;

import ch.aaap.assignment.model.Canton;
import ch.aaap.assignment.model.District;
import ch.aaap.assignment.model.PoliticalCommunity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CSVPoliticalCommunity {

  // GDENR
  private String number;

  // GDENAME
  private String name;

  // GDENAMK
  private String shortName;

  // GDEKT
  private String cantonCode;

  // GDEKTNA
  private String cantonName;

  // GDEBZNR
  private String districtNumber;

  // GDEBZNA
  private String districtName;

  // GDEMUTDAT
  private LocalDate lastUpdate;

  public PoliticalCommunity getPoliticalCommunity() {
    return new PoliticalCommunity(getNumber(), getName(), getShortName(), getLastUpdate());
  }

  public Canton getCanton() {
    return new Canton(getCantonCode(), getCantonName());
  }

  public District getDistrict() {
    return new District(getDistrictNumber(), getDistrictName());
  }
}
