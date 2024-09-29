package com.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestionAnswer {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "question_id")
    private MultipleChoiceQuestion question;

    @NonNull
    @Getter
    @Setter
    private String answerText;
}
