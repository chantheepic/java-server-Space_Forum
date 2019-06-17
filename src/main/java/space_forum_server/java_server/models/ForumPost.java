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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ForumPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "author")
  private User author;
  private Timestamp createTime;
  private String content;
  private int upvotes;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "parentthread")
  private ForumThread parentThread;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "parentpost")
  private ForumPost parentPost;
  @OneToMany
  private List<ForumPost> replies;

  public ForumPost() {
    super();
  }

  public ForumPost(String content) {
    this.content = content;
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

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getUpvotes() {
    return upvotes;
  }

  public void setUpvotes(int upvotes) {
    this.upvotes = upvotes;
  }

  public List<ForumPost> getReplies() {
    return replies;
  }

  public void setReplies(List<ForumPost> replies) {
    this.replies = replies;
  }
}


