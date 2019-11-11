package fromjavatokotlin.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("robots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Robot extends Identifiable {

  public static final String AVAILABILITY_FIELD = "availability";
  public static final String ABILITIES_FIELD = "abilities";

  private String code;

  private Integer mass;

  @Field(AVAILABILITY_FIELD)
  private Boolean availability;

  @Field(ABILITIES_FIELD)
  private List<Ability> abilities = new ArrayList<>();

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  @EqualsAndHashCode
  public static class Ability {
    private String code;
  }

}
