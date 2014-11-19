/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils;

import com.java4us.commons.enums.TimeIntervalType;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author turgay
 */
@SuppressWarnings("deprecation")
@Component
public final class DateUtils {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat
			.forPattern("dd/MM/yyyy");
	private static final DateTimeFormatter DATE_FORMATTER_WITHOUT_SEPARATOR = DateTimeFormat
			.forPattern("ddMMyyyy");
	private static final DateTimeFormatter DATE_FORMATTER_BY_YEAR = DateTimeFormat
			.forPattern("yy/MM/dd");
	private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormat
			.forPattern("MM/yyyy");
	private static final DateTimeFormatter MONTH_FORMATTER_REVERSE = DateTimeFormat
			.forPattern("yyyy/MM");
	private static final DateTimeFormatter MONTH_VALUE_FORMATTER = DateTimeFormat
			.forPattern("dd MMMM yyyy");
	private static final DateTimeFormatter MONTH_VALUE_FORMATTER_WITH_UNDERSCORE = DateTimeFormat
			.forPattern("dd-MMM-yyyy");
	private static final DateTimeFormatter MONTH_YEAR = DateTimeFormat
			.forPattern("MMMM yyyy");
	private static final DateTimeFormatter CURRENT_TIME = DateTimeFormat
			.forPattern("dd-MMM-yyyy HH:mm:ss");
	private static final DateTimeFormatter LONG_TIME = DateTimeFormat
			.forPattern("dd-MM-yyyy HH:mm:ss");
	private static final String MEDIUM_FORMAT = "dd/MM/yyyy HH:mm";
	private static final String LONG_FORMAT = "dd-MM-yyyy HH:mm:ss";

	private DateUtils() {
	}

	public static Date toStartOfDay(Date date) {
		DateTime dateTime = new DateTime(date.getTime()).withTimeAtStartOfDay();
		return dateTime.toDate();
	}

	public static Date toStartOfDay(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}

		Date parsedDate = DATE_FORMATTER.parseDateTime(date).toDate();
		return toStartOfDay(parsedDate);
	}

	public static Date toLongFormattedDate(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return getLongFormatDate(date);
	}

	private static Date getLongFormatDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);
		return parseDate(date, dateFormat);
	}

	public static Date toLongFormatDate(Date date) {
		String formattedDate = new SimpleDateFormat(LONG_FORMAT).format(date);
		return LONG_TIME.parseDateTime(formattedDate).toDate();
	}

	private static Date parseDate(String date, SimpleDateFormat dateFormat) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Date toEndOfDay(Date date) {
		final int endHour = 23;
		final int endMinute = 59;
		final int endSecond = 59;
		final int endMSecond = 999;

		return setTimeOfDate(date, endHour, endMinute, endSecond, endMSecond);
	}

	public static Date toEndOfDay(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}

		Date parsedDate = DATE_FORMATTER.parseDateTime(date).toDate();
		return toEndOfDay(parsedDate);
	}

	public static boolean isWeekDay(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getDayOfWeek() != DateTimeConstants.SATURDAY
				&& dateTime.getDayOfWeek() != DateTimeConstants.SUNDAY;
	}

	public static int daysDifference(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null) {
			return 0;
		}
		return Days.daysBetween(new DateTime(toStartOfDay(earlierDate)),
				new DateTime(toEndOfDay(laterDate))).getDays();
	}

	public static int daysDifferenceWithToday(Date date) {
		Date today = Clock.getTime();
		if (today.compareTo(date) == 0) {
			return 1;
		}
		if (today.compareTo(date) > 0) {
			return daysDifference(date, today);
		} else {
			return daysDifference(today, date);
		}
	}

	public static int yearDifferenceWithToday(Date date) {
		if (date == null) {
			return 0;
		}
		return yearDifference(date, Clock.getTime());
	}

	public static int yearDifference(Date date, Date today) {
		DateMidnight firstDate = new DateMidnight(date.getTime());
		DateTime now = new DateTime(today.getTime());
		return Years.yearsBetween(firstDate, now).getYears();
	}

	public static int secondDifference(Date date, Date today) {
		DateMidnight firstDate = new DateMidnight(date.getTime());
		DateTime now = new DateTime(today.getTime());
		return Seconds.secondsBetween(firstDate, now).getSeconds();
	}

	public static Date addSeconds(Date startDate, int seconds) {
		return DateUtils.addSeconds(startDate, seconds);
	}

	public static Date addMinutes(Date startDate, int minutes) {
		return DateUtils.addMinutes(startDate, minutes);
	}

	public static Date addDays(Date startDate, Integer days) {
		return DateUtils.addDays(startDate, days);
	}

	public static Date addMonths(Date startDate, Integer month) {
		return DateUtils.addMonths(startDate, month);
	}

	public static Date addYears(Date startDate, Integer years) {
		return DateUtils.addYears(startDate, years);
	}

	public static Date setTimeOfDate(Date date, int hour, int minute,
			int second, int milisecond) {
		DateTime dateTime = new DateTime(date.getTime()).withTime(hour, minute,
				second, milisecond);

		return dateTime.toDate();
	}

	public static Date resetSecondAndMilliSecond(Date date) {
		DateTime dateTime = new DateTime(date);
		return setTimeOfDate(date, dateTime.getHourOfDay(),
				dateTime.getMinuteOfHour(), 0, 0);
	}

	public static Date setTimeOfDateFromAnotherDate(Date date, Date source) {
		DateTime sourceDateTime = new DateTime(source.getTime());
		DateTime dateTime = new DateTime(date.getTime()).withTime(
				sourceDateTime.getHourOfDay(),
				sourceDateTime.getMinuteOfHour(),
				sourceDateTime.getSecondOfMinute(),
				sourceDateTime.getMillisOfSecond());

		return dateTime.toDate();
	}

	public static Date createDateWith(int year, int month, int day) {
		DateTime dateTime = new DateTime(Clock.getTime()).withDate(year, month,
				day);

		return dateTime.toDate();
	}

	public static Date setTimeOfDateToNow(Date date) {
		DateTime now = DateTime.now();
		DateTime dateTime = new DateTime(date).withTime(now.getHourOfDay(),
				now.getMinuteOfHour(), now.getSecondOfMinute(),
				now.getMillisOfSecond());

		return dateTime.toDate();
	}

	public static Integer getTimeFieldOfDate(Date date,
			DateTimeFieldType fieldType) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.get(fieldType);
	}

	public static boolean isDateBetweenIncludedDates(Date date, Date startDate,
			Date endDate) {
		return (endDate == null || endDate.compareTo(toStartOfDay(date)) >= 0)
				&& (startDate == null || startDate.compareTo(toEndOfDay(date)) <= 0);
	}

	public static boolean isCurrentDateBetweenBySpecificDates(Date startDate,
			Date endDate) {
		Date currentDate = Clock.getTime();
		return currentDate.compareTo(endDate) <= 0
				&& currentDate.compareTo(startDate) >= 0;
	}

	public static boolean isCurrentDateBetweenBySpecificDatesWithoutTime(
			Date startDate, Date endDate) {
		return isCurrentDateBetweenBySpecificDates(toStartOfDay(startDate),
				toEndOfDay(endDate));
	}

	public static boolean isCurrentDateAfterSpecificDate(Date date) {
		Date currentDate = Clock.getTime();
		return currentDate.after(date);
	}

	public static boolean isCurrentDateBeforeSpecificDate(Date date) {
		Date currentDate = Clock.getTime();
		return currentDate.before(date);
	}

	public static Date nextDayMidNight() {
		Date today = Clock.getTime();
		return toStartOfDay(addDays(today, 1));
	}

	public static Date getMaxDate() {
		return DATE_FORMATTER.parseDateTime("31/12/2999").toDate();
	}

	public static Date endOfCentury() {
		return toEndOfDay(DATE_FORMATTER.parseDateTime("31/12/2099").toDate());
	}

	public static Date getMinDate() {
		return DATE_FORMATTER.parseDateTime("29/10/1923").toDate();
	}

	public static Date getCurrentTime() {
		return CURRENT_TIME.parseDateTime(
				new DateTime(Clock.getTime()).toString()).toDate();
	}

	public static Date toStartOfToday() {
		return DateUtils.toStartOfDay(Clock.getTime());
	}

	public static long getRemainingSecondsToMidnight() {
		Date midnight = DateUtils.toEndOfDay(Clock.getTime());
		Date now = Clock.getTime();
		return (midnight.getTime() - now.getTime()) / 1000;
	}

	public static int getRemainingDaysTo(Date date) {
		return getRemainingDaysTo(date, true);
	}

	public static int getRemainingDaysTo(Date date, boolean includeLastDay) {
		DateMidnight futureDate = new DateTime(date).toDateMidnight();
		DateMidnight today = new DateTime(Clock.getTime()).toDateMidnight();
		int remainingDays = Days.daysBetween(today, futureDate).getDays();

		if (includeLastDay) {
			remainingDays += 1;
		}

		return remainingDays < 0 ? 0 : remainingDays;
	}

	public static Map<String, String> getLastThreeMonthNamesWithId() {
		Map<String, String> monthNameAndIdMap = new HashMap<String, String>();

		List<String> monthNameList = getLastMonthNamesBy(3);
		monthNameAndIdMap.put(TimeIntervalType.THIS_MONTH.getId(),
				monthNameList.get(0));
		monthNameAndIdMap.put(TimeIntervalType.LAST_MONTH.getId(),
				monthNameList.get(1));
		monthNameAndIdMap.put(TimeIntervalType.LAST_SECOND_MONTH.getId(),
				monthNameList.get(2));

		return monthNameAndIdMap;
	}

	public static List<String> getLastMonthNamesBy(int count) {
		List<String> monthNameList = new ArrayList<String>();

		DateTime dateTime = new DateTime(Clock.getTime());
		for (int i = 0; i < count; ++i) {
			int monthNo = dateTime.minusMonths(i).getMonthOfYear();
			String month = new DateFormatSymbols(CommonLocale.getTR())
					.getMonths()[monthNo - 1];
			monthNameList.add(month);
		}
		return monthNameList;
	}

	public static boolean isDateBetweenBySpecificDatesWithoutTime(Date date,
			Date startDate, Date finishDate) {
		return date.compareTo(finishDate) <= 0
				&& date.compareTo(startDate) >= 0;
	}

	public static String getLocalizedDate(Date date, String pattern) {
		return date == null ? "-" : new SimpleDateFormat(pattern,
				CommonLocale.getTR()).format(date);
	}

	public static String formatDate(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern,
				CommonLocale.getTR()).format(date);
	}

	public static String toString(Date date) {
		return new DateTime(date).toString(DATE_FORMATTER);
	}

	public static String formatCurrentDateWithMonthValue() {
		return new DateTime(Clock.getTime()).toString(MONTH_VALUE_FORMATTER
				.withLocale(CommonLocale.getTR()));
	}

	public static String formatCurrentDateWithMonthValueWithUnderScore(Date date) {
		return new DateTime(date)
				.toString(MONTH_VALUE_FORMATTER_WITH_UNDERSCORE
						.withLocale(CommonLocale.getEN()));
	}

	public static String formatCurrentDateWithoutSeparator() {
		return new DateTime(Clock.getTime())
				.toString(DATE_FORMATTER_WITHOUT_SEPARATOR
						.withLocale(CommonLocale.getTR()));
	}

	public static String formatDateWithMonthValue(Date date) {
		return new DateTime(date).toString(MONTH_YEAR.withLocale(CommonLocale
				.getTR()));
	}

	public static Date toStartOfLastMonth() {
		return toStartOfNMonthsAgo(1);
	}

	public static Date toEndOfLastMonth() {
		return toEndOfNMonthsAgo(1);
	}

	public static Date toStartOfNMonthsAgo(int months) {
		Date now = Clock.getTime();
		DateTime sourceDateTime = new DateTime(now.getTime());

		return sourceDateTime.minusMonths(months).dayOfMonth()
				.withMinimumValue().hourOfDay().withMinimumValue()
				.secondOfDay().withMinimumValue().minuteOfDay()
				.withMinimumValue().toDate();
	}

	public static Date toStartOfNMonthsAgo(int months, Date date) {
		DateTime sourceDateTime = new DateTime(date.getTime());
		return sourceDateTime.minusMonths(months).dayOfMonth()
				.withMinimumValue().hourOfDay().withMinimumValue()
				.secondOfDay().withMinimumValue().minuteOfDay()
				.withMinimumValue().millisOfDay().withMinimumValue().toDate();
	}

	public static Date toEndOfNMonthsAgo(int months) {
		Date now = Clock.getTime();
		DateTime sourceDateTime = new DateTime(now.getTime());

		return sourceDateTime.minusMonths(months).dayOfMonth()
				.withMaximumValue().hourOfDay().withMaximumValue()
				.secondOfDay().withMaximumValue().minuteOfDay()
				.withMaximumValue().toDate();
	}

	public static Date toEndOfMonth(Date sourceDate) {
		DateTime sourceDateTime = new DateTime(sourceDate);
		return sourceDateTime.dayOfMonth().withMaximumValue().hourOfDay()
				.withMaximumValue().secondOfDay().withMaximumValue()
				.minuteOfDay().withMaximumValue().toDate();
	}

	public static Date toEndOfMonth(int year, int month) {
		DateTime sourceDateTime = new DateTime(year, month, 1, 1, 1);
		return sourceDateTime.dayOfMonth().withMaximumValue().hourOfDay()
				.withMaximumValue().secondOfDay().withMaximumValue()
				.minuteOfDay().withMaximumValue().toDate();
	}

	public static Date toStartOfWeek(Date sourceDate) {
		DateTime sourceDateTime = new DateTime(sourceDate);
		return sourceDateTime.dayOfWeek().withMinimumValue().hourOfDay()
				.withMinimumValue().secondOfDay().withMinimumValue()
				.minuteOfDay().withMinimumValue().toDate();
	}

	public static Date toEndOfWeek(Date sourceDate) {
		DateTime sourceDateTime = new DateTime(sourceDate);
		return sourceDateTime.dayOfWeek().withMaximumValue().hourOfDay()
				.withMaximumValue().secondOfDay().withMaximumValue()
				.minuteOfDay().withMaximumValue().toDate();
	}

	public static int getYearPart(Date sourceDate) {
		return Integer
				.parseInt(new SimpleDateFormat("yyyy").format(sourceDate));
	}

	public static Date getDateFromYearAndMonth(int year, int month) {
		return createDateWith(
				year,
				month,
				getTimeFieldOfDate(toEndOfMonth(year, month),
						DateTimeFieldType.dayOfMonth()));
	}

	public static Date toStartOfMonth(Date date) {
		return toStartOfNMonthsAgo(0, date);
	}

	public static String getMonthlyFormatted(Date date) {
		return new DateTime(date).toString(MONTH_FORMATTER);
	}

	public static String getMonthlyReverseFormatted(Date date) {
		return new DateTime(date).toString(MONTH_FORMATTER_REVERSE);
	}

	public static String getFormattedDate(Date date) {
		return new DateTime(date).toString(DATE_FORMATTER);
	}

	public static String getFormattedDateWithoutSeperator(Date date) {
		return new DateTime(date).toString(DATE_FORMATTER_WITHOUT_SEPARATOR);
	}

	public static String getFormattedCurrentDateByYear() {
		return new DateTime(Clock.getTime()).toString(DATE_FORMATTER_BY_YEAR);
	}

	public static Date arrangeWorkHours(Date date) {
		Date thatDay6pm = to6pm(date);
		if (date.before(thatDay6pm)) {
			return thatDay6pm;
		}
		return toNextDayMorningOf(date);
	}

	public static Date to6pm(Date date) {
		return setTimeOfDate(date, 18, 0, 0, 0);
	}

	public static Date to8am(Date date) {
		return setTimeOfDate(date, 8, 0, 0, 0);
	}

	public static Date to11am(Date date) {
		return setTimeOfDate(date, 11, 0, 0, 0);
	}

	public static Date toNextDayMorningOf(Date date) {
		return toStartOfDay(addDays(date, 1));
	}

	public static String getMonthName(int index) {
		if (index < 1 || index > 12) {
			index = 1;
		}

		return new DateFormatSymbols(CommonLocale.getTR()).getMonths()[index - 1];
	}

	public static Date getYesterday() {
		return addDays(Clock.getTime(), -1);
	}

	public static String getDateWithHour(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(MEDIUM_FORMAT).format(date);
	}

	public static Date getLastDayOf(int year, int month) {

		DateMidnight now = new DateMidnight().withDayOfMonth(1);
		DateMidnight lastDate = now.minusYears(now.getYear() - year)
				.minusMonths(now.getMonthOfYear() - (month + 1)).minusDays(1);
		return toEndOfDay(lastDate.toDate());
	}

	public static String getDateByDateFormat(Date date, String dateFormat) {
		return new DateTime(date).toString(dateFormat);
	}

	public static int getMaxSelectableYearForMonth() {
		DateTime dateTime = new DateTime();
		return dateTime.getYear() - 18;
	}

	public static int getSecondsDifference(Date startDate, Date endDate) {
		DateTime now = new DateTime(startDate);
		DateTime end = new DateTime(endDate);
		return Seconds.secondsBetween(now, end).getSeconds();
	}

	public static Date getEndOfCurrentDateSecondOfMinuteAndMilliSeconds() {
		return new DateTime(Clock.getTime()).withSecondOfMinute(59)
				.withMillisOfSecond(999).toDate();
	}

	public static int getCurrentYear() {
		return new DateTime(Clock.getTime()).getYear();
	}

	public static boolean isEqualsWithoutTime(Date date1, Date date2) {
		DateMidnight firstDate = new DateTime(date1).toDateMidnight();
		DateMidnight secondDate = new DateTime(date2).toDateMidnight();
		return firstDate.compareTo(secondDate) == 0;
	}
}
