package fromjavatokotlin.controller;

import fromjavatokotlin.controller.dto.RobotDto;
import fromjavatokotlin.model.Robot;
import fromjavatokotlin.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotController {

  private final RobotService robotService;

  private final ModelMapper modelMapper;

  @GetMapping
  public List<RobotDto> findAll(
      @RequestParam(value = "onlyAvailable", defaultValue = "true") boolean onlyAvailable
  ) {
    List<Robot> robots = onlyAvailable ? robotService.findOnlyAvailable() : robotService.findAll();
    return modelMapper.map(robots, new TypeToken<List<RobotDto>>() {
    }.getType());
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
    Robot.Ability ability = modelMapper.map(abilityDto, Robot.Ability.class);
    robotService.addAbility(id, ability);
  }
}
