package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserAnswerDTO {

    private long id;
    private LocalDateTime datetime;
    @JsonProperty("correctAnswer")
    private boolean isGood;
    private long userId;
}
