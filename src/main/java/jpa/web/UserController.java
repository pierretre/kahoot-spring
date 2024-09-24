package jpa.web;

import jpa.domain.User;
import jpa.services.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private IUserDAO userDAO;

    @GetMapping("/users")
    public Iterable<User> users() {
        return userDAO.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getEmployee(@PathVariable("id") final Long id) {
        return userDAO.findById(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userDAO.deleteById(id);
    }
}
