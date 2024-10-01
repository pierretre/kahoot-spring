package com.web;

import com.dto.UserDTO;
import com.mapper.MapStructMapper;
import com.services.IUserDAO;
import com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private IUserDAO userDAO;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/users")
    public Iterable<UserDTO> users() {
        return userDAO.findAll().stream().map(u->mapstructMapper.userToUserDTO(u)).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDTO getEmployee(@PathVariable("id") final Long id) {
        return mapstructMapper.userToUserDTO(userDAO.findById(id).orElse(null));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userDAO.deleteById(id);
    }
}
