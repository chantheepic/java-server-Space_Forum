package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space_forum_server.java_server.models.*;
import space_forum_server.java_server.repositories.*;

@RestController
public class ForumThreadController {

  @Autowired
  ForumThreadRepository forumThreadRepository;
  @Autowired
  ImageRepository imageRepository;
  @Autowired
  private UserController userController;

  @CrossOrigin(origins = "*")
  @GetMapping("/api/threads")
  public List<ForumThread> findAllThreads() {
    return (List<ForumThread>) forumThreadRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/threads/{threadid}")
  public ForumThread findThreadById(@PathVariable("threadid") int threadid) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    return opt.orElse(null);
  }

  //api/threads/register/{sessionid}"
  @CrossOrigin(origins = "*")
  @PostMapping("/api/threads/{sessionid}")
  public ForumThread registerThread(@PathVariable("sessionid") String sessionid,
      @RequestBody ThreadWrapper givenThread) {
    ForumThread ft = new ForumThread();
    ft.setTitle(givenThread.getTitle());
    ft.setType(givenThread.getType());
    ft.setAuthor(userController.authenticateUser(sessionid));

    if (givenThread.getType().equals("IMAGE")) {
      Image img;
      if (imageRepository.existsById(givenThread.getImageId())) {
        Optional<Image> opt = imageRepository.findById(givenThread.getImageId());
        img = opt.orElse(null);
      } else {
        Image newImg = new Image();
        newImg.setId(givenThread.getImageId());
        newImg.setUrl(givenThread.getImageUrl());
        newImg.setCategory(givenThread.getCategory());
        imageRepository.save(newImg);
        img = newImg;
      }
      ft.setImage(img);
    } else {
      ft.setText(givenThread.getContent());
    }

    forumThreadRepository.save(ft);
    return ft;
  }

  //api/threads/checkowner/{sessionid}/{threadid}
  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{sessionid}/threads/{threadid}")
  public boolean checkThreadOwner(@PathVariable("sessionid") String sessionid, @PathVariable("threadid") int threadid) {
    User user = userController.authenticateUser(sessionid);
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    if (user.equals(ft.getAuthor())) {
      return true;
    }
    return false;
  }

  //api/threads/vote/{sessionid}/{threadid}
  @CrossOrigin(origins = "*")
  @PostMapping("/api/users/{sessionid}/threads/{threadid}")
  public ForumThread submitVote(@PathVariable("sessionid") String sessionid, @PathVariable("threadid") int threadid,
      @RequestBody String vote) {
    User user = userController.authenticateUser(sessionid);
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    if (vote.equals("UPVOTE")) {
      if (ft.getUpvotedBy().contains(user)) {
        ft.getUpvotedBy().remove(user);
      } else {
        ft.getUpvotedBy().add(user);
        if (ft.getDownvotedBy().contains(user)) {
          ft.getDownvotedBy().remove(user);
        }
      }
    } else {
      if (ft.getDownvotedBy().contains(user)) {
        ft.getDownvotedBy().remove(user);
      } else {
        ft.getDownvotedBy().add(user);
        if (ft.getUpvotedBy().contains(user)) {
          ft.getUpvotedBy().remove(user);
        }
      }
    }
    try {
      forumThreadRepository.save(ft);
      return ft;
    } catch (Exception e) {
      opt = forumThreadRepository.findById(threadid);
      ft = opt.orElse(null);
      return ft;
    }
  }

  //api/threads/update/{threadid}
  @CrossOrigin(origins = "*")
  @PutMapping("/api/threads/{threadid}")
  public ForumThread updateThread(@PathVariable("threadid") int threadid, @RequestBody ForumThread thread) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    ft.setTitle(thread.getTitle());
    ft.setText(thread.getText());
    forumThreadRepository.save(ft);
    return ft;
  }

  //api/threads/delete/{threadid}
  @CrossOrigin(origins = "*")
  @DeleteMapping("/api/threads/{threadid}")
  public String deleteThread(@PathVariable("sessionid") int threadid) {
    forumThreadRepository.deleteById(threadid);
    return "suceess";
  }

  //api/threads/getbyimgid/{imageid}"
  @CrossOrigin(origins = "*")
  @GetMapping("/api/images/{imageid}/threads")
  public List<ForumThread> findAllThreadsByImgId(@PathVariable("imageid") int imageid) {
    List<ForumThread> allThreads = (List<ForumThread>) forumThreadRepository.findAll();
    List<ForumThread> filteredThreads = new ArrayList<ForumThread>();
    for (ForumThread ft : allThreads) {
      if (ft.getImage() != null && ft.getImage().getId() == imageid) {
        filteredThreads.add(ft);
      }
    }
    return filteredThreads;
  }

}
