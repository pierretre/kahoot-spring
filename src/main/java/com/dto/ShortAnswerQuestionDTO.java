package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShortAnswerQuestionDTO extends QuestionDTO {
    private String expectedResponse;
}
