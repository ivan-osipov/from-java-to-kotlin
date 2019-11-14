package fromjavatokotlin.config;

import fromjavatokotlin.config.props.NameGenerationProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NameGenerationProps.class)
public class PropsConfig {
}
