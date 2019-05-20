package it.my.test;

public class SpecialBody {

  private String type = "B";
  private String content;

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

  public SpecialBody(String type, String content) {
    super();
    this.type = type;
    this.content = content;
  }

  public SpecialBody(String input) {
    this.content = input;
  }

  @Override
  public String toString() {
    return String.format("SpecialBody [type=%s, content=%s]", type, content);
  }
}
