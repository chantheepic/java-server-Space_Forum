package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  //api/users/{userid}
  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{userid}")
  public List<User> findUserById(@PathVariable("userid") int userid) {
    userRepository.deleteById(userid);
    return (List<User>) userRepository.findAll();
  }

  //api/users/register
  @CrossOrigin(origins = "*")
  @PostMapping("/api/users")
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

  //api/users/login
  @CrossOrigin(origins = "*")
  @PostMapping("/api/userlogin")
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

  //api/users/update/{sessionid}
  @CrossOrigin(origins = "*")
  @PutMapping("/api/users/{sessionid}")
  public String updateUser(@PathVariable("sessionid") String sessionid, @RequestBody User updatedProfile) {
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

  //api/users/promote/{direction}
  @CrossOrigin(origins = "*")
  @PutMapping("/api/promoteuser/{direction}")
  public String promoteUser(@PathVariable("direction") String direction, @RequestBody User u) {
    try {
      Optional<User> opt = userRepository.findById(u.getId());
      User user = opt.orElse(null);
      if (direction.equals("PROMOTE")) {
        user.setAdmin(true);
      } else {
        user.setAdmin(false);
      }
      userRepository.save(user);
      return "Promoted to admin";
    } catch (Exception e) {
      return null;
    }
  }

  //api/users/ban/{direction}"
  @CrossOrigin(origins = "*")
  @PutMapping("/api/banuser/{direction}")
  public String banUser(@PathVariable("direction") String direction, @RequestBody User u) {
    try {
      Optional<User> opt = userRepository.findById(u.getId());
      User user = opt.orElse(null);
      if (direction.equals("BAN")) {
        user.setBanned(true);
      } else {
        user.setBanned(false);
      }
      userRepository.save(user);
      return "User Banned";
    } catch (Exception e) {
      return null;
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/api/user/{sessionid}/follow/{followeeid}/direction/{direction}")
  public User followUser(@PathVariable("sessionid") String sessionid, @PathVariable("followeeid") int followeeid, @PathVariable("direction") String direction) {
    try {
      User follower = authenticateUser(sessionid);
      Optional<User> opt = userRepository.findById(followeeid);
      User followee = opt.orElse(null);

      if (direction.equals("FOLLOW")) {
        follower.getFollowing().add(followee);
      } else {
        follower.getFollowing().remove(followee);
      }
      userRepository.save(follower);
      return follower;
    } catch (Exception e) {
      return null;
    }
  }

  //api/users/{id}
  @CrossOrigin(origins = "*")
  @GetMapping("/api/authenticateuser/{id}")
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