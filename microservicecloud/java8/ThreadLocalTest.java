import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalTest {
	private static final ThreadLocal<DateFormat> df = new ThreadLocal(){
		
		protected DateFormat initialValue() {
			
			return new SimpleDateFormat("yyyyMMdd");
		}		
	};
	public static Date convet(String str) throws ParseException {
		return 	df.get().parse(str);
	}
}
