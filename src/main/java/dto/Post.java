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
public class Post {
  @JsonProperty("userId")
  private int userId;
  @JsonProperty("id")
  private int id;
  @JsonProperty("title")
  private String title;
  @JsonProperty("body")
  private String body;
}
