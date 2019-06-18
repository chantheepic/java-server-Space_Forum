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
    import javax.persistence.JoinTable;
    import javax.persistence.ManyToMany;
    import javax.persistence.ManyToOne;
    import javax.persistence.OneToMany;

@Entity
public class ForumThread {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  @ManyToOne
  @JoinColumn(name = "image")
  private Image image;
  private String text;
  private String type;
  @ManyToOne
  @JoinColumn(name = "author")
  private User author;
  private Timestamp createTime;
  private Timestamp lastedUpdated;
  @OneToMany
  private List<User> upvotedBy;
  @OneToMany
  private List<User> downvotedBy;
  @OneToMany
  private List<ForumPost> posts;

  public ForumThread() {
    super();
  }

  public ForumThread(String title, String text) {
    this.title = title;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
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

  public Timestamp getLastedUpdated() {
    return lastedUpdated;
  }

  public void setLastedUpdated(Timestamp lastedUpdated) {
    this.lastedUpdated = lastedUpdated;
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

  public List<ForumPost> getPosts() {
    return posts;
  }

  public void setPosts(List<ForumPost> posts) {
    this.posts = posts;
  }
}
