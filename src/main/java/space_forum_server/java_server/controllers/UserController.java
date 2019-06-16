package space_forum_server.java_server.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space_forum_server.java_server.models.*;
import space_forum_server.java_server.repositories.*;

@RestController
public class UserController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  UserSessionRepository userSessionRepository;

  @CrossOrigin(origins = "*")
  @PostMapping("/api/users/register")
  public UserSession registerUser(@RequestBody User newUser) throws NoSuchAlgorithmException {
    userRepository.save(newUser);
    UserSession newUserSession = new UserSession();
    newUserSession.setUser(newUser);
    userSessionRepository.save(newUserSession);
    return newUserSession;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/login")
  public UserSession loginUser(@RequestBody User returning) throws NoSuchAlgorithmException {
    try {
      User user = userRepository.authenticate(returning.getUsername(), returning.getPassword());
      if (user != null) {
        UserSession newUserSession = new UserSession();
        newUserSession.setUser(user);
        userSessionRepository.save(newUserSession);
        return newUserSession;
      } else {
        System.out.println(returning.getUsername() + " " + returning.getAlias() + " " + returning.getPassword());
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/api/users/update")
  public UserSession login(@RequestBody User returning) {
    try {
      User user = userRepository.authenticate(returning.getUsername(), returning.getPassword());
      UserSession newUserSession = new UserSession();
      newUserSession.setUser(user);
      return newUserSession;
    } catch (Exception e) {
      return null;
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{id}")
  public User AuthenticateUser(@PathVariable("id") String sessionid) {
    try {
      UserSession session = userSessionRepository.matchSession(sessionid);
//      if(userSessionRepository.latestSession(session.getUser().getId()).equals(session)){
//        System.out.println("");
//      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(session.getLoginTime());
      cal.add(Calendar.DAY_OF_WEEK, 1);

      if (cal.getTime().compareTo(new Timestamp(System.currentTimeMillis())) > 0) {
        return session.getUser();
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

}
