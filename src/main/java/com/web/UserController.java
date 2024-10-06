package com.web;

import com.domain.Session;
import com.domain.User;
import com.dto.get.UserGetDTO;
import com.dto.post.UserPostDTO;
import com.exceptions.ResourceNotFoundException;
import com.mapper.MapStructMapper;
import com.services.ISessionRepository;
import com.services.IUserAnswerRepository;
import com.services.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private IUserAnswerRepository userAnswerRepository;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/users")
    public Iterable<UserGetDTO> all() {
        return userRepository.findAll().stream().map(u -> mapstructMapper.userToUserDTO(u)).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserGetDTO one(@PathVariable("id") final Long id) {
        return mapstructMapper
                .userToUserDTO(userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(User.class, id)));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userRepository.findById(id).map(user -> {
            userAnswerRepository.deleteUserAnswerByUser_Id(id);
            userRepository.delete(user);
            return user;
        }).orElseThrow(()-> new ResourceNotFoundException(User.class, id));
    }

    @PostMapping(value = "/users", consumes = "application/json")
    public UserGetDTO addUser(@RequestBody UserPostDTO newUser) {
        return sessionRepository.findById(newUser.getSessionId()).map(session -> {
            User user = mapstructMapper.userDTOToUser(newUser);
                user.setSession(session);
                ArrayList<User> guests = new ArrayList<>(session.getGuests());
                guests.add(user);
                session.setGuests(guests);
                sessionRepository.save(session);
                userRepository.save(user);
                return mapstructMapper.userToUserDTO(user);
        }).orElseThrow(()-> new ResourceNotFoundException(User.class, newUser.getSessionId()));
    }

    @PutMapping(value = "/users/{id}", consumes = "application/json")
    public UserGetDTO updateUser(@RequestBody UserPostDTO newUserDto, @PathVariable Long id) {
        return userRepository.findById(id).map(user -> sessionRepository.findById(newUserDto.getSessionId()).map(session -> {
            User newUser = mapstructMapper.userDTOToUser(newUserDto);

            user.setSession(session);
            user.setUsername(newUser.getUsername());
            user.setScore(newUser.getScore());

            ArrayList<User> guests = new ArrayList<>(session.getGuests());
            guests.remove(user);
            guests.add(user);
            session.setGuests(guests);
            sessionRepository.save(session);
            userRepository.save(user);
            return mapstructMapper.userToUserDTO(user);
        }).orElseThrow(() -> new ResourceNotFoundException(Session.class, id))).orElseThrow(()-> new ResourceNotFoundException(User.class, id));
    }
}
