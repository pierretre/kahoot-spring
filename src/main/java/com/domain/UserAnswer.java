package com.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type")
public class UserAnswer {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    private LocalDateTime datetime = LocalDateTime.now();

    @NonNull
    @Getter
    @Setter
    private boolean isGood;

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
