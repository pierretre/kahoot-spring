package jpa.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DiscriminatorValue("SA")
public class ShortUserAnswer extends UserAnswer{
private String shortAnswer;

    public ShortUserAnswer() {}

    public ShortUserAnswer(String shortAnswer, LocalDateTime datetime, boolean isGood, User user) {
        super(datetime, isGood, user);
        this.shortAnswer = shortAnswer;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }
}
