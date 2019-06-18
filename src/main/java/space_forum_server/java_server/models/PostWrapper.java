package space_forum_server.java_server.models;

public class PostWrapper {
  private String type;
  private String content;

  public PostWrapper(){
    super();
  }

  public PostWrapper(String type, String content) {
    this.type = type;
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
