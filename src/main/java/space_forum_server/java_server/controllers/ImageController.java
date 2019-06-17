package space_forum_server.java_server.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space_forum_server.java_server.models.*;

@RestController
public class ImageController {
//  @CrossOrigin(origins = "*")
//  @GetMapping("/api/images/recommendCategory/{sessionid}")
//  public List<ForumThread> findAllThreads(@PathVariable("sessionid") String sessionid) {
//    ForumThread ft = new ForumThread();
//    UserController uc = new UserController();
//    List<Image> likedImgs = uc.authenticateUser(sessionid).getLikedImages();
//
//    for(Image img : likedImgs){
//      img.
//    }
//
//    List<ForumThread> allThreads = (List<ForumThread>)forumThreadRepository.findAll();
//    List<ForumThread> filteredThreads = new ArrayList<ForumThread>();
//    for(ForumThread ft : allThreads){
//      if(ft.getImage().getId() == imageid){
//        filteredThreads.add(ft);
//      }
//    }
//    return filteredThreads;
//  }
}
