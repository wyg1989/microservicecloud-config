import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

import Service.CalculateService;

public class testLambda1 {
	private int compare;
	@Test
	public void test() {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				System.out.println("lambda hello");
				
			}
			
		};
		r.run();
		System.out.println("------------------------");
		
		Runnable r1 = ()->System.out.println("lambda hello1");
		r1.run();
	}
	@Test
	public void test2() {
		Consumer<String> con = (x) ->System.out.println(x);
		
		
		con.accept("中华人民共和国威武");
		
	}
	@Test
	public void test3() {
		Comparator<Integer> com = (x,y)->{
			System.out.println("函数式表达式");
			return Integer.compare(x, y);
		};
	}
	
	
	//对一个数进行操作
	
	
	public Integer getValue(Integer num,CalculateService ma) {
		return ma.test(num);
	}
	@Test
	public void test4() {
		Integer value = getValue(2,x->x*x);
		System.out.println(value);
	}
	@Test
	public void test5() {
		Integer value = getValue(300, x->x+400);
		System.out.println(value);
	}
}
