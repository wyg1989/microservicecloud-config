import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import entity.Employee;
import entity.Employee.Status;

public class TestStream {
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);
	@Test
	public void test() {
		PrintStream ps = System.out;
		List<Employee> list = new ArrayList<>();
		Stream<Employee> stream = emps.stream();
		stream.filter(e->e.getAge()>35)
		.limit(1)
		.sorted()
		.forEach(ps::println);
		
	}
	@Test
	public void test1() {
		Employee[] emp = new Employee[10];
		Stream stream = Arrays.stream(emp);
		
		Stream<String> stream1 = Stream.of("aa","bb");
		stream1.filter(e->e.length()>1)
		.forEach(System.out::println);
		
	}
	//迭代
	@Test
	public void test2() {
		//创建无限流Stream.iterate(0, x->x+2
		Stream<Integer> stream = 
				
				Stream.iterate(0, x->x+2);
		stream
		.limit(10)
		.forEach(System.out::println);
	}
	//生成
	@Test
	public void test3() {
		Stream<Double> stream = Stream.generate(()->Math.random());
		stream.forEach(System.out::println);
	}
}
