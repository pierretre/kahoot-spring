package com.services;

import com.domain.*;
import com.dto.SessionDTO;
import com.dto.UserAnswerDTO;
import com.dto.get.UserGetDTO;
import com.exceptions.ResourceNotFoundException;
import com.mapper.MapStructMapper;
import com.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
    private IKahootRepository kahootRepository;

    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOrganizerRepository organizerRepository;

    @Autowired
    private IQCMAnswerRepository qcmAnswerRepository;

    @Autowired
    private IUserAnswerRepository userAnswerRepository;


    MapStructMapper mapstructMapper = MapStructMapper.INSTANCE;

    public List<SessionDTO> getAll() {
        return sessionRepository.findAll().stream().map(s -> mapstructMapper.sessionToSessionDTO(s)).collect(Collectors.toList());
    }

    public SessionDTO get(long id) {
        return mapstructMapper.sessionToSessionDTO(sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Session.class, id)));
    }

    public void delete(long id) {
        sessionRepository.deleteById(id);
    }

    public List<UserAnswerDTO> getUserAnswers(long id) {
        return sessionRepository.getUserAnswersForSessionById(id).stream()
                .map(ua -> mapstructMapper.userAnswerToUserAnswerDTO(ua)).collect(Collectors.toList());
    }

    public List<UserGetDTO> getUsersScores(long id) {
        return sessionRepository.getUsersOrderedByScoreForSessionById(id).stream()
                .map(u -> mapstructMapper.userToUserDTO(u)).collect(Collectors.toList());
    }

    public SessionDTO generateSession() {

        Kahoot kahoot = new Kahoot();
        kahootRepository.save(kahoot);

        // Generate the questions :
        Question q1 = new ShortAnswerQuestion("How many legs does a cat have ?", "4", kahoot);
        questionRepository.save(q1);

        MultipleChoiceQuestion q2 = new MultipleChoiceQuestion();
        questionRepository.save(q2);

        q2.setQuestion("What is the largest country in the world ?");
        MultipleChoiceQuestionAnswer goodAnswer = new MultipleChoiceQuestionAnswer(q2, "Russia");
        qcmAnswerRepository.save(goodAnswer);
        MultipleChoiceQuestionAnswer qa1 = new MultipleChoiceQuestionAnswer(q2, "USA");
        qcmAnswerRepository.save(qa1);
        MultipleChoiceQuestionAnswer qa2 = new MultipleChoiceQuestionAnswer(q2, "Canada");
        qcmAnswerRepository.save(qa2);
        MultipleChoiceQuestionAnswer qa3 = new MultipleChoiceQuestionAnswer(q2, "Brazil");
        qcmAnswerRepository.save(qa3);
        q2.setPossibleAnswers(new ArrayList<>(Arrays.asList(qa1, qa2, qa3, goodAnswer))); // List must be mutable !!
        q2.setGoodAnswer(goodAnswer);
        q2.setKahoot(kahoot);
        questionRepository.save(q2);

        kahoot.setQuestions(new ArrayList<>(Arrays.asList(q1, q2)));

        // Generate the organizer :
        Organizer creator = new Organizer("bob@mail.com", "bob");
        organizerRepository.save(creator);
        kahoot.setCreator(creator);

        kahoot.setCreationDate(LocalDateTime.now());

        kahootRepository.save(kahoot);

        // Generate the session :
        Session session = new Session(kahoot, new ArrayList<>());
        sessionRepository.save(session);

        // Generate the users :
        User u1 = new User("u1", session);
        User u2 = new User("u2", session);
        User u3 = new User("u3", session);
        User u4 = new User("u4", session);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);

        // Update the session :
        session.setGuests(new ArrayList<>(Arrays.asList(u1, u2, u3, u4)));
        sessionRepository.save(session);

        // Generate answers :
        ShortUserAnswer sua1 = new ShortUserAnswer("2", false, u1);
        ShortUserAnswer sua2 = new ShortUserAnswer("4", true, u2);
        ShortUserAnswer sua3 = new ShortUserAnswer("3", false, u3);
        ShortUserAnswer sua4 = new ShortUserAnswer("4", true, u4);

        userAnswerRepository.save(sua1);
        userAnswerRepository.save(sua2);
        userAnswerRepository.save(sua3);
        userAnswerRepository.save(sua4);

        MultipleChoiceUserAnswer qcma1 = new MultipleChoiceUserAnswer(qa1, false, u1);
        MultipleChoiceUserAnswer qcma2 = new MultipleChoiceUserAnswer(goodAnswer, true, u2);
        MultipleChoiceUserAnswer qcma3 = new MultipleChoiceUserAnswer(qa1, false, u3);
        MultipleChoiceUserAnswer qcma4 = new MultipleChoiceUserAnswer(qa3, false, u4);

        userAnswerRepository.save(qcma1);
        userAnswerRepository.save(qcma2);
        userAnswerRepository.save(qcma3);
        userAnswerRepository.save(qcma4);

        return mapstructMapper.sessionToSessionDTO(session);
    }
}
