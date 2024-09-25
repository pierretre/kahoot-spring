package jpa.web;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/index")
   // @PreAuthorize("hasRole('USER')")
    public ModelAndView index( ) {

        ModelAndView modelAndView = new ModelAndView("index");
        /*authentication.getToken().getClaims().forEach((e,v)-> {
            System.err.println(e + ' ' +v);
        });
        modelAndView.addObject("user", authentication);*/
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("indexmain");
        return modelAndView;
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView admin(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("user", principal);
        return modelAndView;
    }


}