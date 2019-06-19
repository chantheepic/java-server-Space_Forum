package space_forum_server.java_server.models;

public class PostWrapper {
  private String type;
  private int threadid;
  private int postid;
  private String content;

  public PostWrapper(){
    super();
  }

  public PostWrapper(String type, int threadid, int postid, String content) {
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

  public int getThreadid() {
    return threadid;
  }

  public void setThreadid(int threadid) {
    this.threadid = threadid;
  }

  public int getPostid() {
    return postid;
  }

  public void setPostid(int postid) {
    this.postid = postid;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
