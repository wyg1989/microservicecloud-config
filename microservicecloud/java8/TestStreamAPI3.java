import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import entity.Employee;
import entity.Employee.Status;

public class TestStreamAPI3 {
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);
	@Test
	
	public void test() {
		Optional<Integer> op = emps.stream()
		.map((x)->1)
		.reduce((x,y)->Integer.sum(x, y));
		System.out.println(op.get());
		
	}
}
