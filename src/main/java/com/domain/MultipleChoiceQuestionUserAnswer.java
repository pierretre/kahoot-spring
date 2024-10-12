package com.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MCQA")
public class MultipleChoiceQuestionUserAnswer extends UserAnswer {

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "mcq_answer_id")
    private MultipleChoiceQuestionAnswer multipleChoiceQuestionAnswer;

    public MultipleChoiceQuestionUserAnswer(MultipleChoiceQuestionAnswer multipleChoiceQuestionAnswer, boolean isGood, User user) {
        super(isGood, user);
        this.multipleChoiceQuestionAnswer = multipleChoiceQuestionAnswer;
    }
}
