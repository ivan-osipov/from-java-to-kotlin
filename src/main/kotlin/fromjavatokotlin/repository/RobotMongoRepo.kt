package fromjavatokotlin.repository

import fromjavatokotlin.model.Robot
import org.springframework.data.mongodb.repository.MongoRepository

interface RobotMongoRepo : MongoRepository<Robot, String> {
    fun findAllByAvailabilityIsTrue(): List<Robot>
    @JvmDefault
    fun findOnlyAvailable(): List<Robot> {
        return findAllByAvailabilityIsTrue()
    }
}