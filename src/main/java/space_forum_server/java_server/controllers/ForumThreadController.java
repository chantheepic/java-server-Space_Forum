package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
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
  @PostMapping("/api/thread/register/{sessionid}/{imageid}")
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
  @PutMapping("/api/thread/update/{sessionid}")
  public ForumThread updateThread(@PathVariable("sessionid") String sessionid, @RequestBody ForumThread thread) {
    Optional<ForumThread> opt = forumThreadRepository.findById(thread.getId());
    ForumThread ft = opt.orElse(null);
    ft.setTitle(thread.getTitle());
    ft.setText(thread.getText());
    ft.setLastedUpdated(new Timestamp((System.currentTimeMillis())));
    forumThreadRepository.save(ft);
    return ft;
  }
}
