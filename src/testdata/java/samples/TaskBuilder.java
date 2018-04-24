package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class TaskBuilder
    implements Cloneable {
  protected TaskBuilder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected User value$assignee$samples$User;
  protected boolean isSet$assignee$samples$User;
  protected String value$description$java$lang$String;
  protected boolean isSet$description$java$lang$String;

  /**
   * Factory Method to construct a TaskBuilder
   *
   * @return a new TaskBuilder
   */
  public static TaskBuilder task() {
    return new TaskBuilder();
  }

  /**
   * Creates a new {@link TaskBuilder}.
   */
  public TaskBuilder() {
    self = (TaskBuilder)this;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public TaskBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the assignee property.
   *
   * @param value the default value
   * @return this builder
   */
  public TaskBuilder withAssignee(User value) {
    this.value$assignee$samples$User = value;
    this.isSet$assignee$samples$User = true;
    return self;
  }

  /**
   * Sets the default value for the description property.
   *
   * @param value the default value
   * @return this builder
   */
  public TaskBuilder withDescription(String value) {
    this.value$description$java$lang$String = value;
    this.isSet$description$java$lang$String = true;
    return self;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @Override
  @GwtIncompatible
  public Object clone() {
    try {
      TaskBuilder result = (TaskBuilder)super.clone();
      result.self = result;
      return result;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @GwtIncompatible
  public TaskBuilder but() {
    return (TaskBuilder)clone();
  }

  /**
   * Creates a new {@link Task} based on this builder's settings.
   *
   * @return the created Task
   */
  public Task build() {
    try {
      Task result = new Task(value$name$java$lang$String);
      if (isSet$assignee$samples$User) {
        result.setAssignee(value$assignee$samples$User);
      }
      if (isSet$description$java$lang$String) {
        result.setDescription(value$description$java$lang$String);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
