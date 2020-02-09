package fromjavatokotlin.service

import fromjavatokotlin.constant.DocumentConstants
import fromjavatokotlin.model.Robot
import fromjavatokotlin.model.Robot.Ability
import fromjavatokotlin.repository.RobotMongoRepo
import fromjavatokotlin.util.updateById
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.update
import org.springframework.stereotype.Service

@Service
class RobotService(
    private val robotMongoRepo: RobotMongoRepo,
    private val mongoTemplate: MongoTemplate
) {
    fun findAll(): List<Robot> {
        return robotMongoRepo.findAll()
    }

    fun findOnlyAvailable(): List<Robot> {
        return robotMongoRepo.findOnlyAvailable()
    }

    fun updateAvailability(id: String, availability: Boolean) {
        mongoTemplate.update<Robot>()
            .matching(Query(Criteria.where(DocumentConstants.ID_FIELD).isEqualTo(id)))
            .apply(
                Update.update(
                    Robot.AVAILABILITY_FIELD,
                    availability
                )
            ).first()
    }

    fun addAbility(id: String, ability: Ability) {
        mongoTemplate.updateById<Robot>(id) {
            push(Robot.ABILITIES_FIELD, ability)
        }
    }
}