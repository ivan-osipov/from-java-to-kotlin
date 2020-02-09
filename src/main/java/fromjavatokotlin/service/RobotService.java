package fromjavatokotlin.service;

import fromjavatokotlin.model.Robot;
import fromjavatokotlin.repository.RobotMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
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

  public void updateAvailability(@Nonnull String id, boolean availability) {
    checkNotNull(id);
    mongoTemplate.updateFirst(
        new Query(where(ID_FIELD).is(id)),
        Update.update(Robot.AVAILABILITY_FIELD, availability),
        Robot.class
    );
  }

  public void addAbility(/*@Nonnull*/ String id, /*@Nonnull*/ Robot.Ability ability) {
    checkNotNull(id);
    checkNotNull(ability);
    mongoTemplate.updateFirst(
        new Query(where(ID_FIELD).is(id)),
        new Update().push(ABILITIES_FIELD, ability),
        Robot.class
    );
  }
}
