package fromjavatokotlin.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("app")
@Configuration
public class NameGenerationProps {
  private String robotNameTemplate;
}
