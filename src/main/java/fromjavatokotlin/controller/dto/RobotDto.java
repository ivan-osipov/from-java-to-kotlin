package fromjavatokotlin.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fromjavatokotlin.model.Identifiable;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RobotDto extends IdentifiableDto {

  @JsonProperty("name")
  private String code;

  private String displayName;

  private Integer mass;

  private Boolean availability;

  private List<AbilityDto> abilities = new ArrayList<>();

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AbilityDto {
    @NotBlank
    private String code;
  }
}
