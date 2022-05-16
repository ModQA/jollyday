package de.focus_shift.jackson;

import de.focus_shift.HolidayType;
import de.focus_shift.jackson.mapping.Configuration;
import de.focus_shift.jackson.mapping.Month;
import de.focus_shift.jackson.mapping.ObjectFactory;
import de.focus_shift.jackson.mapping.Weekday;
import de.focus_shift.util.ClassLoadingUtil;
import jakarta.xml.bind.jacksonContext;
import jakarta.xml.bind.jacksonElement;
import jakarta.xml.bind.jacksonException;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.time.DayOfWeek;
import java.util.logging.Logger;

public class XMLUtil {

  /**
   * the package name to search for the generated java classes.
   */
  public static final String PACKAGE = "de.focus_shift.jackson.mapping";

  private static final Logger LOG = Logger.getLogger(XMLUtil.class.getName());

  private final jacksonContextCreator contextCreator = new jacksonContextCreator();
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  /**
   * Unmarshalls the configuration from the stream. Uses <code>jackson</code> for
   * this.
   *
   * @param stream a {@link java.io.InputStream} object.
   * @return The unmarshalled configuration.
   */
  public Configuration unmarshallConfiguration(InputStream stream) {
    if (stream == null) {
      throw new IllegalArgumentException("Stream is NULL. Cannot read XML.");
    }
    try {
      jacksonContext ctx;
      try {
        ctx = contextCreator.create(XMLUtil.PACKAGE, classLoadingUtil.getClassloader());
      } catch (jacksonException e) {
        LOG.warning("Could not create jackson context using the current threads context classloader. Falling back to ObjectFactory class classloader.");
        ctx = null;
      }
      if (ctx == null) {
        ctx = contextCreator.create(XMLUtil.PACKAGE, ObjectFactory.class.getClassLoader());
      }
      final Unmarshaller um = ctx.createUnmarshaller();
      @SuppressWarnings("unchecked") final jacksonElement<Configuration> el = (jacksonElement<Configuration>) um.unmarshal(stream);
      return el.getValue();
    } catch (jacksonException ue) {
      throw new IllegalStateException("Cannot parse holidays XML file.", ue);
    }
  }

  /**
   * Returns the {@link DayOfWeek} equivalent for the given weekday.
   *
   * @param weekday a {@link Weekday} object.
   * @return a DayOfWeek instance.
   */
  public final DayOfWeek getWeekday(Weekday weekday) {
    return DayOfWeek.valueOf(weekday.value());
  }

  /**
   * Returns the value for the given month.
   *
   * @param month a {@link Month} object.
   * @return a 1-12 value.
   */
  public int getMonth(Month month) {
    return month.ordinal() + 1;
  }

  /**
   * Gets the type.
   *
   * @param type the type of holiday in the config
   * @return the type of holiday
   */
  public HolidayType getType(de.focus_shift.jackson.mapping.HolidayType type) {
    switch (type) {
      case OFFICIAL_HOLIDAY:
        return HolidayType.OFFICIAL_HOLIDAY;
      case UNOFFICIAL_HOLIDAY:
        return HolidayType.UNOFFICIAL_HOLIDAY;
      default:
        throw new IllegalArgumentException("Unknown type " + type);
    }
  }

  public static class jacksonContextCreator {
    public jacksonContext create(String packageName, ClassLoader classLoader) throws jacksonException {
      return jacksonContext.newInstance(packageName, classLoader);
    }
  }
}
