package fromjavatokotlin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import static fromjavatokotlin.constant.DocumentConstants.ID_FIELD;

@Data
public class Identifiable {

  @Id
  @Field(ID_FIELD)
  private String id;
}
