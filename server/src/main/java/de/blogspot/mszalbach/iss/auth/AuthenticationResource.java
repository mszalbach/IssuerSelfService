package de.blogspot.mszalbach.iss.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
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
    public User login(@RequestBody Credentials credentials, HttpSession httpSession) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
        User user = new User(credentials.getUsername(), httpSession.getId(), true);
        httpSession.setAttribute("user", user);
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) throws ServletException {
        session.invalidate();
    }

    @RequestMapping(method = RequestMethod.GET)
    public User details(HttpSession session) throws ServletException {
        return (User) session.getAttribute("user");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AuthenticationException> userNotAuthenticated(AuthenticationException e) {
        return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
    }
}
