package fromjavatokotlin

import fromjavatokotlin.model.Robot
import fromjavatokotlin.repository.RobotMongoRepo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class `4_ControllerTests` {

    @Autowired
    lateinit var robotMongoRepo: RobotMongoRepo

    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        robotMongoRepo.deleteAll()
    }

    @Test
    fun `finds empty robots list`() {
        mockMvc.perform(get("/robots"))
                .andExpect(status().isOk)
                .andExpect(content().json("[]"))
    }

    @Test
    fun `finds only available robots by default`() {
        robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))
        robotMongoRepo.insert(Robot("robot_2", 20, false, emptyList()))
        mockMvc.perform(get("/robots"))
                .andExpect(status().isOk)
                .andExpect(content().json("""[
                    {'name':'robot_1', 'availability':true, 'displayName': 'Test Prefix: robot 1', 'mass': 10, 'abilities': [] }
                ]"""))
    }

    @Test
    fun `finds all robots`() {
        robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))
        robotMongoRepo.insert(Robot("robot_2", 20, false, emptyList()))
        mockMvc.perform(get("/robots?onlyAvailable=false"))
                .andExpect(status().isOk)
                .andExpect(content().json("""[
                    {'name':'robot_1', 'availability':true, 'displayName': 'Test Prefix: robot 1', 'mass': 10, 'abilities': [] },
                    {'name':'robot_2', 'availability':false, 'displayName': 'Test Prefix: robot 2', 'mass': 20, 'abilities': [] }
                ]"""))
    }

    @Test
    fun `finds only available robots by criteria`() {
        robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))
        robotMongoRepo.insert(Robot("robot_2", 20, false, emptyList()))
        mockMvc.perform(get("/robots?onlyAvailable=true"))
                .andExpect(status().isOk)
                .andExpect(content().json("""[
                    {'name':'robot_1', 'availability':true, 'displayName': 'Test Prefix: robot 1', 'mass': 10, 'abilities': [] }
                ]"""))
    }

    @Test
    fun `updates availability`() {
        val savedRobot = robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))

        mockMvc.perform(put("/robots/{id}/available/false", savedRobot.id))
                .andExpect(status().isOk)

        val reloadedRobot = robotMongoRepo.findById(savedRobot.id).get()
        assertThat(reloadedRobot.availability).isFalse()
    }

    @Test
    fun `badRequest during availability update if path var has incorrect type`() {
        val savedRobot = robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))

        mockMvc.perform(put("/robots/{id}/available/abc", savedRobot.id))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `adds new ability`() {
        val savedRobot = robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))

        mockMvc.perform(
                post("/robots/{id}/abilities", savedRobot.id)
                        .content("""{"code":"grasp"}""")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk)

        val reloadedRobot = robotMongoRepo.findById(savedRobot.id).get()
        assertThat(reloadedRobot.abilities).containsExactly(Robot.Ability("grasp"))
    }

    @Test
    fun `badRequest on new ability adding with blank code`() {
        val savedRobot = robotMongoRepo.insert(Robot("robot_1", 10, true, emptyList()))

        mockMvc.perform(
                post("/robots/{id}/abilities", savedRobot.id)
                        .content("""{"code":"  "}""")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isBadRequest)
    }
}