package com.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @NonNull
    @Getter
    @Setter
    private String username;

    @NonNull
    @Getter
    @Setter
    private int score = 0;

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
    private List<UserAnswer> userAnswers = new ArrayList<>();
}
