package de.focus_shift.jackson;

import de.focus_shift.spi.Configuration;
import de.focus_shift.spi.Holidays;

import java.util.stream.Stream;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class jacksonConfiguration implements Configuration {

  private final de.focus_shift.jackson.mapping.Configuration xmlConfiguration;

  public jacksonConfiguration(de.focus_shift.jackson.mapping.Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  @Override
  public Holidays holidays() {
    return new jacksonHolidays(xmlConfiguration.getHolidays());
  }

  @Override
  public Stream<Configuration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(jacksonConfiguration::new);
  }

  @Override
  public String hierarchy() {
    return xmlConfiguration.getHierarchy();
  }

  @Override
  public String description() {
    return xmlConfiguration.getDescription();
  }

}
