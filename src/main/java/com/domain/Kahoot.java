package com.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Kahoot {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Organizer creator;

    @NonNull
    @Getter
    @Setter
    @OneToMany(mappedBy = "kahoot")
    private List<Question> questions;

    @NonNull
    @Getter
    @Setter
    private LocalDateTime creationDate = LocalDateTime.now();
}
