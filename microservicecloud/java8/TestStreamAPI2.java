import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import entity.Employee;
import entity.Employee.Status;

public class TestStreamAPI2 {
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.REST),
			new Employee(103, "王五", 28, 3333.33,Status.FREE),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
	);
	private DoubleSummaryStatistics collect;
	private Long collect2;

	@Test
	public void test1() {
		//allMatch 是否配置所有的元素
		boolean b1 = emps.stream()
		.allMatch((x)->x.getStatus().equals(Status.BUSY));
		System.out.println(b1);
		//anyMatch 检查是否至少有一个元素匹配
		boolean b2 = emps.stream()
		.anyMatch((e)->e.getStatus().equals(Status.BUSY));
		System.out.println(b2);
		//noneMatch 检查是否没有匹配所有的元素
		boolean b3 = emps.stream()
		.noneMatch((x)->x.getStatus().equals(Status.BUSY));
		System.out.println(b3);
		//java8中避免尽量出现空指针异常，所有Optional容器做了封装，
		//如果为空返回一个对应的空对象 op.orElse(other);
		//返回stream中的第一个元素
		Optional<Employee> op = emps.stream()
		.sorted((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()))
		.findFirst();
		System.out.println(op.get());
		//findAny返回stream中的任意一个元素
		//stream()是串行流，按照顺序查询，
		//emps.parallelStream()//并行流，多个线程去找
		Optional<Employee> op1 =emps.stream()
		.filter(e->e.getStatus().equals(Status.REST))
		.findAny();
		System.out.println(op1.get());
	}
	//获取stream中的最小值
	@Test
	public void test2() {
		  Optional<Double> op = emps.stream()
		.map(Employee::getSalary)//map将stream中的元素映射成新的元素map中放的是功能性函数式接口Function
		.min((e1,e2)->Double.compare(e1, e2));//min实在map的基础再进行帅选 方法引用：类：静态方法
		  System.out.println(op.get());
 
	}
	//获取流中元素最大的值
	@Test
	public void test3() {
		
		  Optional<Employee> op = emps.stream()
		  .max((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()));
		  System.out.println(op.get());

	}
	//规约
	@Test
	public void test4() {
		//第一种情况返回结果一定不会为空，有个起始值
		List<Integer> list = Arrays.asList(1,2,3,5,6,7,8,9,10);
		Integer reduce = list.stream()
		.reduce(0, (x,y)->x+y);
		System.out.println(reduce);
		
		//第二种情况可能出现为空的情况，所以返回Optional可以处理为空的情况
		Optional<Double> reduce2 = emps.stream()
		.map(Employee::getSalary)
		.reduce(Double::sum);
		System.out.println(reduce2.get());
	}
	//收集
	
	@Test
	public void test5() {
		//放入到list中
		emps.stream()
		.map(Employee::getName)
		.collect(Collectors.toList())//List 中的元素可能出现重复的情况，如果过滤重复可以用Set
		.forEach(System.out::println);
		System.out.println("-------------------------------");
		//放入到Set中
		emps.stream()
		.map(Employee::getName)
		.collect(Collectors.toSet())
		.forEach(System.out::println);
		System.out.println("------------------------");
		//放入到HashSet中
		HashSet<String> collect = emps.stream()
		.map(Employee::getName)
		.collect(Collectors.toCollection(HashSet::new));
		collect.forEach(System.out::println);
		System.out.println("------------------------");
	
	}
	
	@Test
	public void test6() {
		//工资的平均值
		
		  Double avg = emps.stream()
		  .collect(Collectors.averagingDouble(Employee::getSalary));
		  System.out.println(avg); 
		  //求和 
		  collect = emps.stream()
		  .collect(Collectors.summarizingDouble(Employee::getSalary));
		  System.out.println(collect.getSum());
		 //统计
		collect2 = emps.stream()
		.collect(Collectors.counting());
		System.out.println(collect2);
	}
	@Test
	public void test7() {
		//获取工资最高的员工
		Optional<Employee> op = emps.stream()
		.collect(Collectors.maxBy((e1,e2)->
		Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(op.get());
		//获取员工的最少工资
		Optional<Double> op1 = emps.stream()
		.map(Employee::getSalary)
		.collect(Collectors.minBy(Double::compare));
		System.out.println(op1.get());
	
	}
	@Test
	public void test8() {
		//分组
		//按照状态进行分组
		Map<Status, List<Employee>> collect3 = emps.stream()
		.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(collect3);
	}
	@Test
	public void test9() {
		//多级分组
		Map<Status, Map<String, List<Employee>>> collect3 = emps.stream()
		.collect(Collectors.groupingBy(Employee::getStatus,
				Collectors.groupingBy((x)->{
					if(x.getAge()<=35) {
						return "青年";
					}else if(x.getAge()>35&&x.getAge()<=50) {
						return "壮年";
					}else {
						return "老年";
					}
					
				})));
		System.out.println(collect3);
	}
	@Test
	public void test10() {
		//分片
		//按照工资大于8000分片
		Map<Boolean, List<Employee>> map = emps.stream()
		.collect(Collectors.partitioningBy((x)->x.getSalary()>8000));
		System.out.println(map);
	}
	@Test
	public void test11() {
		DoubleSummaryStatistics dds = emps.stream()
		.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dds.getAverage());
		System.out.println(dds.getCount());
		System.out.println(dds.getMax());
		System.out.println(dds.getSum());
	}
	@Test
	public void test12() {
		//测试连接，将姓名连接后进行收集
		String collect3 = emps.stream()
		.map(Employee::getName)
		.collect(Collectors.joining(","));
		System.out.println(collect3);
	}

}
