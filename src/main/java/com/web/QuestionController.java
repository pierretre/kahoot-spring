package com.web;

import com.domain.Question;
import com.dto.QuestionDTO;
import com.exceptions.ResourceNotFoundException;
import com.mapper.MapStructMapper;
import com.services.IQuestionRepository;
import com.domain.MultipleChoiceQuestion;
import com.domain.ShortAnswerQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionRepository questionRepository;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/questions")
    public List<QuestionDTO> all() {
        return questionRepository.findAll().stream()
                .map(q->mapstructMapper.questionToQuestionDTO(q)).collect(Collectors.toList());
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO one(@PathVariable Long id) {
        return mapstructMapper.questionToQuestionDTO(questionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(Question.class, id)));
    }

    @DeleteMapping("/questions/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        questionRepository.deleteById(id);
    }
}
