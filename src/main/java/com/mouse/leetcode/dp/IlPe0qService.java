package com.mouse.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 三角形中最小路径之和
 * https://leetcode-cn.com/problems/IlPe0q/
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/16 11:23 下午
 */
@Slf4j
@Service
public class IlPe0qService {


    /**
     * 时间复杂度：O(n^2)
     *
     * 空间复杂度：O(n^2)
     *
     * @param triangle
     * @return
     */
    //https://leetcode-cn.com/problems/IlPe0q/
    //剑指 Offer II 100. 三角形中最小路径之和
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n]; //到达f[i][j]的最短路径 i行j列
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0); //当我们在第 i 行的最左侧时，我们只能从第 i−1 行的最左侧移动过来。
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i); //当我们在第 i 行的最右侧时，我们只能从第 i−1 行的最右侧移动过来。
        }
        log.info("f={}", f);
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }

    public int minimumTotalON2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        log.info("f={}", f);
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }

    /**
     * 复杂度O(n)
     * @param triangle
     * @return
     */
    public int minimumTotalON(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] = f[0] + triangle.get(i).get(0);
        }
        log.info("f={}", f);
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }


}
