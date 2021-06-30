package src.engine.controllers;

import src.engine.entity.Answer;
import src.engine.entity.Quiz;
import src.engine.entity.QuizNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.engine.entity.Solving;
import src.engine.service.Service;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
@Validated
public class QuizController {
    private final Service service;

    @Autowired
    public QuizController(Service service) {
        this.service = service;
    }

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        service.addQuiz(quiz);
        return quiz;
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) throws QuizNotFoundException {
        return service.getQuizById(id);
    }

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes() {
        return service.getAllQuizzes();
    }

    @PostMapping("/quizzes/{id}/solve")
    public Solving solveQuiz(@PathVariable int id, @RequestBody Answer answer) throws QuizNotFoundException {
        return service.solveQuiz(id, answer);
    }

}
