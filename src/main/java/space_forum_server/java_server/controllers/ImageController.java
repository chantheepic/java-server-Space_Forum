package space_forum_server.java_server.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space_forum_server.java_server.models.*;

@RestController
public class ImageController {
  @CrossOrigin(origins = "*")
  @GetMapping("/api/images/recommendCategory/{sessionid}")
  public String findAllThreads(@PathVariable("sessionid") String sessionid) {
    UserController uc = new UserController();
    List<Image> likedImgs = uc.authenticateUser(sessionid).getLikedImages();

    HashMap<String, Integer> likedByUser = new HashMap<>();

    for(Image img : likedImgs){
      String key = img.getCategory();
      if(likedByUser.containsKey(key)){
        likedByUser.replace(key, likedByUser.get(key) + 1);
      } else {
        likedByUser.put(key, 1);
      }
    }

    return Collections.max(likedByUser.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
  }
}
