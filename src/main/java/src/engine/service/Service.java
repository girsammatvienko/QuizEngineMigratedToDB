package src.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import src.engine.entity.Answer;
import src.engine.entity.Quiz;
import src.engine.entity.QuizNotFoundException;
import src.engine.entity.Solving;
import src.engine.repository.QuizRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private QuizRepository repository;

    @Autowired
    public Service(QuizRepository repository) {
        this.repository = repository;
    }

    public void addQuiz(@Valid Quiz quiz) {
        repository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) repository.findAll();
    }

    public Solving solveQuiz(int id, Answer answer) throws QuizNotFoundException {
        boolean success = false;
        Quiz quiz = repository.findById(id).get();
        int[] rightAnswers = convertListToArray(quiz.getAnswer());
        int[] userAnswers = convertListToArray(answer.getAnswer());
        Arrays.sort(rightAnswers);
        Arrays.sort(userAnswers);
        String feedback = "Wrong answer! Please, try again.";

        if(isEqual(rightAnswers, userAnswers)) {
            success = true;
            feedback = "Congratulations, you're right!";
        }
        return new Solving(success, feedback);
    }


    private static boolean isEqual(int[] arr1, int[] arr2) {
        if(arr1.length != arr2.length) {
            return false;
        }
        for(int i = 0;i < arr1.length;i++) {
            if(arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private List<Integer> convertArrayToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < arr.length;i++) {
            list.add(arr[i]);
        }
        return list;
    }
    private int[] convertListToArray(List<Integer> list) {
        int[] resultArray = new int[list.size()];
        for(int i = 0;i < list.size();i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
    }

    public int getQuizzesAmount() { return (int)repository.count(); }

    public Quiz getQuizById(int id) throws QuizNotFoundException {
        return repository.findById(id).orElseThrow(()-> new QuizNotFoundException()); }

    }