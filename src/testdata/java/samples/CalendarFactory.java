package samples;

import java.util.GregorianCalendar;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class CalendarFactory {
  @GeneratePojoBuilder(intoPackage = "samples", excludeProperties = "*")
  public static GregorianCalendar createCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute,
      int second) {
    return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
  }
}
