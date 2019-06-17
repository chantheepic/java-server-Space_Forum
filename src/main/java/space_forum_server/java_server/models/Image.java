package space_forum_server.java_server.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Image {
  @Id
  private int id;
  private String category;
  @OneToMany
  private List<ForumThread> forumThreads;

  public Image() {
    super();
  }

  public Image(int id, String category, List<ForumThread> forumThreads) {
    this.id = id;
    this.category = category;
    this.forumThreads = forumThreads;
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

  public List<ForumThread> getForumThreads() {
    return forumThreads;
  }

  public void setForumThreads(List<ForumThread> forumThreads) {
    this.forumThreads = forumThreads;
  }
}
