package src.engine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.engine.entity.Quiz;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
 Optional<Quiz> findByTitle(String title);

}
