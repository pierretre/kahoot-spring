package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SessionDTO implements Serializable {

    private long id;
    private LocalDateTime date;
    private KahootDTO kahoot;
    private List<UserDTO> guests = new ArrayList<>();
}
