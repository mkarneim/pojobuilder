package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Task {
  private String name;
  private User assignee;
  private String description;

  @GeneratePojoBuilder(withFactoryMethod = "*")
  public Task(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getAssignee() {
    return assignee;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
