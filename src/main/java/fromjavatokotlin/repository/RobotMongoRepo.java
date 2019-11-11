package fromjavatokotlin.repository;

import fromjavatokotlin.model.Robot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RobotMongoRepo extends MongoRepository<Robot, String> {

  List<Robot> findAllByAvailabilityIsTrue();

  default List<Robot> findOnlyAvailable() {
    return findAllByAvailabilityIsTrue();
  }
}
