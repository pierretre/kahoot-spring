package com.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MCQ")
public class MultipleChoiceQuestion extends Question {

    @NonNull
    @Getter
    @Setter
    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<MultipleChoiceQuestionAnswer> possibleAnswers = new ArrayList<>();

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "good_answer_id")
    private MultipleChoiceQuestionAnswer goodAnswer;


    public MultipleChoiceQuestion(String question,
                                  List<MultipleChoiceQuestionAnswer> possibleAnswers,
                                  MultipleChoiceQuestionAnswer goodAnswer,
                                  Kahoot kahoot) {
        super(question, kahoot);
        this.possibleAnswers = possibleAnswers;
        this.goodAnswer = goodAnswer;
    }
}