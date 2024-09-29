package com.web;

import com.services.IQuestionDAO;
import com.domain.Question;
import com.domain.MultipleChoiceQuestion;
import com.domain.ShortAnswerQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionDAO questionDAO;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    @GetMapping("/question/{id}")
    public Optional<Question> questionById(@PathVariable Long id) {
        return questionDAO.findById(id);
    }

    @DeleteMapping("/question/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        questionDAO.deleteById(id);
    }

    @PostMapping("/qcm-question")
    public void createQuestion(@RequestBody MultipleChoiceQuestion question) {
        questionDAO.save(question);
    }

    @PostMapping("/short-answer-question")
    public void createQuestion(@RequestBody ShortAnswerQuestion question) {
        questionDAO.save(question);
    }




}
