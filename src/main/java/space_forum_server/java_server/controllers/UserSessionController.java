package space_forum_server.java_server.controllers;

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
import space_forum_server.java_server.models.ForumPost;
import space_forum_server.java_server.models.ForumThread;
import space_forum_server.java_server.models.Image;
import space_forum_server.java_server.models.UserSession;
import space_forum_server.java_server.repositories.UserSessionRepository;

@RestController
public class UserSessionController {

  @Autowired
  UserSessionRepository userSessionRepository;

  @CrossOrigin(origins = "*")
  @GetMapping("/api/usersession")
  public List<UserSession> findAllImages() {
    return (List<UserSession>)userSessionRepository.findAll();
  }

  // Uses a wrapper to take in nedded threadid (required) and postid (optional)
  @CrossOrigin(origins = "*")
  @PostMapping("/api/usersession")
  public void registerPost(@RequestBody UserSession newsession) {
    userSessionRepository.save(newsession);
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/api/usersession/{usersessionid}")
  public void updatePost(@PathVariable("usersessionid") int usersessionid, @RequestBody UserSession session) {
    Optional<UserSession> opt = userSessionRepository.findById(usersessionid);
    UserSession s = opt.orElse(null);
    s.setToken(session.getToken());
    s.setLoginTime(session.getLoginTime());
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/api/usersession/{usersessionid}")
  public void deletePost(@PathVariable("usersessionid") int usersessionid) {
    userSessionRepository.deleteById(usersessionid);
  }
}
