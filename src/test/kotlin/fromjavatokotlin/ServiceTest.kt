package fromjavatokotlin

import fromjavatokotlin.model.Robot
import fromjavatokotlin.repository.RobotMongoRepo
import fromjavatokotlin.service.RobotService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import

@DataMongoTest
@Import(RobotService::class)
class ServiceTest {

    @Autowired
    lateinit var robotService: RobotService

    @Autowired
    lateinit var robotMongoRepo: RobotMongoRepo

    @BeforeEach
    fun setUp() {
        robotMongoRepo.deleteAll()
    }

    @Test
    fun `returns empty list of robots`() {
        assertThat(robotService.findAll()).isEmpty()
    }

    @Test
    fun `returns pre-saved list of robots`() {
        val preSavedRobot = robotMongoRepo.save(Robot("robot", 1, true, emptyList()))
        assertThat(robotService.findAll()).containsExactly(preSavedRobot)
    }

    @Test
    fun `returns only available robots`() {
        val preSavedAvailableRobot = robotMongoRepo.save(Robot("robot", 1, true, emptyList()))
        robotMongoRepo.save(Robot("robot 2", 1, false, emptyList()))
        assertThat(robotService.findOnlyAvailable()).containsExactly(preSavedAvailableRobot)
    }

    @Test
    fun `adds ability to robot`() {
        val preSavedAvailableRobot = robotMongoRepo.save(Robot("robot", 1, true, emptyList()))
        robotService.addAbility(preSavedAvailableRobot.id, Robot.Ability("new"))
        val savedRobot = robotMongoRepo.findById(preSavedAvailableRobot.id).get()
        assertThat(savedRobot.abilities).containsExactly(Robot.Ability("new"))
    }

    @Test
    fun `updates availability of a robot`() {
        val preSavedAvailableRobot = robotMongoRepo.insert(Robot("robot", 1, true, emptyList()))
        robotService.updateAvailability(preSavedAvailableRobot.id, false)
        val savedRobot = robotMongoRepo.findById(preSavedAvailableRobot.id).get()
        assertThat(savedRobot.availability).isFalse()
    }

    @Test
    fun `throws NPE on adding ability for robot with null id`() {
        assertThrows<NullPointerException> {
            robotService.addAbility(null, Robot.Ability("new"))
        }
    }

    @Test
    fun `throws NPE on adding NULL instead of ability`() {
        val preSavedAvailableRobot = robotMongoRepo.save(Robot("robot", 1, true, emptyList()))

        assertThrows(NullPointerException::class.java) {
            robotService.addAbility(preSavedAvailableRobot.id, null)
        }
    }
}