package space_forum_server.java_server.models;

import java.util.List;
import javax.persistence.OneToMany;

public class Admin extends User{
  @OneToMany
  private List<User> bannedUsers;
}
