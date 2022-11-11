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
public class User {
  @JsonProperty("id")
  private int id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("username")
  private String username;
  @JsonProperty("email")
  private String email;
  @JsonProperty("address")
  private Address address;
  @JsonProperty("phone")
  private Address phone;
  @JsonProperty("website")
  private Address website;
  @JsonProperty("company")
  private Company company;
}
