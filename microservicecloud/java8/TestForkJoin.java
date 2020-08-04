import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {
	
	@Test
	public void test() {
		Instant start = Instant.now(); //java8中获取当前的时间
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculator(0L,1000000000L);
		pool.invoke(task);
		Instant end = Instant.now();
		System.out.println("耗费时间"+Duration.between(start, end).toMillis());//计算两个时间的时间差
	}
	
	@Test
	//java8并发流
	public void test1(){
		Instant start = Instant.now();
		LongStream.rangeClosed(0l, 1000000000L)
		.parallel()//通过并发的方式
		//.sequential()//顺序流，并发流和顺序流可以切换
		.reduce(Long::sum);//reduce归集，将流中的元素重复处理生成一个新值
		Instant end = Instant.now();
		System.out.println("耗费时间"+Duration.between(start, end).toMillis());
		
	}
}
