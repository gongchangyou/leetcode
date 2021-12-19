package com.mouse.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 分汤
 * https://leetcode-cn.com/problems/soup-servings/
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/18 6:47 下午
 */
@Slf4j
@Component
public class SoupServings {
    public double soupServings(int n) {
        n = n / 25 + (n%25 > 0 ? 1 : 0);
        if (n >= 500) return 1.0;
        int x = 0;
        double[][] dp = new double[n+1][n+1]; //dp[i][j] 表示A剩余i B剩余j的概率
        for (int s = 0; s <= 2*n; s++) {
            for (int i = 0; i <= n; i++) {
                int j = s - i;
                if (j < 0 || j > n) continue;
                double ans = 0.0;
                if (i==0) ans=1.0;
                if (i==0 && j==0) ans=0.5;

                if (i > 0 && j >0) {
                    x++;
                    ans = 0.25 * (
                            dp[M(i-4)][j]
                                    + dp[M(i-3)][M(j-1)]
                                    + dp[M(i-2)][M(j-2)]
                                    + dp[M(i-1)][M(j-3)]
                            );
                }
                dp[i][j] = ans;
            }
        }

        log.info("x={}",x);

        return dp[n][n];

    }

    public double soupServings2(int n) {
        n = n / 25 + (n%25 > 0 ? 1 : 0);
        if (n >= 500) return 1.0;
        int x = 0;
        double[][] dp = new double[n+1][n+1]; //dp[i][j] 表示A剩余i B剩余j的概率
        for (int s = 0; s <= n; s++) {
            for (int i = 0; i <= n; i++) {
                int j = s;
                if (j < 0 || j > n) continue;
                double ans = 0.0;
                if (i==0) ans=1.0;
                if (i==0 && j==0) ans=0.5;

                if (i > 0 && j >0) {
                    x++;
                    ans = 0.25 * (
                            dp[M(i-4)][j]
                                    + dp[M(i-3)][M(j-1)]
                                    + dp[M(i-2)][M(j-2)]
                                    + dp[M(i-1)][M(j-3)]
                    );
                }
                dp[i][j] = ans;
            }
        }

        log.info("x={}",x);
        return dp[n][n];

    }

    public int M(int x) { return Math.max(0, x); }

}
