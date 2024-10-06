package com.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPostDTO {
    private String username;
    private Long sessionId;
    private int score;
}
