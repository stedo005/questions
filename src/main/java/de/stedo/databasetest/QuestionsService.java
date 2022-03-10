package de.stedo.databasetest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    public Question createNewQuestion(Question frage) {
        return questionsRepository.save(frage);
    }

    public Optional<Question> getQuestionById(String id) {
        return questionsRepository.findById(id);
    }

    public List<Question> getQuestionByAnswer(String antwort) {
        return questionsRepository.findByAnswer(antwort);
    }

    public void deleteById(String id) {
        questionsRepository.deleteById(id);
    }

    public List<Question> getAllQuestions() {
        return questionsRepository.findAll();
    }



}
