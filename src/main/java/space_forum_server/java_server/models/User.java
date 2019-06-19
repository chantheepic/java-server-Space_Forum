package space_forum_server.java_server.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

// implementation of user model
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String username;
  private String alias;
  private String password;
  private boolean banned;
  private boolean isAdmin;
  @ManyToMany
  @JoinTable(name = "FavoriteImages",
      joinColumns = @JoinColumn(name = "UserID",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name =
          "ImageID", referencedColumnName = "id"))
  @Fetch(value = FetchMode.JOIN)
  private List<Image> likedImages;
  @OneToMany
  private List<ForumPost> createdForumPost;
  @OneToMany
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

  public User(String username, String alias, String password, boolean banned, boolean isAdmin,
      List<Image> likedImages, List<ForumPost> createdForumPost,
      List<ForumThread> createdForumThreads,
      List<ForumThread> followingThreads) {
    this.username = username;
    this.alias = alias;
    this.password = password;
    this.banned = banned;
    this.isAdmin = isAdmin;
    this.likedImages = likedImages;
    this.createdForumPost = createdForumPost;
    this.createdForumThreads = createdForumThreads;
    this.followingThreads = followingThreads;
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

  public boolean isBanned() {
    return banned;
  }

  public void setBanned(boolean banned) {
    this.banned = banned;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }
}
