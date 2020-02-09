package fromjavatokotlin.controller;

import fromjavatokotlin.config.props.NameGenerationProps;
import fromjavatokotlin.controller.dto.RobotDto;
import fromjavatokotlin.model.Robot;
import fromjavatokotlin.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static fromjavatokotlin.controller.MappingsKt.toDto;
import static fromjavatokotlin.controller.MappingsKt.toModel;

@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotController {

  private final RobotService robotService;

  private final NameGenerationProps props;

  @GetMapping
  public List<RobotDto> findAll(
      @RequestParam(value = "onlyAvailable", defaultValue = "true") boolean onlyAvailable
  ) {
    List<Robot> robots = onlyAvailable ? robotService.findOnlyAvailable() : robotService.findAll();
    return robots.stream().map((r) -> toDto(r, props.getRobotNameTemplate())).collect(Collectors.toList());
  }

  @Deprecated
  @GetMapping("/all")
  public List<Robot> findAllOld() {
    return robotService.findAll();
  }

  @PutMapping("/{id}/available/{availability}")
  public void updateAvailability(
      @PathVariable String id,
      @PathVariable boolean availability
  ) {
    robotService.updateAvailability(id, availability);
  }

  @PostMapping("/{id}/abilities")
  public void addAbility(
      @PathVariable String id,
      @Valid @RequestBody RobotDto.AbilityDto abilityDto
  ) {
    Robot.Ability ability = toModel(abilityDto);
    robotService.addAbility(id, ability);
  }
}
