package com.web;

import com.domain.Session;
import com.dto.SessionDTO;
import com.dto.UserAnswerDTO;
import com.exceptions.ResourceNotFoundException;
import com.mapper.MapStructMapper;
import com.services.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
public class SessionController {

    @Autowired
    private ISessionRepository sessionRepository;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/sessions")
    public List<SessionDTO> all() {
        return sessionRepository.findAll().stream().map(s -> mapstructMapper.sessionToSessionDTO(s)).collect(Collectors.toList());
    }

    @GetMapping("/sessions/{id}")
    public SessionDTO one(@PathVariable long id) {
        return mapstructMapper.sessionToSessionDTO(sessionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(Session.class, id)));
    }

    @DeleteMapping("/sessions/{id}")
    public void deleteSessionById(@PathVariable long id) {
        sessionRepository.deleteById(id);
    }

    /**
     * Get all user answers associated to this session
     */
    @GetMapping("/sessions/{id}/useranswers")
    public List<UserAnswerDTO> getUserAnswersForSession(@PathVariable int id) {
        return sessionRepository.getUserAnswersForSessionById(id).stream()
                .map(ua -> mapstructMapper.userAnswerToUserAnswerDTO(ua)).collect(Collectors.toList());
    }
}
