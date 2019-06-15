package space_forum_server.java_server.models;


import java.util.List;
import javax.persistence.*;

// implementation of user model
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String username;
  private String alias;
  private String password;
  @OneToMany
  List<Image> likedImages;
  @OneToMany
  List<ForumThread> followedForumThreads;
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  List<ForumThread> createdForumThreads;

  public User() {
    super();
  }

  public User(String username, String alias, String password) {
    this.username = username;
    this.alias = alias;
    this.password = password;
  }
}
