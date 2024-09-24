package jpa.web;

import jpa.domain.*;
import jpa.services.*;
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
        LocalDateTime now = LocalDateTime.now();

        // Generate the questions :
        Question q1 = new ShortAnswerQuestion("How many legs does a cat have ?", "4");
        questionDAO.save(q1);

        QCMQuestion q2 = new QCMQuestion();
        questionDAO.save(q2);

        q2.setQuestion("What is the largest country in the world ?");
        QCMAnswer goodAnswer = new QCMAnswer(q2, "Russia");
        qcmAnswerDAO.save(goodAnswer);
        QCMAnswer qa1 = new QCMAnswer(q2, "USA");
        qcmAnswerDAO.save(qa1);
        QCMAnswer qa2 = new QCMAnswer(q2, "Canada");
        qcmAnswerDAO.save(qa2);
        QCMAnswer qa3 = new QCMAnswer(q2, "Brazil");
        qcmAnswerDAO.save(qa3);
        q2.setPossibleAnswers(new ArrayList<>(Arrays.asList(qa1, qa2, qa3, goodAnswer))); // List must be mutable !!
        q2.setGoodAnswer(goodAnswer);
        questionDAO.save(q2);

        // Generate the kahoot :
        Organizer creator = new Organizer("bob@mail.com", "bob");
        organizerDAO.save(creator);

        Kahoot kahoot = new Kahoot(creator, new ArrayList<>(Arrays.asList(q1, q2)));
        kahootDAO.save(kahoot);

        // Generate the session :
        Session session = new Session(now, kahoot, new ArrayList<>());
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
        ShortUserAnswer sua1 = new ShortUserAnswer("2", now, false, u1);
        ShortUserAnswer sua2 = new ShortUserAnswer("4", now, true, u2);
        ShortUserAnswer sua3 = new ShortUserAnswer("3", now, false, u3);
        ShortUserAnswer sua4 = new ShortUserAnswer("4", now, true, u4);

        userAnswerDAO.save(sua1);
        userAnswerDAO.save(sua2);
        userAnswerDAO.save(sua3);
        userAnswerDAO.save(sua4);

        QCMUserAnswer qcma1 = new QCMUserAnswer(qa1, now, false, u1);
        QCMUserAnswer qcma2 = new QCMUserAnswer(goodAnswer, now, true, u2);
        QCMUserAnswer qcma3 = new QCMUserAnswer(qa1, now, false, u3);
        QCMUserAnswer qcma4 = new QCMUserAnswer(qa3, now, false, u4);

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
