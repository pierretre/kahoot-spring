package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KahootDTO {

    private long id;
    private OrganizerDTO creator;
    private List<QuestionDTO> questions;
}
