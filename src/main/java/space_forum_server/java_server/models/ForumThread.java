package space_forum_server.java_server.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import java.sql.Timestamp;
    import java.util.List;
    import java.util.Set;
    import javax.persistence.CascadeType;
    import javax.persistence.Entity;
    import javax.persistence.FetchType;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.JoinColumn;
    import javax.persistence.JoinTable;
    import javax.persistence.ManyToMany;
    import javax.persistence.ManyToOne;
    import javax.persistence.OneToMany;
    import org.hibernate.annotations.Fetch;
    import org.hibernate.annotations.FetchMode;

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
  @OneToMany(cascade = CascadeType.ALL)
  private List<ForumPost> posts;

  @ManyToMany
  @JoinTable(name = "ThreadLikedByUser",
      joinColumns = @JoinColumn(name = "ThreadID",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name =
          "UserID", referencedColumnName = "id"))
  @Fetch(value = FetchMode.JOIN)
  private Set<User> upvotedBy;

  @ManyToMany
  @JoinTable(name = "ThreadDislikedByUser",
      joinColumns = @JoinColumn(name = "ThreadID",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name =
          "UserID", referencedColumnName = "id"))
  @Fetch(value = FetchMode.JOIN)
  private Set<User> downvotedBy;

  public ForumThread() {
    super();
  }

  public ForumThread(String title, String text) {
    this.title = title;
    this.text = text;
  }

  public ForumThread(String title, Image image, String text, String type,
      User author, Set<User> upvotedBy, Set<User> downvotedBy, List<ForumPost> posts) {
    this.title = title;
    this.image = image;
    this.text = text;
    this.type = type;
    this.author = author;
    this.upvotedBy = upvotedBy;
    this.downvotedBy = downvotedBy;
    this.posts = posts;
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

  public Set<User> getUpvotedBy() {
    return upvotedBy;
  }

  public void setUpvotedBy(Set<User> upvotedBy) {
    this.upvotedBy = upvotedBy;
  }

  public Set<User> getDownvotedBy() {
    return downvotedBy;
  }

  public void setDownvotedBy(Set<User> downvotedBy) {
    this.downvotedBy = downvotedBy;
  }

  public List<ForumPost> getPosts() {
    return posts;
  }

  public void setPosts(List<ForumPost> posts) {
    this.posts = posts;
  }
}
