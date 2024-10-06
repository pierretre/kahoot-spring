package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MultipleChoiceQuestionDTO extends QuestionDTO {
    private List<MultipleChoiceQuestionAnswerDTO> possibleAnswers;
    private MultipleChoiceQuestionAnswerDTO goodAnswer;
}
