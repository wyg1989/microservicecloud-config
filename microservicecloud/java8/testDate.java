import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class testDate {
	@Test
	public void test() {
		
		LocalDate date = LocalDate.now();
		date.atStartOfDay();
		date.compareTo(LocalDate.now());
		date.equals(LocalDate.now());
		System.out.println(date.hashCode());
		date.format(DateTimeFormatter.ofPattern("yyyyMM-dd"));
		System.out.println(date);
		System.out.println(date.getDayOfMonth());
		LocalDateTime atTime = date.atTime(4, 29, 23);
		System.out.println(date.atTime(4, 29, 23));
		Era era = date.getEra();
		System.out.println(era);
		System.out.println(date.getMonth());
		System.out.println(date.getDayOfMonth());
		System.out.println(date.getMonthValue());
		System.out.println(date.isAfter(date.now()));
		
	}
}
