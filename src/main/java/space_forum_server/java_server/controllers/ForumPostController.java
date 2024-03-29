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
  @GetMapping("/api/threads/{threadid}/posts")
  public List<ForumPost> findAllPosts(@PathVariable("threadid") int threadid) {
    Optional<ForumThread> opt = forumThreadRepository.findById(threadid);
    ForumThread ft = opt.orElse(null);
    return ft.getPosts();
  }

  // Uses a wrapper to take in nedded threadid (required) and postid (optional)
  @CrossOrigin(origins = "*")
  @PostMapping("/api/users/{sessionid}/posts")
  public ForumThread registerPost(@PathVariable("sessionid") String sessionid, @RequestBody PostWrapper postWrapper) {
    User author = userController.authenticateUser(sessionid);
    ForumPost newPost = new ForumPost();
    newPost.setContent(postWrapper.getContent());
    newPost.setAuthor(author);

    Optional<ForumThread> opt = forumThreadRepository.findById(postWrapper.getThreadid());
    ForumThread ft = opt.orElse(null);
    newPost.setAssociatedThread(ft);
    forumPostRepository.save(newPost);

    if (postWrapper.getType().equals("THREADREPLY")) {
      ft.getPosts().add(newPost);
      forumThreadRepository.save(ft);
    }
    if (postWrapper.getType().equals("POSTREPLY")){
      Optional<ForumPost> optfp = forumPostRepository.findById(postWrapper.getPostid());
      ForumPost fp = optfp.orElse(null);
      fp.getReplies().add(newPost);
      forumPostRepository.save(fp);
    }

    return ft;
  }

  //api/users/{sessionid}/posts/{postid}/checkOwner
  @CrossOrigin(origins = "*")
  @GetMapping("/api/users/{sessionid}/posts/{postid}")
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
    forumPostRepository.save(fp);
    return fp;
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/api/posts/{postid}")
  public ForumThread deletePost(@PathVariable("postid") int postid) {
    Optional<ForumPost> opt = forumPostRepository.findById(postid);
    ForumPost fp = opt.orElse(null);
    ForumThread ft = fp.getAssociatedThread();
    fp.setAssociatedThread(null);

    for(ForumPost fpp : forumPostRepository.findAll()){
      if(fpp.getReplies().contains(fp)){
        fpp.getReplies().remove(fp);
        forumPostRepository.save(fpp);
        break;
      }
    }

    ft.getPosts().remove(fp);
    forumThreadRepository.save(ft);
    forumPostRepository.deleteById(postid);
    return ft;
  }

}
