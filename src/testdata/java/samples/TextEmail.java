package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withCopyMethod = true)
public class TextEmail {
  private String from;
  private String[] recipients;
  private String subject;
  private String body;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String[] getRecipients() {
    return recipients;
  }

  public void setRecipients(String... recipients) {
    this.recipients = recipients;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }



}
