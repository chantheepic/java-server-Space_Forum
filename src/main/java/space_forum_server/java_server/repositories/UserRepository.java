package space_forum_server.java_server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import space_forum_server.java_server.models.*;
import org.springframework.data.repository.*;

public interface UserRepository extends CrudRepository<User, Integer> {

  @Query("SELECT User FROM User User WHERE User.username=:usern AND User.password=:pswd")
  public User authenticate(@Param("usern") String usern, @Param("pswd") String pswd);
    }