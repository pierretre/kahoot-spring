package com.web;

import com.domain.*;
import com.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class KahootController {

    @Autowired
    private IKahootDAO kahootDAO;
    @Autowired
    private IQuestionDAO questionDAO;
    @Autowired
    private IQCMAnswerDAO qcmAnswerDAO;
    @Autowired
    private IOrganizerDAO organizerDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ISessionDAO sessionDAO;
    @Autowired
    private IUserAnswerDAO userAnswerDAO;

    @GetMapping("/kahoot")
    @ResponseBody
    public List<Kahoot> kahoot() {
        return kahootDAO.findAll();
    }

    @PostMapping("/randomkahoot")
    public String randomKahoot() {
        return this.createKahoots();
    }

    private String createKahoots() {

        Kahoot kahoot = new Kahoot();
        kahootDAO.save(kahoot);

        // Generate the questions :
        Question q1 = new ShortAnswerQuestion("How many legs does a cat have ?", "4", kahoot);
        questionDAO.save(q1);

        MultipleChoiceQuestion q2 = new MultipleChoiceQuestion();
        questionDAO.save(q2);

        q2.setQuestion("What is the largest country in the world ?");
        MultipleChoiceQuestionAnswer goodAnswer = new MultipleChoiceQuestionAnswer(q2, "Russia");
        qcmAnswerDAO.save(goodAnswer);
        MultipleChoiceQuestionAnswer qa1 = new MultipleChoiceQuestionAnswer(q2, "USA");
        qcmAnswerDAO.save(qa1);
        MultipleChoiceQuestionAnswer qa2 = new MultipleChoiceQuestionAnswer(q2, "Canada");
        qcmAnswerDAO.save(qa2);
        MultipleChoiceQuestionAnswer qa3 = new MultipleChoiceQuestionAnswer(q2, "Brazil");
        qcmAnswerDAO.save(qa3);
        q2.setPossibleAnswers(new ArrayList<>(Arrays.asList(qa1, qa2, qa3, goodAnswer))); // List must be mutable !!
        q2.setGoodAnswer(goodAnswer);
        q2.setKahoot(kahoot);
        questionDAO.save(q2);

        kahoot.setQuestions(new ArrayList<>(Arrays.asList(q1, q2)));

        // Generate the organizer :
        Organizer creator = new Organizer("bob@mail.com", "bob");
        organizerDAO.save(creator);
        kahoot.setCreator(creator);

        kahoot.setCreationDate(LocalDateTime.now());

        kahootDAO.save(kahoot);

        // Generate the session :
        Session session = new Session(kahoot, new ArrayList<>());
        sessionDAO.save(session);

        // Generate the users :
        User u1 = new User("u1", session);
        User u2 = new User("u2", session);
        User u3 = new User("u3", session);
        User u4 = new User("u4", session);
        userDAO.save(u1);
        userDAO.save(u2);
        userDAO.save(u3);
        userDAO.save(u4);

        // Update the session :
        session.setGuests(new ArrayList<>(Arrays.asList(u1, u2, u3, u4)));
        sessionDAO.save(session);

        // Generate answers :
        ShortUserAnswer sua1 = new ShortUserAnswer("2", false, u1);
        ShortUserAnswer sua2 = new ShortUserAnswer("4", true, u2);
        ShortUserAnswer sua3 = new ShortUserAnswer("3", false, u3);
        ShortUserAnswer sua4 = new ShortUserAnswer("4", true, u4);

        userAnswerDAO.save(sua1);
        userAnswerDAO.save(sua2);
        userAnswerDAO.save(sua3);
        userAnswerDAO.save(sua4);

        MultipleChoiceUserAnswer qcma1 = new MultipleChoiceUserAnswer(qa1, false, u1);
        MultipleChoiceUserAnswer qcma2 = new MultipleChoiceUserAnswer(goodAnswer, true, u2);
        MultipleChoiceUserAnswer qcma3 = new MultipleChoiceUserAnswer(qa1, false, u3);
        MultipleChoiceUserAnswer qcma4 = new MultipleChoiceUserAnswer(qa3, false, u4);

        userAnswerDAO.save(qcma1);
        userAnswerDAO.save(qcma2);
        userAnswerDAO.save(qcma3);
        userAnswerDAO.save(qcma4);

        List<Session> sessions = sessionDAO.findAll();
        StringBuilder response = new StringBuilder();
        response.append("SESSIONS : " + sessions.size()).append("SCORES :");

        List<User> guests = sessionDAO.listUsersScoresForSessionById(session.getId());

        for (User guest : guests) {
            response.append(" > " + guest.getUsername() + " : " + guest.getScore());
        }

        return response.toString();
    }
}
