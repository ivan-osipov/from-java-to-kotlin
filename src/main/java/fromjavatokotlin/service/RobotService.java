package fromjavatokotlin.service;

import com.google.common.base.Preconditions;
import fromjavatokotlin.model.Robot;
import fromjavatokotlin.repository.RobotMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static fromjavatokotlin.constant.DocumentConstants.ID_FIELD;
import static fromjavatokotlin.model.Robot.ABILITIES_FIELD;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class RobotService {

  private final RobotMongoRepo robotMongoRepo;

  private final MongoTemplate mongoTemplate;

  public List<Robot> findAll() {
    return robotMongoRepo.findAll();
  }

  public List<Robot> findOnlyAvailable() {
    return robotMongoRepo.findOnlyAvailable();
  }

  public void updateAvailability(String id, boolean availability) {
    mongoTemplate.updateFirst(
        new Query(where(ID_FIELD).is(id)),
        Update.update(Robot.AVAILABILITY_FIELD, availability),
        Robot.class
    );
  }

  public void addAbility(String id, Robot.Ability ability) {
    Preconditions.checkNotNull(id);
    Preconditions.checkNotNull(ability);
    mongoTemplate.updateFirst(
        new Query(where(ID_FIELD).is(id)),
        new Update().push(ABILITIES_FIELD, ability),
        Robot.class
    );
  }
}
