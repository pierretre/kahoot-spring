package jpa.web;

import jpa.domain.UserAnswer;
import jpa.services.IUserAnswerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserAnswerController {

    @Autowired
    private IUserAnswerDAO userAnswerDAO;


    @GetMapping("/useranswer/{id}")
    public Optional<UserAnswer> userAnswerById(@PathVariable Long id) {
        return userAnswerDAO.findById(id);
    }

    @DeleteMapping("/useranswer/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userAnswerDAO.deleteById(id);
    }
}
