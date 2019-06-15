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
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<ForumThread> createdForumThreads;

  public User() {
    super();
  }

  public User(String username, String alias, String password) {
    this.username = username;
    this.alias = alias;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Image> getLikedImages() {
    return likedImages;
  }

  public void setLikedImages(List<Image> likedImages) {
    this.likedImages = likedImages;
  }

  public List<ForumThread> getFollowedForumThreads() {
    return followedForumThreads;
  }

  public void setFollowedForumThreads(List<ForumThread> followedForumThreads) {
    this.followedForumThreads = followedForumThreads;
  }

  public List<ForumThread> getCreatedForumThreads() {
    return createdForumThreads;
  }

  public void setCreatedForumThreads(List<ForumThread> createdForumThreads) {
    this.createdForumThreads = createdForumThreads;
  }
}
