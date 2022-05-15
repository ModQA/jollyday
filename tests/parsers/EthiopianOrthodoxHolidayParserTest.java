package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jaxb.mapping.EthiopianOrthodoxHolidayType;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.parser.impl.EthiopianOrthodoxHolidayParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
class EthiopianOrthodoxHolidayParserTest {

  private EthiopianOrthodoxHolidayParser parser = new EthiopianOrthodoxHolidayParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testAllHolidays() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.ENKUTATASH));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.MESKEL));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.TIMKAT));
    parser.parse(2010, holidays, config);
    assertEquals(3, holidays.size(), "Wrong number of holidays.");
    assertContains(calendarUtil.create(2010, 1, 18), holidays);
    assertContains(calendarUtil.create(2010, 9, 11), holidays);
    assertContains(calendarUtil.create(2010, 9, 27), holidays);
  }

  private void assertContains(LocalDate date, Set<Holiday> holidays) {
    assertTrue(calendarUtil.contains(holidays, date), "Missing holiday " + date);
  }

  /**
   * @return an EthiopianOrthodoxHoliday instance
   */
  private EthiopianOrthodoxHoliday createHoliday(EthiopianOrthodoxHolidayType type) {
    EthiopianOrthodoxHoliday h = new EthiopianOrthodoxHoliday();
    h.setType(type);
    return h;
  }

}