package com.mouse.leetcode;

import com.mouse.leetcode.dp.IlPe0qService;
import com.mouse.leetcode.dp.Matrix;
import com.mouse.leetcode.dp.MinCostClimbingStairs;
import com.mouse.leetcode.dp.SoupServings;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class MatrixTests {

	@Resource
	Matrix matrix;


	@Test
	void matrix() {
		int[][] dp = new int[3][3];
		dp[0][0] = 1;
		dp[0][1] = 1;
		dp[0][2] = 0;

		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;

		dp[2][0] = 1;
		dp[2][1] = 1;
		dp[2][2] = 1;

		val r = matrix.updateMatrix(dp);

		log.info("r={}", r);
	}
}
