package cl.gendigital.gendeporte.users.infra.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

	public static boolean afterNow(@NonNull LocalDateTime date) {
		return date.isAfter(LocalDateTime.now());
	}

	public static boolean beforeNow(@NonNull LocalDateTime date) {
		return date.isBefore(LocalDateTime.now());
	}

	public static boolean nowAfter(@NonNull LocalDateTime date) {
		return LocalDateTime.now().isAfter(date);
	}

	public static boolean nowBefore(@NonNull LocalDateTime date) {
		return LocalDateTime.now().isBefore(date);
	}

	public static LocalDate addDays(int days) {
		return addDays(LocalDate.now(), days);
	}

	public static LocalDate addDays(@NonNull LocalDate date, int days) {
		return date.plusDays(days);
	}

	public static boolean between(@NonNull LocalDateTime dateTime, @NonNull LocalDateTime from, @NonNull LocalDateTime to) {
		return dateTime.isAfter(from) && dateTime.isBefore(to);
	}

	public static boolean between(@NonNull LocalDate date, @NonNull LocalDate from, @NonNull LocalDate to) {
		return date.isAfter(from) && date.isBefore(to);
	}

	// Transformation methods

	public static LocalDate toLocalDate(@NonNull LocalDateTime date) {
		return toLocalDate(date, ZoneId.systemDefault());
	}

	public static LocalDate toLocalDate(@NonNull LocalDateTime date, @NonNull ZoneId zoneId) {
		return date.atZone(zoneId).toLocalDate();
	}

	public static LocalTime toLocalTime(@NonNull LocalDateTime time) {
		return toLocalTime(time, ZoneId.systemDefault());
	}

	public static LocalTime toLocalTime(@NonNull LocalDateTime time, @NonNull ZoneId zoneId) {
		return time.atZone(zoneId).toLocalTime();
	}

	public static LocalDate toLocalDate(@NonNull Date date) {
		return toLocalDate(date, ZoneId.systemDefault());
	}

	public static LocalDate toLocalDate(@NonNull Date date, @NonNull ZoneId zoneId) {
		return toLocalDateTime(date, zoneId).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(@NonNull Date date) {
		return toLocalDateTime(date, ZoneId.systemDefault());
	}

	public static LocalDateTime toLocalDateTime(@NonNull Date date, @NonNull ZoneId zoneId) {
		return date.toInstant().atZone(zoneId).toLocalDateTime();
	}

	// Parse methods

	public static String toString(@NonNull LocalDate date) {
		return toString(date, DateTimeFormatter.ISO_LOCAL_DATE.toString());
	}

	public static String toString(@NonNull LocalDateTime date) {
		return toString(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME.toString());
	}

	public static String toString(@NonNull LocalDate date, @NonNull String format) {
		return getString(date, format);
	}

	public static String toString(@NonNull LocalDateTime date, @NonNull String format) {
		return getString(date, format);
	}

	private static String getString(@NonNull TemporalAccessor date, @NonNull String format) {
		return formatter(format).format(date);
	}

	public static LocalDate parseLocalDate(@NonNull String formattedDate){
		return parseLocalDate(formattedDate, DateTimeFormatter.ISO_LOCAL_DATE.toString());
	}

	public static LocalDateTime parseLocalDateTime(@NonNull String formattedDate){
		return parseLocalDateTime(formattedDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME.toString());
	}

	public static LocalDate parseLocalDate(@NonNull String formattedDate, @NonNull String format){
		return LocalDate.parse(formattedDate, formatter(format));
	}

	public static LocalDateTime parseLocalDateTime(@NonNull String formattedDate, @NonNull String format){
		return LocalDateTime.parse(formattedDate, formatter(format));
	}

	public static DateTimeFormatter formatter(@NonNull String format){
		return DateTimeFormatter.ofPattern(format);
	}

	// java.util.Date

	public static Date to(LocalDate localDate) {
		return to(localDate, ZoneId.systemDefault());
	}

	public static Date to(LocalDateTime localDate) {
		return to(localDate, ZoneId.systemDefault());
	}

	public static Date to(LocalDate localDate, ZoneId zoneId) {
		return Date.from(localDate.atStartOfDay().atZone(zoneId).toInstant());
	}

	public static Date to(LocalDateTime dateTime, ZoneId zoneId) {
		return Date.from(dateTime.atZone(zoneId).toInstant());
	}
}
