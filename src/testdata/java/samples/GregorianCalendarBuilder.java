package samples;

import java.util.GregorianCalendar;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class GregorianCalendarBuilder
    implements Cloneable {
  protected GregorianCalendarBuilder self;
  protected int value$year$int;
  protected boolean isSet$year$int;
  protected int value$month$int;
  protected boolean isSet$month$int;
  protected int value$dayOfMonth$int;
  protected boolean isSet$dayOfMonth$int;
  protected int value$hourOfDay$int;
  protected boolean isSet$hourOfDay$int;
  protected int value$minute$int;
  protected boolean isSet$minute$int;
  protected int value$second$int;
  protected boolean isSet$second$int;

  /**
   * Creates a new {@link GregorianCalendarBuilder}.
   */
  public GregorianCalendarBuilder() {
    self = (GregorianCalendarBuilder)this;
  }

  /**
   * Sets the default value for the year property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withYear(int value) {
    this.value$year$int = value;
    this.isSet$year$int = true;
    return self;
  }

  /**
   * Sets the default value for the month property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withMonth(int value) {
    this.value$month$int = value;
    this.isSet$month$int = true;
    return self;
  }

  /**
   * Sets the default value for the dayOfMonth property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withDayOfMonth(int value) {
    this.value$dayOfMonth$int = value;
    this.isSet$dayOfMonth$int = true;
    return self;
  }

  /**
   * Sets the default value for the hourOfDay property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withHourOfDay(int value) {
    this.value$hourOfDay$int = value;
    this.isSet$hourOfDay$int = true;
    return self;
  }

  /**
   * Sets the default value for the minute property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withMinute(int value) {
    this.value$minute$int = value;
    this.isSet$minute$int = true;
    return self;
  }

  /**
   * Sets the default value for the second property.
   *
   * @param value the default value
   * @return this builder
   */
  public GregorianCalendarBuilder withSecond(int value) {
    this.value$second$int = value;
    this.isSet$second$int = true;
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
      GregorianCalendarBuilder result = (GregorianCalendarBuilder)super.clone();
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
  public GregorianCalendarBuilder but() {
    return (GregorianCalendarBuilder)clone();
  }

  /**
   * Creates a new {@link GregorianCalendar} based on this builder's settings.
   *
   * @return the created GregorianCalendar
   */
  public GregorianCalendar build() {
    try {
      GregorianCalendar result = CalendarFactory.createCalendar(value$year$int, value$month$int, value$dayOfMonth$int, value$hourOfDay$int, value$minute$int, value$second$int);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
