package space_forum_server.java_server.controllers;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space_forum_server.java_server.models.User;
import space_forum_server.java_server.models.UserSession;
import space_forum_server.java_server.repositories.ForumThreadRepository;

@RestController
public class ForumThreadController {
  @Autowired
  ForumThreadRepository forumThreadRepository;

//  @CrossOrigin(origins = "*")
//  @PostMapping("/api/thread/register/{sessionid}/{imageid}")
//  public UserSession registerUser(@RequestBody User newUser) throws NoSuchAlgorithmException {
//    userRepository.save(newUser);
//    UserSession newUserSession = new UserSession();
//    newUserSession.setUser(newUser);
//    userSessionRepository.save(newUserSession);
//    return newUserSession;
//  }
}
