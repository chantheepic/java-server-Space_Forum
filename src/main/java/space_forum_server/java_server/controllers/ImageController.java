package space_forum_server.java_server.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space_forum_server.java_server.models.*;
import space_forum_server.java_server.repositories.ImageRepository;

@RestController
public class ImageController {

  @Autowired
  ImageRepository imageRepository;
  @Autowired
  UserController userController;

  @CrossOrigin(origins = "*")
  @GetMapping("/api/images/recommendCategory/{sessionid}")
  public String recommend(@PathVariable("sessionid") String sessionid) {
    List<Image> likedImgs = userController.authenticateUser(sessionid).getLikedImages();

    HashMap<String, Integer> likedByUser = new HashMap<>();

    for (Image img : likedImgs) {
      String key = img.getCategory();
      if (likedByUser.containsKey(key)) {
        likedByUser.replace(key, likedByUser.get(key) + 1);
      } else {
        likedByUser.put(key, 1);
      }
    }

    return Collections.max(likedByUser.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/api/images")
  public List<Image> addImage(@RequestBody Image newImage) {
    if (!imageRepository.existsById(newImage.getId())) {
      imageRepository.save(newImage);
    }
    return (List<Image>) imageRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/api/user/{sessionid}/images/{imageid}")
  public List<Image> likeImage(@PathVariable("sessionid") String sessionid, @PathVariable("imageid") int imageid) {
    User user = userController.authenticateUser(sessionid);
    Optional<Image> opt = imageRepository.findById(imageid);
    Image img = opt.orElse(null);
    if (img.getLikedBy().contains(user)) {
      img.getLikedBy().remove(user);
      user.getLikedImages().remove(img);
    } else {
      img.getLikedBy().add(user);
      user.getLikedImages().add(img);
    }
    imageRepository.save(img);
    return user.getLikedImages();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/images")
  public List<Image> findAllImages() {
    return (List<Image>) imageRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/images/{imageid}")
  public List<Image> updateImage(@PathVariable("imageid") int imageid, @RequestBody Image newImage) {
    Optional<Image> opt = imageRepository.findById(imageid);
    Image img = opt.orElse(null);
    img.setCategory(newImage.getCategory());
    img.setUrl(newImage.getUrl());
    img.setForumThreads(newImage.getForumThreads());
    imageRepository.save(img);
    return (List<Image>) imageRepository.findAll();
  }

  @DeleteMapping("/api/images/{imageid}")
  public List<Image> deleteImage(@PathVariable("imageid") int imageid) {
    imageRepository.deleteById(imageid);
    return (List<Image>) imageRepository.findAll();
  }

}
