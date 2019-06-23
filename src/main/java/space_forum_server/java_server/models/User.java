package space_forum_server.java_server.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
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
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "FavoriteImages",
      joinColumns = @JoinColumn(name = "UserID",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name =
          "ImageID", referencedColumnName = "id"))
  @Fetch(value = FetchMode.JOIN)
  private List<Image> likedImages;
  @OneToMany(cascade = CascadeType.ALL)
  private List<ForumPost> createdForumPost;
  @OneToMany(cascade = CascadeType.ALL)
  private List<ForumThread> createdForumThreads;

  @ManyToMany(mappedBy = "upvotedBy", cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.JOIN)
  private Set<ForumThread> likedThreads;

  @ManyToMany(mappedBy = "downvotedBy", cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.JOIN)
  private Set<ForumThread> dislikedThreads;

  @ManyToMany
  @JoinTable(name = "UserFollow",
      joinColumns = @JoinColumn(name = "FollowerID",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name =
          "FolloweeID", referencedColumnName = "id"))
  @Fetch(value = FetchMode.JOIN)
  private Set<User> following;

  @ManyToMany(mappedBy = "following")
  @Fetch(value = FetchMode.JOIN)
  @JsonIgnore
  private Set<User> followedBy;

  public User() {
    super();
  }

  public User(String username, String alias, String password, boolean isAdmin) {
    this.id = 0;
    this.username = username;
    this.alias = alias;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public User(String username, String alias, String password, boolean banned, boolean isAdmin,
      List<Image> likedImages, List<ForumPost> createdForumPost,
      List<ForumThread> createdForumThreads) {
    this.username = username;
    this.alias = alias;
    this.password = password;
    this.banned = banned;
    this.isAdmin = isAdmin;
    this.likedImages = likedImages;
    this.createdForumPost = createdForumPost;
    this.createdForumThreads = createdForumThreads;
  }

  public Set<User> getFollowing() {
    return following;
  }

  public void setFollowing(Set<User> following) {
    this.following = following;
  }

  public Set<User> getFollowedBy() {
    return followedBy;
  }

  public void setFollowedBy(Set<User> followedBy) {
    this.followedBy = followedBy;
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
