package space_forum_server.java_server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import space_forum_server.java_server.models.*;
import org.springframework.data.repository.*;

public interface UserSessionRepository extends CrudRepository<UserSession, Integer> {

  @Query("SELECT UserSession FROM UserSession UserSession WHERE UserSession.token=:sessionid")
  public UserSession matchSession(@Param("sessionid") String sessionid);

  @Query("SELECT UserSession FROM UserSession UserSession WHERE UserSession.user=:userid ORDER BY UserSession.loginTime")
  public UserSession latestSession(@Param("userid") int userid);
}