package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public UserSession registerUser(@RequestBody User newUser) {
    try {
      userRepository.save(newUser);
      UserSession newUserSession = new UserSession();
      newUserSession.setUser(newUser);
      userSessionRepository.save(newUserSession);
      return newUserSession;
    } catch (Exception e) {
      return null;
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/login")
  public UserSession loginUser(@RequestBody User returning) {
    try {
      User user = userRepository.authenticate(returning.getUsername(), returning.getPassword());
      if (user != null) {
        UserSession newUserSession = new UserSession();
        newUserSession.setUser(user);
        userSessionRepository.save(newUserSession);
        return newUserSession;
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/api/users/update/{id}")
  public String updateUser(@PathVariable("id") String sessionid, @RequestBody User updatedProfile) {
    try {
      User user = authenticateUser(sessionid);
      user.setUsername(updatedProfile.getUsername());
      user.setAlias(updatedProfile.getAlias());
      user.setPassword(updatedProfile.getPassword());
      userRepository.save(user);
      return "Profile Updated";
    } catch (Exception e) {
      return null;
    }
  }


  // Not for external use. Endpoint is just for testing. Don't use on front end.
  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{id}")
  public User authenticateUser(@PathVariable("id") String sessionid) {
    try {
      UserSession session = userSessionRepository.matchSession(sessionid);
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
