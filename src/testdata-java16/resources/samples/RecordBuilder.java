package samples;

import javax.annotation.processing.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class RecordBuilder
    implements Cloneable {
  protected RecordBuilder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected User value$assignee$samples$User;
  protected boolean isSet$assignee$samples$User;
  protected String value$description$java$lang$String;
  protected boolean isSet$description$java$lang$String;

  /**
   * Creates a new {@link RecordBuilder}.
   */
  public RecordBuilder() {
    self = (RecordBuilder)this;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public RecordBuilder withName(String value) {
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
  public RecordBuilder withAssignee(User value) {
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
  public RecordBuilder withDescription(String value) {
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
      RecordBuilder result = (RecordBuilder)super.clone();
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
  public RecordBuilder but() {
    return (RecordBuilder)clone();
  }

  /**
   * Creates a new {@link Record} based on this builder's settings.
   *
   * @return the created Record
   */
  public Record build() {
    try {
      Record result = new Record(value$name$java$lang$String, value$assignee$samples$User, value$description$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
