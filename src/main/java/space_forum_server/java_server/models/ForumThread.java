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
  @JoinColumn(name = "imageid")
  private Image image;
  @ManyToOne
  @JoinColumn(name = "authorid")
  private User author;
  private Timestamp createTime;
  private Timestamp lastedUpdated;
  @OneToMany
  private List<ForumPost> posts;
}
