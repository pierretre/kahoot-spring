package com.dto;

import com.dto.get.UserGetDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SessionDTO implements Serializable {

    private long id;
    private LocalDateTime date;
    private KahootDTO kahoot;
    private List<UserGetDTO> guests = new ArrayList<>();
}
