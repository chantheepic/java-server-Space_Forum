package space_forum_server.java_server.models;

public class PostWrapper {
  private String type;
  private Integer threadid;
  private Integer postid;
  private String content;

  public PostWrapper(){
    super();
  }

  public PostWrapper(String type, Integer threadid, Integer postid, String content) {
    this.type = type;
    this.threadid = threadid;
    this.postid = postid;
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getThreadid() {
    return threadid;
  }

  public void setThreadid(Integer threadid) {
    this.threadid = threadid;
  }

  public Integer getPostid() {
    return postid;
  }

  public void setPostid(Integer postid) {
    this.postid = postid;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
