package com.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("SA")
public class ShortUserAnswer extends UserAnswer {

    @NonNull
    @Getter
    @Setter
    private String shortAnswer;

    public ShortUserAnswer(String shortAnswer, boolean isGood, User user) {
        super(isGood, user);
        this.shortAnswer = shortAnswer;
    }
}
