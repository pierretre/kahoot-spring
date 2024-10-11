package com.web;

import com.dto.SessionDTO;
import com.dto.UserAnswerDTO;
import com.dto.get.UserGetDTO;
import com.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/sessions")
@Tag(name = "Sessions", description = "API to manage sessions and user answers")
public class SessionController {

  @Autowired
  SessionService sessionService;

    /**
     * Get all sessions.
     *
     * @return A list of SessionDTO representing all sessions.
     */
    @Operation(summary = "Get all sessions", description = "Retrieve a list of all sessions available in the database.")
    @GetMapping("")
    public List<SessionDTO> all() {
        return sessionService.getAll();
    }

    /**
     * Get a specific session by its ID.
     *
     * @param id The ID of the session to retrieve.
     * @return The SessionDTO of the requested session.
     */
    @Operation(summary = "Get a session by ID", description = "Retrieve a specific session by providing its ID.")
    @GetMapping("/{id}")
    public SessionDTO one(@PathVariable long id) {
        return sessionService.get(id);
    }

    /**
     * Delete a session by its ID. This action requires admin privileges.
     *
     * @param id The ID of the session to delete.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a session", description = "Delete a session by providing its ID. Admin privileges are required for this action.")
    @DeleteMapping("/{id}")
    public void deleteSessionById(@PathVariable long id) {
        sessionService.delete(id);
    }

    /**
     * Get all user answers associated with a specific session.
     *
     * @param id The ID of the session for which to retrieve user answers.
     * @return A list of UserAnswerDTO representing the user answers for the session.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get user answers for a session", description = "Retrieve all user answers for a specific session. Admin privileges are required.")
    @GetMapping("/{id}/useranswers")
    public List<UserAnswerDTO> getUserAnswersForSession(@PathVariable long id) {
        return sessionService.getUserAnswers(id);
    }

    /**
     * Get all users ordered by score in descending order for a specific session.
     *
     * @param id The ID of the session for which to retrieve users sorted by score.
     * @return A list of UserAnswerDTO representing the users sorted by their score.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get users ordered by score for a session", description = "Retrieve all users associated with a specific session, ordered by their score in descending order. Admin privileges are required.")
    @GetMapping("/{id}/scores")
    public List<UserGetDTO> getUsersOrderedByScoreForSession(@PathVariable long id) {
        return sessionService.getUsersScores(id);
    }

    /**
     * Generate a dummy session for testing purposes.
     *
     * @return SessionDTO - A dummy session object.
     *
     * This endpoint is used to generate and return a dummy session.
     */
    @PostMapping("/dummy")
    public SessionDTO createDummy() {
        return sessionService.generateSession();
    }
}