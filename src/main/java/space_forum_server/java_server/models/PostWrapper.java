package space_forum_server.java_server.models;

public class PostWrapper {
  String title;
  String type;
  int imageId;
  String content;
  String category;

  PostWrapper(){
    super();
  }

  public PostWrapper(String title, String type, int imageId, String content, String category) {
    this.title = title;
    this.type = type;
    this.imageId = imageId;
    this.content = content;
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
