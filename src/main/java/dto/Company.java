package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Company {
  @JsonProperty("name")
  private String name;
  @JsonProperty("catchPhrase")
  private String catchPhrase;
  @JsonProperty("bs")
  private String bs;
}
