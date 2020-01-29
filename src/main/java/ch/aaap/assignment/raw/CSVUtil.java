package ch.aaap.assignment.raw;

import ch.aaap.assignment.model.ApplicationModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.Cleanup;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * This is a helper class to read the provided CSV
 *
 * <p>You don't have adapt anything within this class!
 */
public class CSVUtil {

  private static final String POLITICAL_COMMUNITY_FILE = "/GDE_from_be-b-00.04-agv-01.xlsx.csv";
  private static final String POSTAL_COMMUNITY_FILE = "/PLZ6_from_do-t-09.02-gwr-37.xlsx.csv";

  private CSVUtil() {}

  private interface RowParser<T> {
    T parser(CSVRecord record);
  }

  public static Set<CSVPoliticalCommunity> getPoliticalCommunities() {
    try {
      return parseCSV(POLITICAL_COMMUNITY_FILE, CSVUtil::rowToPoliticalCommunity);
    } catch (IOException e) {
      throw new RuntimeException("Could not parse political community csv", e);
    }
  }

  public static Set<CSVPostalCommunity> getPostalCommunities() {

    try {
      return parseCSV(POSTAL_COMMUNITY_FILE, CSVUtil::rowToPostalCommunity);
    } catch (IOException e) {
      throw new RuntimeException("could not parse postal community csv", e);
    }
  }

  public static ApplicationModel getApplicationModel() {
    return new ApplicationModel(getPoliticalCommunities(), getPostalCommunities());
  }

  private static <T> Set<T> parseCSV(String csvFile, RowParser<T> rp) throws IOException {
    InputStream is = CSVUtil.class.getResourceAsStream(csvFile);
    Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    @Cleanup CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
    return StreamSupport.stream(parser.spliterator(), false)
        .map(rp::parser)
        .collect(Collectors.toSet());
  }

  private static CSVPoliticalCommunity rowToPoliticalCommunity(CSVRecord record) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return CSVPoliticalCommunity.builder()
        .number(record.get("GDENR"))
        .name(record.get("GDENAME"))
        .shortName(record.get("GDENAMK"))
        .cantonCode(record.get("GDEKT"))
        .cantonName(record.get("GDEKTNA"))
        .districtNumber(record.get("GDEBZNR"))
        .districtName(record.get("GDEBZNA"))
        .lastUpdate(LocalDate.parse(record.get("GDEMUTDAT"), formatter))
        .build();
  }

  private static CSVPostalCommunity rowToPostalCommunity(CSVRecord record) {
    return CSVPostalCommunity.builder()
        .zipCode(record.get("PLZ4"))
        .zipCodeAddition(record.get("PLZZ"))
        .name(record.get("PLZNAMK"))
        .cantonCode(record.get("KTKZ"))
        .politicalCommunityShortName(record.get("GDENAMK"))
        .politicalCommunityNumber(record.get("GDENR"))
        .build();
  }
}
