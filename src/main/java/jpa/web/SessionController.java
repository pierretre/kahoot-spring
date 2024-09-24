package jpa.web;

import jpa.domain.Session;
import jpa.services.ISessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SessionController {
    @Autowired
    private ISessionDAO sessionDAO;

    @GetMapping("/sessions")
    @ResponseBody
    public List<Session> sessions() {
        return sessionDAO.findAll();
    }
}
