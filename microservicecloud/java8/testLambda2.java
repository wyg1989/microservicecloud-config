import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import Service.myFunction;
import Service.myFunction2;
import entity.Employee;
import entity.Employee.Status;

public class testLambda2 {
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);
	@Test
	public void test1() {
		Collections.sort(emps, (e1,e2)->{
			if(e1.getAge()==e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			}else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}
	
	public String getValue(String str ,myFunction m) {
		String value = m.getValue(str);
		return value;
		
	}
	
	@Test
	public void test3() {
		String value = getValue("adfds", x->x.toLowerCase());
		System.out.println(value);
		String value2 = getValue("的开发接口的", x->x.substring(2, 4));
		System.out.println(value2);
	}
	public void op(Long a,Long b,myFunction2<Long, Long> m) {
		System.out.println(m.getValue(a,b));
	}
	@Test
	public void test4() {
	 op(200L,300L,(x,y)-> x+y);
	 op(300l,400l,(x,y)-> x*y);
	}
	@Test
	public void test5() {
		happy(3000, (x)->System.out.println("中国人民共和国万岁"));
	}
	//Consumer 消费型函数式接口
	public void happy(double money,Consumer<Double> con) {
		con.accept(money);
	}
	//Supplier 供给型函数式接口
	public List<Integer> give(int num,Supplier<Integer> su) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(su.get());
			
		}
		return list;
	}
	@Test  
	public void test6() {
		List<Integer> give = give(10,()->(int)(Math.random()*100));
			for (Integer integer : give) {
				System.out.println(integer);
			}
	}
	//函数型函数式接口
	//Function<T,R>
	
	public String setHandler(String str,Function<String, String> f) {
		return f.apply(str);
	}
	@Test
	public void test7() {
		String str = setHandler("\t\t\t\tksdfkdsfj skdfjkds", (x)->x.trim());
		String str1 = setHandler("sdfsdfsd", (x)->x.toUpperCase());
		System.out.println(str);
		System.out.println(str1);
	}
	//断言型函数式接口
	
	
	public List<String> justStr(List<String> list ,Predicate<String> pre) {
		List<String> list1 = new ArrayList<>();
		for (String str : list) {
			 if(pre.test(str)) {
				 list1.add(str); 
			 }
		
		}
		return list1;
		
	}
	@Test
	public void test8() {
		List<String> list = Arrays.asList("sdf","dfsdfdsf","as","34dsf");
		for (String string : list) {
			System.out.println(string.length());
		}
		
		
		  List<String> justStr = justStr(list, (x)-> x.length()>4); 
		  for (String string: justStr)
		  { 
			  System.out.println(string);
		}
		 
	}

	
}
