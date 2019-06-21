package space_forum_server.java_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Image {
  @Id
  private int id;
  private String url;
  private String category;
  @OneToMany
  private List<ForumThread> forumThreads;

//  , fetch = FetchType.EAGER
  @ManyToMany(mappedBy = "likedImages")
  @Fetch(value = FetchMode.JOIN)
  @JsonIgnore
  private List<User> likedBy;

  public Image() {
    super();
  }

  public Image(int id, String url, String category,
      List<ForumThread> forumThreads, List<User> likedBy) {
    this.id = id;
    this.url = url;
    this.category = category;
    this.forumThreads = forumThreads;
    this.likedBy = likedBy;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<ForumThread> getForumThreads() {
    return forumThreads;
  }

  public void setForumThreads(List<ForumThread> forumThreads) {
    this.forumThreads = forumThreads;
  }

  public List<User> getLikedBy() {
    return likedBy;
  }

  public void setLikedBy(List<User> likedBy) {
    this.likedBy = likedBy;
  }
}
