package com.dto.get;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetDTO {
    private long id;
    private String username;
    private int score;
}
