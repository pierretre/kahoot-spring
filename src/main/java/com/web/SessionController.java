package com.web;

import com.dto.SessionDTO;
import com.mapper.MapStructMapper;
import com.services.ISessionDAO;
import com.domain.Session;
import com.domain.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SessionController {

    @Autowired
    private ISessionDAO sessionDAO;

    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    @GetMapping("/sessions")
    public List<SessionDTO> sessions() {
        return sessionDAO.findAll().stream().map(s->mapstructMapper.sessionToSessionDTO(s)).collect(Collectors.toList());
    }

    @GetMapping("/session/{id}")
    public Optional<Session> sessionById(@PathVariable long id) {
        return sessionDAO.findById(id);
    }

    @DeleteMapping("/session/{id}")
    public void deleteSessionById(@PathVariable long id) {
        sessionDAO.deleteById(id);
    }

    /**
     * Add guest to session : TODO
     */

    /**
     * Get session organizer: TODO
     */

    /**
     * Get all user answers associated to this session
     */
    @GetMapping("/session/{id}/useranswers")
    public List<UserAnswer> getUserAnswersForSession(@PathVariable int id) {
        return sessionDAO.getUserAnswersForSessionById(id);
    }
}
