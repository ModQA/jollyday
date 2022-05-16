package de.focus_shift.jackson;

import de.focus_shift.spi.ChristianHoliday;
import de.focus_shift.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.spi.Fixed;
import de.focus_shift.spi.FixedWeekdayBetweenFixed;
import de.focus_shift.spi.FixedWeekdayInMonth;
import de.focus_shift.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.spi.HinduHoliday;
import de.focus_shift.spi.IslamicHoliday;
import de.focus_shift.spi.RelativeToEasterSunday;
import de.focus_shift.spi.RelativeToFixed;
import de.focus_shift.spi.RelativeToWeekdayInMonth;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class jacksonHolidays implements de.focus_shift.spi.Holidays {

  private final de.focus_shift.jackson.mapping.Holidays holidays;

  public jacksonHolidays(de.focus_shift.jackson.mapping.Holidays holidays) {
    this.holidays = holidays;
  }

  @Override
  public List<Fixed> fixed() {
    return holidays.getFixed().stream().map(jacksonFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToFixed> relativeToFixed() {
    return holidays.getRelativeToFixed().stream().map(jacksonRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream().map(jacksonRelativeToWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayInMonth> fixedWeekdays() {
    return holidays.getFixedWeekday().stream().map(jacksonFixedWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<ChristianHoliday> christianHolidays() {
    return holidays.getChristianHoliday().stream().map(jacksonChristianHoliday::new).collect(toList());
  }

  @Override
  public List<IslamicHoliday> islamicHolidays() {
    return holidays.getIslamicHoliday().stream().map(jacksonIslamicHoliday::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream().map(jacksonFixedWeekdayBetweenFixed::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream().map(jacksonFixedWeekdayRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<HinduHoliday> hinduHolidays() {
    return holidays.getHinduHoliday().stream().map(jacksonHinduHoliday::new).collect(toList());
  }

  @Override
  public List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream().map(jacksonEthiopianOrthodoxHoliday::new).collect(toList());
  }

  @Override
  public List<RelativeToEasterSunday> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream().map(jacksonRelativeToEasterSunday::new).collect(toList());
  }
}
