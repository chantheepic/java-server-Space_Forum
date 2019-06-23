package space_forum_server.java_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ForumPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JoinColumn(name = "author")
  private User author;
  private String content;
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JsonIgnore
  @JoinColumn(name = "thread")
  private ForumThread associatedThread;
  @OneToMany(cascade = CascadeType.ALL)
  private List<ForumPost> replies;

  public ForumPost() {
    super();
  }

  public ForumPost(String content) {
    this.content = content;
  }

  public ForumPost(User author, String content, ForumThread associatedThread, List<ForumPost> replies) {
    this.author = author;
    this.content = content;
    this.associatedThread = associatedThread;
    this.replies = replies;
  }

  public ForumThread getAssociatedThread() {
    return associatedThread;
  }

  public void setAssociatedThread(ForumThread associatedThread) {
    this.associatedThread = associatedThread;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<ForumPost> getReplies() {
    return replies;
  }

  public void setReplies(List<ForumPost> replies) {
    this.replies = replies;
  }
}