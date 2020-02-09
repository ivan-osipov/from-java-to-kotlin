package fromjavatokotlin.controller

import fromjavatokotlin.config.props.NameGenerationProps
import fromjavatokotlin.controller.dto.RobotDto
import fromjavatokotlin.controller.dto.RobotDto.AbilityDto
import fromjavatokotlin.model.Robot
import fromjavatokotlin.service.RobotService
import fromjavatokotlin.util.DeprecatedApi
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/robots")
class RobotController(
    private val robotService: RobotService,
    private val props: NameGenerationProps
) {

    @GetMapping
    fun findAll(
        @RequestParam(value = "onlyAvailable", defaultValue = "true") onlyAvailable: Boolean
    ): List<RobotDto> {
        val robots = if (onlyAvailable) robotService.findOnlyAvailable() else robotService.findAll()
        return robots.map { it.toDto(props.robotNameTemplate) }
    }

    @DeprecatedApi
    @GetMapping("/all")
    fun findAllOld(): List<Robot> {
        return robotService.findAll()
    }

    @PutMapping("/{id}/available/{availability}")
    fun updateAvailability(
        @PathVariable id: String,
        @PathVariable availability: Boolean
    ) {
        robotService.updateAvailability(id, availability)
    }

    @PostMapping("/{id}/abilities")
    fun addAbility(
        @PathVariable id: String,
        @RequestBody @Valid abilityDto: AbilityDto
    ) {
        val ability = abilityDto.toModel()
        robotService.addAbility(id, ability)
    }
}