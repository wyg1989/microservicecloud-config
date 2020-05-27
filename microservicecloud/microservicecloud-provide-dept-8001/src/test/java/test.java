import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.csii.springcloud.service.TGoodInfoService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

	@Autowired
	private TGoodInfoService tGoodInfoService;

	@Test
	public void test11() {

		Map<String, String> map = new HashMap();
		map.put("code", "apple");
		map.put("buys", "3");
		tGoodInfoService.updateAmount(map);
	}

	/*
	 * @Override public void run() {
	 * System.out.println("线程编号========="+Thread.currentThread().getId());
	 * Map<String, String> map = new HashMap(); map.put("code", "apple");
	 * map.put("buys", "3"); tGoodInfoService.updateAmount(map);
	 * 
	 * synchronized (map) {
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
}
