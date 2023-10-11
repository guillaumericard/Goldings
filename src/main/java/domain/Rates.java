package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"success", "timestamp"})
public class Rates {
  @JsonProperty("base")
  String base;
  @JsonProperty("date")
  String date;
  @JsonProperty("rates")
  Map<String,String> rates;

}
