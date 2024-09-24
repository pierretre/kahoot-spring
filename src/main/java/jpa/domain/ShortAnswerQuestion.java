package jpa.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAQ")
public class ShortAnswerQuestion extends Question {

    private String expectedResponse;

    public ShortAnswerQuestion() {
    }

    public ShortAnswerQuestion(String question, String expectedResponse) {
        super(question);
        this.expectedResponse = expectedResponse;
    }

    public String getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(String expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}