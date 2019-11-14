package fromjavatokotlin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Identifiable {

  @Id
  private String id;
}
