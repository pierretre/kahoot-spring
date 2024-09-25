package jpa.web;

import jpa.domain.Session;
import jpa.domain.UserAnswer;
import jpa.services.ISessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SessionController {
    @Autowired
    private ISessionDAO sessionDAO;

    @GetMapping("/sessions")
    public List<Session> sessions() {
        return sessionDAO.findAll();
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
