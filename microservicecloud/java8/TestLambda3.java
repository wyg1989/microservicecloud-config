import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import entity.Employee;

/**
 * 测试方法引用
 * 方法引用：传递给lambda的操作，再lambda体中已经有方法实现了，
 * 可以使用方法引用 
 * 前提是实现的抽象方法的参数列表和返回值类型必须和方法引用的
 * 方法的参数列表和返回值类型一直
 * 方法引用使用符号:: ,并且方法引用中的方法后面的()可以省略
 * @author wyg17
 *
 */
public class TestLambda3 {
	
	@Test
	public void test() {
		PrintStream ps = System.out;
		Consumer<String> con = x-> ps.println(x);
		con.accept("sdfsdf");
		PrintStream ps1 = System.out;
		Consumer<String> con1 = ps1::println;
		con1.accept("sdfsdf1");
	}
	//方法::实例方法
	@Test
	public void test1() {
		Employee emp = new Employee();
		Supplier<Integer> su =emp::getAge;
		Integer string = su.get();
		System.out.println(string);
		Supplier<String> su1 = emp::getName;
		String string2 = su1.get();
		System.out.println(string2);
	}
	//类::静态方法
	@Test
	public void test2() {
		Comparator<Integer> com =(x,y)->Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;
		System.out.println(com1.toString());
		
	}
	//类::实例方法
	@Test
	public void test3() {
		BiPredicate<String, String> bp = (x,y)->x.equals(y);
		
		BiPredicate<String, String> bp1 = String::equals;
	}
	//构造器引用
	@Test
	public void test4() {
		Supplier<Employee> sp = ()->new Employee();
		Employee employee = sp.get();
		System.out.println(employee);
		Supplier<Employee> sp1 = Employee::new;
		Function<Integer,Employee> sp2 = Employee::new;
		Employee emp = sp2.apply(101);
		System.out.println(emp);
		//构造器引用：lambda体中调用方法的参数列表和函数式接口中抽象方法的参数列表一致
		
	}
	//数组引用
	@Test
	public void test5() {
		Function<Integer, String[]> fn = (x)->new String[x];
		String[] str = fn.apply(10);
		System.out.println(str.length);
		Function<Integer, String[]> fn1 = String[]::new;
		String[] apply = fn1.apply(11);
		System.out.println(apply.length);
	}
}
