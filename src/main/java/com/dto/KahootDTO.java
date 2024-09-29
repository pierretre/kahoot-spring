package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KahootDTO {

    private long id;
    private OrganizerDTO creator;
    private List<QuestionDTO> questions;
}
