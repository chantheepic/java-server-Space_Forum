package space_forum_server.java_server.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ForumThread {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  String title;
  @ManyToOne()
  @JoinColumn(name = "imageid")
  Image image;
  @ManyToOne()
  @JoinColumn(name = "authorid")
  User author;
  Timestamp createTime;
  Timestamp lastedUpdated;
}
