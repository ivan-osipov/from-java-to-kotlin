package fromjavatokotlin.config;

import fromjavatokotlin.config.props.NameGenerationProps;
import fromjavatokotlin.controller.dto.RobotDto;
import fromjavatokotlin.model.Robot;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ModelMapperConfig {

  private final NameGenerationProps nameGenerationProps;

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    Converter<String, String> displayNameConverter = context ->
        String.format(
            nameGenerationProps.getRobotNameTemplate(),
            context.getSource().replace('_', ' ')
        );

    modelMapper.addMappings(new PropertyMap<Robot, RobotDto>() {
      protected void configure() {
        using(displayNameConverter).map(source.getCode()).setDisplayName(null);
      }
    });

    return modelMapper;
  }
}
