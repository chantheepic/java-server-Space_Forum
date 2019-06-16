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

  @CrossOrigin(origins = "*")
  @PostMapping("/api/threads/register/{sessionid}/{imageid}")
  public ForumThread registerThread(@PathVariable("sessionid") String sessionid, @PathVariable("imageid") int imageid) {
    Image img;

    if(imageRepository.existsById(imageid)){
      Optional<Image> opt = imageRepository.findById(imageid);
      img = opt.orElse(null);
    } else {
      Image newImg = new Image();
      newImg.setId(imageid);
      imageRepository.save(newImg);
      img = newImg;
    }

    ForumThread ft = new ForumThread();
    UserController uc = new UserController();
    ft.setAuthor(uc.authenticateUser(sessionid));
    ft.setCreateTime(new Timestamp(System.currentTimeMillis()));
    ft.setImage(img);

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
  @GetMapping("/api/threads/getall")
  public List<ForumThread> findAllThreads() {
    return (List<ForumThread>)forumThreadRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/threads/getbyimgid/{imageid}")
  public List<ForumThread> findAllThreads(@PathVariable("imageid") int imageid) {
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
