package com.mouse.leetcode.dp;

import lombok.val;
import org.springframework.stereotype.Component;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/23 8:49 下午
 */
@Component
public class ThreeMax {
    public int solution(int[] nums) {
        int N = nums.length;
        int[] remainder = {0,0,0};

        for (int i = 0; i < N; i++) {
            int a,b,c;
            a= remainder[0] + nums[i];
            b= remainder[1] + nums[i];
            c= remainder[2] + nums[i];
            remainder[a%3] = Math.max(remainder[a%3], a);
            remainder[b%3] = Math.max(remainder[b%3], b);
            remainder[c%3] = Math.max(remainder[c%3], c);
        }

        return remainder[0];
    }
}
