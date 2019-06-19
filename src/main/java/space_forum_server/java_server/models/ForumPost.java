package space_forum_server.java_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.List;
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
  private Timestamp updateTime;
  private String content;
  @OneToMany
  private List<User> upvotedBy;
  @OneToMany
  private List<User> downvotedBy;
  @ManyToOne
  @JoinColumn(name = "thread")
  private ForumThread associatedThread;
  @OneToMany
  private List<ForumPost> replies;

  public ForumPost() {
    super();
  }

  public ForumPost(String content) {
    this.content = content;
  }

  public ForumPost(User author, Timestamp updateTime, String content,
      List<User> upvotedBy, List<User> downvotedBy, ForumThread associatedThread,
      List<ForumPost> replies) {
    this.author = author;
    this.updateTime = updateTime;
    this.content = content;
    this.upvotedBy = upvotedBy;
    this.downvotedBy = downvotedBy;
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

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<User> getUpvotedBy() {
    return upvotedBy;
  }

  public void setUpvotedBy(List<User> upvotedBy) {
    this.upvotedBy = upvotedBy;
  }

  public List<User> getDownvotedBy() {
    return downvotedBy;
  }

  public void setDownvotedBy(List<User> downvotedBy) {
    this.downvotedBy = downvotedBy;
  }

  public List<ForumPost> getReplies() {
    return replies;
  }

  public void setReplies(List<ForumPost> replies) {
    this.replies = replies;
  }
}


