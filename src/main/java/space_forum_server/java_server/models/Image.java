package space_forum_server.java_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private List<Thread> threads;

  public Image() {
    super();
  }

  public Image(String title, String url, String description) {
    this.title = title;
    this.url = url;
    this.description = description;
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

  public List<Thread> getThreads() {
    return threads;
  }

  public void setThreads(List<Thread> threads) {
    this.threads = threads;
  }
}
