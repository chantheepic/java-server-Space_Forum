package space_forum_server.java_server.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
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
  @OneToMany(mappedBy = "postauthor", cascade = CascadeType.ALL)
  private List<ForumPost> createdForumPost;
  @OneToMany(mappedBy = "threadauthor", cascade = CascadeType.ALL)
  private List<ForumThread> createdForumThreads;
  @OneToMany
  private List<ForumThread> followingThreads;

  public User() {
    super();
  }

  public User(String username, String alias, String password) {
    this.id = 0;
    this.username = username;
    this.alias = alias;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
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

  public List<Image> getLikedImages() {
    return likedImages;
  }

  public void setLikedImages(List<Image> likedImages) {
    this.likedImages = likedImages;
  }

  public List<ForumPost> getCreatedForumPost() {
    return createdForumPost;
  }

  public void setCreatedForumPost(List<ForumPost> createdForumPost) {
    this.createdForumPost = createdForumPost;
  }

  public List<ForumThread> getCreatedForumThreads() {
    return createdForumThreads;
  }

  public void setCreatedForumThreads(List<ForumThread> createdForumThreads) {
    this.createdForumThreads = createdForumThreads;
  }

  public List<ForumThread> getFollowingThreads() {
    return followingThreads;
  }

  public void setFollowingThreads(List<ForumThread> followingThreads) {
    this.followingThreads = followingThreads;
  }
}
