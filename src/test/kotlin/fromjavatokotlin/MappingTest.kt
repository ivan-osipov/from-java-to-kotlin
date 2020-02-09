package fromjavatokotlin

import fromjavatokotlin.config.props.NameGenerationProps
import fromjavatokotlin.controller.dto.RobotDto
import fromjavatokotlin.controller.toDto
import fromjavatokotlin.controller.toModel
import fromjavatokotlin.model.Robot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(
        classes = [MappingTest.ConfigurationPropertiesConfig::class],
        initializers = [ConfigFileApplicationContextInitializer::class]
)
class MappingTest {

    @Autowired
    lateinit var props: NameGenerationProps

    @Test
    fun `maps Robot to RobotDto`() {
        val robot = Robot("robot", 10, false, listOf(Robot.Ability("grasp"))).apply { id = "robot_id" }

        val dto = robot.toDto(props.robotNameTemplate)

        val expectedDto = RobotDto("robot", "Test Prefix: robot", 10, false, listOf(RobotDto.AbilityDto("grasp")))
                .apply { id = "robot_id" }
        assertThat(dto).isEqualTo(expectedDto)
    }

    @Test
    fun `maps AbilityDto to Ability`() {
        val abilityDto = RobotDto.AbilityDto("grasp")

        val model = abilityDto.toModel()

        assertThat(model).isEqualTo(Robot.Ability("grasp"))
    }

    @EnableConfigurationProperties(NameGenerationProps::class)
    class ConfigurationPropertiesConfig

}