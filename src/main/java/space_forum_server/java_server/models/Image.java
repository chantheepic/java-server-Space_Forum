package space_forum_server.java_server.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  private String url;
  private String description;
  @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
  private List<ForumThread> forumThreads;

  public Image() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<ForumThread> getForumThreads() {
    return forumThreads;
  }

  public void setForumThreads(List<ForumThread> forumThreads) {
    this.forumThreads = forumThreads;
  }
}
