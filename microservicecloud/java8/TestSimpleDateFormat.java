
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestSimpleDateFormat {
	public static void main(String[] args) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		Callable<LocalDate> task = new Callable<LocalDate>() {

			@Override
			public LocalDate call() throws Exception {
				// TODO Auto-generated method stub
				return LocalDate.parse("20200908", dtf) ;
			}
		};
		List<Future<LocalDate>> list  = new ArrayList<>();
		
		ExecutorService pool = Executors.newFixedThreadPool(10);//创建容量为10的固定大小的线程池
		for (int i = 0; i < 10; i++) {
			Future<LocalDate> submit = pool.submit(task);
			list.add(submit);
		}
		
		  for (Future<LocalDate> future : list) { System.out.println(future.get()); }
		 
		pool.shutdown();
	}
	
}
