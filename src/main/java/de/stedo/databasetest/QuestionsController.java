package de.stedo.databasetest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionsService questionsService;

    @PostMapping
    public Question createNewQuestion(@RequestBody Question frage) {
        return questionsService.createNewQuestion(frage);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionsService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        return ResponseEntity.of(questionsService.getQuestionById(id));
    }

    @GetMapping("/search")
    public List<Question> getByQuestionAnswer(@RequestParam String antwort) {
        return questionsService.getQuestionByAnswer(antwort);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        questionsService.deleteById(id);
    }

}
