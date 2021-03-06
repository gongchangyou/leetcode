package com.mouse.leetcode;

import com.mouse.leetcode.dp.Matrix;
import com.mouse.leetcode.dp.ThreeMax;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ThreeTests {

	@Resource
	ThreeMax threeMax;


	@Test
	void threeMax() {
		int[] dp = {1,2,3,4,4};

		var r = threeMax.solution(dp);
		log.info("r={}", r);

		int[] dp1 = {100, 10, 1, 200, 3, 1};

		r = threeMax.solution(dp1);

		log.info("r={}", r);
	}
}
