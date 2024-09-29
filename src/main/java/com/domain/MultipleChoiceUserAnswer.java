package com.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MCQA")
public class MultipleChoiceUserAnswer extends UserAnswer {

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "mcq_answer_id")
    private MultipleChoiceQuestionAnswer multipleChoiceQuestionAnswer;

    public MultipleChoiceUserAnswer(MultipleChoiceQuestionAnswer multipleChoiceQuestionAnswer, boolean isGood, User user) {
        super(isGood, user);
        this.multipleChoiceQuestionAnswer = multipleChoiceQuestionAnswer;
    }
}
