package com.dto;

import com.domain.Kahoot;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
public class QuestionDTO {

    long id;
    String question;
    QuestionType type;

}
