package space_forum_server.java_server.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private List<Image> likedImages;
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<ForumPost> createdForumPost;
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<ForumThread> createdForumThreads;
  @OneToMany
  private List<ForumThread> followingThreads;


  public User() {
    super();
  }

  public User(String username, String alias, String password) {
    this.username = username;
    this.alias = alias;
    this.password = password;
  }
}
