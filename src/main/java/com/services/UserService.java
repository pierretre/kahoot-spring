package com.services;

import com.domain.Session;
import com.domain.User;
import com.dto.get.UserGetDTO;
import com.dto.post.UserPostDTO;
import com.exceptions.ResourceNotFoundException;
import com.mapper.MapStructMapper;
import com.repositories.ISessionRepository;
import com.repositories.IUserAnswerRepository;
import com.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private IUserAnswerRepository userAnswerRepository;

    private MapStructMapper mapper = MapStructMapper.INSTANCE;

    public List<UserGetDTO> getAll() {
        return userRepository.findAll().stream().map(u -> mapper.userToUserDTO(u)).collect(Collectors.toList());
    }

    public UserGetDTO get(long id) {
        return mapper.userToUserDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, id)));
    }

    public void delete(long id) {
        userRepository.findById(id).map(user -> {
            userAnswerRepository.deleteUserAnswerByUser_Id(id);
            userRepository.delete(user);
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException(User.class, id));
    }

    public UserGetDTO post(UserPostDTO userPostDTO) {
        return sessionRepository.findById(userPostDTO.getSessionId()).map(session -> {
            User user = mapper.userDTOToUser(userPostDTO);
            user.setSession(session);
            ArrayList<User> guests = new ArrayList<>(session.getGuests());
            guests.add(user);
            session.setGuests(guests);
            sessionRepository.save(session);
            userRepository.save(user);
            return mapper.userToUserDTO(user);
        }).orElseThrow(() -> new ResourceNotFoundException(User.class, userPostDTO.getSessionId()));
    }

    public UserGetDTO put(long id, UserPostDTO userPostDTO) {
        return userRepository.findById(id).map(user -> sessionRepository.findById(userPostDTO.getSessionId()).map(session -> {
            User newUser = mapper.userDTOToUser(userPostDTO);

            user.setSession(session);
            user.setUsername(newUser.getUsername());
            user.setScore(newUser.getScore());

            ArrayList<User> guests = new ArrayList<>(session.getGuests());
            guests.remove(user);
            guests.add(user);
            session.setGuests(guests);
            sessionRepository.save(session);
            userRepository.save(user);
            return mapper.userToUserDTO(user);
        }).orElseThrow(() -> new ResourceNotFoundException(Session.class, id))).orElseThrow(() -> new ResourceNotFoundException(User.class, id));
    }
}