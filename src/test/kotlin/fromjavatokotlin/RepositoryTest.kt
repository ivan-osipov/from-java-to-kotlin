package fromjavatokotlin

import fromjavatokotlin.model.Robot
import fromjavatokotlin.repository.RobotMongoRepo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class RepositoryTest {

    @Autowired
    lateinit var robotMongoRepo: RobotMongoRepo

    @BeforeEach
    fun setUp() {
        robotMongoRepo.deleteAll()
    }

    @Test
    fun `returns empty list`() {
        assertThat(robotMongoRepo.findAll()).isEmpty()
    }

    @Test
    fun `finds only the pre-saved robot`() {
        val preSavedRobot = robotMongoRepo.save(Robot("robot", 1, null, emptyList()))
        assertThat(robotMongoRepo.findAll()).containsExactly(preSavedRobot)
    }
}