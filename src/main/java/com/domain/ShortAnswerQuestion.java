package com.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("SAQ")
public class ShortAnswerQuestion extends Question {

    @NonNull
    @Getter
    @Setter
    private String expectedResponse;

    public ShortAnswerQuestion(String question, String expectedResponse, Kahoot kahoot) {
        super(question, kahoot);
        this.expectedResponse = expectedResponse;
    }
}