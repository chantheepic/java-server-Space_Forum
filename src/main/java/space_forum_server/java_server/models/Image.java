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

  public Image(String title, String url, String description) {
    this.title = title;
    this.url = url;
    this.description = description;
  }
}
