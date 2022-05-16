package de.focus_shift.jackson;

import de.focus_shift.ManagerParameter;
import de.focus_shift.datasource.ConfigurationDataSource;

import java.io.InputStream;
import java.net.URL;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class jacksonConfigurationService implements ConfigurationDataSource {

  private final de.focus_shift.jackson.XMLUtil xmlUtil = new XMLUtil();

  @Override
  public de.focus_shift.spi.Configuration getConfiguration(ManagerParameter parameter) {
    final URL resourceUrl = parameter.createResourceUrl();
    try (final InputStream inputStream = resourceUrl.openStream()) {
      return new jacksonConfiguration(xmlUtil.unmarshallConfiguration(inputStream));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot instantiate configuration from URL '" + resourceUrl + "'.", e);
    }
  }
}
