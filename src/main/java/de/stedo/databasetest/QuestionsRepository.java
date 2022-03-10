package de.stedo.databasetest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends MongoRepository<Question, String> {

    List<Question> findByAnswer(String answer);

}
