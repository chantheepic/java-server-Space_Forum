package space_forum_server.java_server.models;

    import com.google.common.hash.Hashing;
    import java.nio.charset.StandardCharsets;
    import java.security.NoSuchAlgorithmException;
    import java.sql.Timestamp;
    import java.time.LocalDateTime;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.JoinColumn;
    import javax.persistence.ManyToOne;

@Entity
public class UserSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JoinColumn(name = "userid")
  private User user;
  private Timestamp loginTime;
  private String token;

  public UserSession() throws NoSuchAlgorithmException {
    this.id = 0;
    this.loginTime = new Timestamp(System.currentTimeMillis());
    String hash = Hashing.sha256()
        .hashString(LocalDateTime.now().toString(), StandardCharsets.UTF_8)
        .toString();
    this.token = hash;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Timestamp loginTime) {
    this.loginTime = loginTime;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
