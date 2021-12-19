package com.mouse.leetcode;

import com.mouse.leetcode.dp.IlPe0qService;
import com.mouse.leetcode.dp.MinCostClimbingStairs;
import com.mouse.leetcode.dp.SoupServings;
import com.sun.source.tree.BinaryTree;
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
	IlPe0qService dPService;

	@Resource
	com.mouse.leetcode.dp.SoupServings soupServings;

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

	@Test
	void soupServings() {
		double r = 0.0;
		r = soupServings.soupServings(20);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());
		r = soupServings.soupServings2(20);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());

		r = soupServings.soupServings(50);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());
		r = soupServings.soupServings2(50);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());

		r = soupServings.soupServings(500);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());
		r = soupServings.soupServings2(500);
		log.info("r={} time={} nanoTime={}", r, System.currentTimeMillis(),System.nanoTime());
	}

	@Resource
	com.mouse.leetcode.dp.MinCostClimbingStairs minCostClimbingStairs;

	@Test
	void minCostClimbingStairs() {
		int  r= 0;
		r= minCostClimbingStairs.minCostClimbingStairs(new int[] {
				10, 15, 20
		});
		log.info("r={}", r);

		r= minCostClimbingStairs.minCostClimbingStairs(new int[] {
				1, 100, 1, 1, 1, 100, 1, 1, 100, 1
		});
		log.info("r={}", r);
	}
}
