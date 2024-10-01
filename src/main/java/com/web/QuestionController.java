package com.web;

import com.dto.QuestionDTO;
import com.mapper.MapStructMapper;
import com.services.IQuestionDAO;
import com.domain.Question;
import com.domain.MultipleChoiceQuestion;
import com.domain.ShortAnswerQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionDAO questionDAO;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestions() {
        return questionDAO.findAll().stream().map(q->mapstructMapper.questionToQuestionDTO(q)).collect(Collectors.toList());
    }

    @GetMapping("/question/{id}")
    public QuestionDTO questionById(@PathVariable Long id) {
        return mapstructMapper.questionToQuestionDTO(questionDAO.findById(id).orElse(null));
    }

    @DeleteMapping("/question/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        questionDAO.deleteById(id);
    }

    @PostMapping("/multiple-choice-question")
    public void createQuestion(@RequestBody MultipleChoiceQuestion question) {
        questionDAO.save(question);
    }

    @PostMapping("/short-answer-question")
    public void createQuestion(@RequestBody ShortAnswerQuestion question) {
        questionDAO.save(question);
    }




}
