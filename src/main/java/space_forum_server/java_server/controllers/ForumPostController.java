package space_forum_server.java_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import space_forum_server.java_server.repositories.ForumPostRepository;

@RestController
public class ForumPostController {
  @Autowired
  ForumPostRepository forumPostRepository;

}
