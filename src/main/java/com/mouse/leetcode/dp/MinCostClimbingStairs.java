package com.mouse.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/19 8:24 下午
 */
@Slf4j
@Component
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        //dp[] 表示到达这一层的最小花费
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1]);
        }

        log.info("dp={}",dp);

        return dp[cost.length];
    }
}
