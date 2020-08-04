

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Service.employeeService;
import Service.impl.employeeImpl;
import Service.impl.employeeSalary;
import entity.Employee;
import entity.Employee.Status;

public class Testlambda {
	
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);


	//需求：获取公司中年龄小于 35 的员工信息
	public List<Employee> filterEmployeeAge(List<Employee> emps){
		List<Employee> list = new ArrayList<>();
		
		for (Employee emp : emps) {
			if(emp.getAge() <= 35){
				list.add(emp);
			}
		}
		
		return list;
	}
	//查询年龄大于35岁的员工
	@Test
	public void test() {
		List<Employee> list = filterEmployeeAge(emps);
		System.out.println(list);
	}
	//优化方式一：策略设计模式
	public List<Employee> filterEmployee(List<Employee> emps, employeeService<Employee> mp){
		List<Employee> list = new ArrayList<>();
		for (Employee employee : emps) {
			if(mp.test(employee)) {
				list.add(employee);
			}
		}
		return list;
		
	}
	@Test
	public void test1() {
		List<Employee> list = filterEmployee(emps,new employeeImpl());
		System.out.println(list);
	}
	@Test
	public void test2() {
		List<Employee> list = filterEmployee(emps,new employeeSalary());
		System.out.println(list);
	}
	//优化方式二：匿名内部类
	@Test
	public void test3() {
		List<Employee> list = filterEmployee(emps,new employeeImpl() {

			@Override
			public boolean test(Employee t) {

				return t.getAge()>35;
			}
			
		});
		System.out.println(list);
	}
	//优化方式三：lambda表达式
	@Test
	public void test4() {
		/*
		 * List<Employee> list = filterEmployee(emps, e->e.getAge()>35);
		 * System.out.println(list);
		 * System.out.println("--------------------------------");
		 */
		List<Employee> aaa = filterEmployee(emps, (e)->e.getAge()>35);
		System.out.println(aaa);
	}
	@Test
	public void test5() {
		/*
		 * List<Employee> list1 = filterEmployee(emps, a->a.getSalary()>6000);
		 * System.out.println(list1);
		 */
		
		List<Employee> list = filterEmployee(emps, e->e.getAge()>35);
	}
	//优化方式四：Sream API
	@Test
	public void test6() {
		emps.stream().filter(e->e.getAge()>35).forEach(System.out::println);
		emps.stream().filter((e)->e.getSalary()>6000).forEach(System.out::print);
	}
	@Test
	public void test7() {
		emps.stream().filter(e->e.getAge()>35).sorted().limit(3).forEach(System.out::println);
		emps.stream()
		.filter(e->e.getAge()>35)
		.limit(1)
		.sorted()
		.forEach(System.out::println);
	}
	
	
	
}
