package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space_forum_server.java_server.models.ForumPost;
import space_forum_server.java_server.models.ForumThread;
import space_forum_server.java_server.models.User;
import space_forum_server.java_server.repositories.ForumPostRepository;
import space_forum_server.java_server.repositories.ForumThreadRepository;

@RestController
public class ForumPostController {

  @Autowired
  ForumPostRepository forumPostRepository;
  @Autowired
  ForumThreadRepository forumThreadRepository;

  @CrossOrigin(origins = "*")
  @PostMapping("/api/modules/{threadid}/posts/{sessionid}")
  public List<ForumPost> findAllPosts(@PathVariable("threadid") int threadid, @PathVariable("sessionid") String sessionid) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    return ft.getPosts();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/modules/{threadid}/posts/{sessionid}")
  public List<ForumPost> registerPost(@PathVariable("threadid") int threadid, @PathVariable("sessionid") String sessionid, @RequestBody ForumPost newPost) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    forumPostRepository.save(newPost);
    ft.getPosts().add(newPost);
    
    ft.setLastedUpdated(new Timestamp((System.currentTimeMillis())));
    return ft.getPosts();
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/api/posts/registerPost/{sessionid}/{threadid}")
  public List<ForumPost> registerPost(@RequestBody ForumPost newPost, @PathVariable("sessionid") String sessionid, @PathVariable("threadid") int threadid) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    ft.setLastedUpdated(new Timestamp((System.currentTimeMillis())));

    UserController uc = new UserController();
    User author = uc.authenticateUser(sessionid);

    newPost.setAuthor(author);
    newPost.setCreateTime(new Timestamp(System.currentTimeMillis()));
    newPost.setUpvotes(0);
    forumPostRepository.save(newPost);
    ft.getPosts().add(newPost);
    forumThreadRepository.save(ft);

    return ft.getPosts();
  }

}
