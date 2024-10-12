package com.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    private LocalDateTime date = LocalDateTime.now();

    @NonNull
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "kahoot_id")
    private Kahoot kahoot;

    @NonNull
    @Getter
    @Setter
    @OneToMany(mappedBy = "session", cascade=CascadeType.REMOVE)
    private List<User> guests;
}
