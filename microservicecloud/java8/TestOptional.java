import java.util.Optional;

import org.junit.Test;

import entity.Employee;
import entity.GoldNess;
import entity.Man;

public class TestOptional {
/**
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
	
	@Test
	public void test() {
		//of创建一个optional实例
		Optional<Employee> op = Optional.of(new Employee());
		System.out.println(op.get());
	}
	@Test
	public void test1() {
		//empty创建一个空的optional实例
		Optional<Object> empty = Optional.empty();
		System.out.println(empty.get());
	}
	@Test
	public void test2() {
		//ofNullable 若参数为空，则创建一个空实例，如果参数不为空，船舰一个optional实例
		Optional<Employee> op = Optional.ofNullable(new Employee());
		if(op.isPresent()) {//isPresent判断是否有值
			System.out.println(op.get());
		}
		
	}
	@Test
	public void test3() {
		Optional<GoldNess> goldNess = Optional.ofNullable(new GoldNess("林志玲"));
		Optional<Man> map = Optional.ofNullable(new Man(goldNess));
		String name = getGoldNessName(map);
		System.out.println(name);
	}
	public String getGoldNessName(Optional<Man> man) {
		

		
		return man.orElse(new Man())
			  	.getGodness()
			    .orElse(new GoldNess("苍老师"))
			    .getName();
	}
}
