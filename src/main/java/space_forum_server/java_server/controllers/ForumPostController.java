package space_forum_server.java_server.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space_forum_server.java_server.models.ForumPost;
import space_forum_server.java_server.models.ForumThread;
import space_forum_server.java_server.models.PostWrapper;
import space_forum_server.java_server.models.User;
import space_forum_server.java_server.repositories.ForumPostRepository;
import space_forum_server.java_server.repositories.ForumThreadRepository;

@RestController
public class ForumPostController {

  @Autowired
  ForumPostRepository forumPostRepository;
  @Autowired
  ForumThreadRepository forumThreadRepository;
  @Autowired
  private UserController userController;

  @CrossOrigin(origins = "*")
  @PostMapping("/api/users/{sessionid}/threads/{threadid}/posts")
  public List<ForumPost> findAllPosts(@PathVariable("threadid") int threadid, @PathVariable("sessionid") String sessionid) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    return ft.getPosts();
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{sessionid}/posts/{replyid}")
  public List<ForumPost> registerPost(@PathVariable("replyid") int replyid, @PathVariable("sessionid") String sessionid, @RequestBody PostWrapper postWrapper) {
    User author = userController.authenticateUser(sessionid);
    ForumPost newPost = new ForumPost();
    newPost.setContent(postWrapper.getContent());
    newPost.setAuthor(author);
    newPost.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    forumPostRepository.save(newPost);

    if(postWrapper.getType().equals("THREADREPLY")){
      Optional<ForumThread> opt = forumThreadRepository.findById(replyid);
      ForumThread ft = opt.orElse(null);
      ft.getPosts().add(newPost);
      forumThreadRepository.save(ft);
      return ft.getPosts();
    } else {
      Optional<ForumPost> opt = forumPostRepository.findById(replyid);
      ForumPost fp = opt.orElse(null);
      fp.getReplies().add(newPost);
      forumPostRepository.save(fp);
      return fp.getReplies();
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{sessionid}/posts/checkOwner{postid}")
  public boolean checkPostOwner(@PathVariable("sessionid") String sessionid, @PathVariable("postid") int postid) {
    User user = userController.authenticateUser(sessionid);
    Optional<ForumPost> opt = forumPostRepository.findById(postid);
    ForumPost fp = opt.orElse(null);
    if (user.equals(fp.getAuthor())) {
      return true;
    }
    return false;
  }


  @CrossOrigin(origins = "*")
  @PutMapping("/api/posts/{postid}")
  public ForumPost updatePost(@PathVariable("postid") int postid, @RequestBody ForumPost update) {
    Optional<ForumPost> opt = forumPostRepository.findById(postid);
    ForumPost fp = opt.orElse(null);
    fp.setContent(update.getContent());
    fp.setUpdateTime(update.getUpdateTime());
    forumPostRepository.save(fp);
    return fp;
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/api/posts/{postid}")
  public String deletePost(@PathVariable("postid") int postid) {
    forumPostRepository.deleteById(postid);
    return "suceess";
  }

}
