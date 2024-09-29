package com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor()
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
public  abstract class Question {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @NonNull
    @Getter
    @Setter
    private String question;

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "kahoot_id")
    private Kahoot kahoot;
}
