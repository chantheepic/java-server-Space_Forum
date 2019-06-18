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
    return (List<ForumThread>)forumThreadRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/api/threads/register/{sessionid}")
  public ForumThread registerThread(@PathVariable("sessionid") String sessionid, @RequestBody PostWrapper givenThread) {
    ForumThread ft = new ForumThread();
    ft.setTitle(givenThread.getTitle());
    ft.setType(givenThread.getType());
    ft.setAuthor(userController.authenticateUser(sessionid));

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    ft.setCreateTime(ts);
    ft.setCreateTime(ts);
    if(givenThread.getType() == "IMAGE"){
      Image img;
      if(imageRepository.existsById(givenThread.getImageId())){
        Optional<Image> opt = imageRepository.findById(givenThread.getImageId());
        img = opt.orElse(null);
      } else {
        Image newImg = new Image();
        newImg.setId(givenThread.getImageId());
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

  @CrossOrigin(origins = "*")
  @PutMapping("/api/threads/update/{sessionid}")
  public ForumThread updateThread(@PathVariable("sessionid") String sessionid, @RequestBody ForumThread thread) {
    Optional<ForumThread> opt = forumThreadRepository.findById(thread.getId());
    ForumThread ft = opt.orElse(null);
    ft.setTitle(thread.getTitle());
    ft.setText(thread.getText());
    ft.setLastedUpdated(new Timestamp((System.currentTimeMillis())));
    forumThreadRepository.save(ft);
    return ft;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/threads/checkowner/{sessionid}/{threadid}")
  public boolean checkThreadOwner(@PathVariable("sessionid") String sessionid, @PathVariable("threadid") int threadid) {
    UserController uc = new UserController();
    User user = uc.authenticateUser(sessionid);
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    if(user.equals(ft.getAuthor())){
      return true;
    }
    return false;
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/api/threads/delete/{threadid}")
  public String deleteThread(@PathVariable("sessionid") int threadid) {
    forumThreadRepository.deleteById(threadid);
    return "suceess";
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/threads/getbyimgid/{imageid}")
  public List<ForumThread> findAllThreadsByImgId(@PathVariable("imageid") int imageid) {
    List<ForumThread> allThreads = (List<ForumThread>)forumThreadRepository.findAll();
    List<ForumThread> filteredThreads = new ArrayList<ForumThread>();
    for(ForumThread ft : allThreads){
      if(ft.getImage().getId() == imageid){
        filteredThreads.add(ft);
      }
    }
    return filteredThreads;
  }

}
