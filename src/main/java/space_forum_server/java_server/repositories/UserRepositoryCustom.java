package space_forum_server.java_server.repositories;

import java.util.List;
import space_forum_server.java_server.models.Image;
import space_forum_server.java_server.models.User;

public interface UserRepositoryCustom {
  List<Image> getLikedImages(User user);

}
