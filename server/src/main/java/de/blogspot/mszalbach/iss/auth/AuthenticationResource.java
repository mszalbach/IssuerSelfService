package de.blogspot.mszalbach.iss.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by foobarkilla on 07.11.16.
 */
@RestController()
@RequestMapping("/api/session")
public class AuthenticationResource {


    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpSession httpSession) {
        Authentication authentication = new UsernamePasswordAuthenticationToken("Ralf", "ralf");
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
        return httpSession.getId();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object details() throws ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;


    }
}
