package net.sp.referenceapp.domain;

public class Health {
  private final long id;
  private final String content;

  public Health(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
