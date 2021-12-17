package com.mouse.leetcode;

import com.mouse.leetcode.dp.DPService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class LeetcodeApplicationTests {

	@Resource
	com.mouse.leetcode.dp.DPService dPService;

	@Test
	void minimumTotal() {
		ArrayList params = new ArrayList<List<Integer>>(){
			{
				add(new ArrayList<Integer>() {{
					add(2);
				}});
				add(new ArrayList<Integer>() {{
					add(3);
					add(4);
				}});
				add(new ArrayList<Integer>() {{
					add(6);
					add(5);
					add(7);
				}});
				add(new ArrayList<Integer>() {{
						add(4);
						add(1);
						add(8);
						add(3);
					}}
				);
			}};
		int r = dPService.minimumTotal(params);
		log.info("r={}", r);

		int r2 = dPService.minimumTotalON2(params);
		log.info("r2={}", r2);

		int r3 = dPService.minimumTotalON(params);
		log.info("r3={}", r3);
	}

}
