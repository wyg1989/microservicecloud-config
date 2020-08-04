import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import entity.Employee;
import entity.Employee.Status;

public class TestStreamAPI1 {
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);
	 
	@Test
	public void test() {
		emps.stream()
		.filter(e->e.getSalary()>5000)
		.skip(5)
		.forEach(System.out::println);
	}
	//distinct 根据hashCode和equal进行去重
	@Test
	public void test1() {
		emps.stream()
		.filter(e->e.getSalary()>5000)
		.distinct()
		.forEach(System.out::println);
		}
	
	//映射
	
	@Test
	public void test2() {
		List<String> list = Arrays.asList("aaa","bbb","ccc");
		/*
		 * list.stream() .map((x)->x.toUpperCase()) .forEach(System.out::println);
		 * emps.stream() .map(Employee::getName) //.map((x)->x.getName())
		 * .forEach(System.out::println);
		 */
		
		/*
		 * Stream<Stream<Character>> stream = list.stream()
		 * .map(TestStreamAPI1::filterCharacter); stream.forEach((sm)->{
		 * sm.forEach(System.out::println); });
		 */
		list.stream()
		.flatMap(TestStreamAPI1::filterCharacter)
		.forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<>();
		for (Character character : str.toCharArray()) {
			list.add(character);
		}
		return list.stream();
	}
	@Test
	public void test3() {
		//自然排序Comparable
		List<String> list = Arrays.asList("ccc","aaa","bbb","ddd");
		list.stream()
		.sorted()
		.forEach(System.out::println);
		//定制排序 Compartor 按我们指定的方式排序
		emps.stream()
		.sorted((e1,e2)->{
			if(e1.getAge().equals(e2.getAge())) {
				return e1.getName().compareTo(e2.getName());
			}else {
				return e1.getAge().compareTo(e2.getAge());
			}
		}).forEach(System.out::println);
	}
	
	@Test
	public void test4() {
		
	}
	
}
