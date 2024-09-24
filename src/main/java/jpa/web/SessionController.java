package jpa.web;

import jpa.domain.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SessionController {

    @GetMapping("/sessions")
    @ResponseBody
    public List<Session> sessions() {

    }
}
