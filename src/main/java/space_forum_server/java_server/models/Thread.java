package space_forum_server.java_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Thread {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  private Timestamp lastUpdated;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user")
  private User user;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "imageid")
  private Image image;
//   private List<Post> posts;

  public Thread() {
    super();
  }

  public Thread(String title, Timestamp lastUpdated, User user, Image image) {
    this.title = title;
    this.lastUpdated = lastUpdated;
    this.user = user;
    this.image = image;
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

  public Timestamp getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Timestamp lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
