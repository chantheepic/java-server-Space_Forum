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

  public List<ForumThread> getForumThreads() {
    return forumThreads;
  }

  public void setForumThreads(List<ForumThread> forumThreads) {
    this.forumThreads = forumThreads;
  }
}
