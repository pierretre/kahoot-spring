package jpa.web;

import jpa.domain.Question;
import jpa.domain.UserAnswer;
import jpa.services.IQuestionDAO;
import jpa.services.IUserAnswerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionDAO questionDAO;

    @GetMapping("/question/{id}")
    public Optional<Question> questionById(@PathVariable Long id) {
        return questionDAO.findById(id);
    }

    @DeleteMapping("/question/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        questionDAO.deleteById(id);
    }
}
