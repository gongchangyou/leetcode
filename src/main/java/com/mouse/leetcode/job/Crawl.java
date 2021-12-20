package com.mouse.leetcode.job;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/20 11:53 上午
 */

/**
 * leetcode 爬虫
 * 将网站上的内容抓取到本地，供kindle 查看
 * https://leetcode-cn.com/tag/dynamic-programming/problemset/
 */
@Slf4j
@Service
public class Crawl implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        val driver = new ChromeDriver();

        driver.get("https://leetcode-cn.com/tag/dynamic-programming/problemset/");

        val mainHandler = driver.getWindowHandle();//保留一下 待会儿留着翻页

        Thread.sleep(5000L);
        //遍历当前页面并在新标签页打开所有的题目
        var problemList = driver.findElementsByCssSelector("tr > td:nth-child(2) > div > div > div > a");
        problemList = problemList.subList(0, 3);//test
        problemList.forEach(problem -> {
            try {
                val url = problem.getAttribute("href");
                log.info("url={}", url);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.open(\""+url+"\");");
            } catch(Exception e) {
                log.info("open new tab error ", e);
            }
        });
        Thread.sleep(5000L);
        //遍历所有的标签页 读取题目内容和题解

        driver.getWindowHandles().forEach(windowHandler->{
            if (!windowHandler.equals(mainHandler)) {
                val driverTmp = driver.switchTo().window(windowHandler);
                //TODO 读取题目内容
                val problemContent = driverTmp.findElement(By.cssSelector("question-detail-main-tabs"));
                log.info("problemContent text= {}", problemContent.getText());
                //保存 题解
            }
        });


        val nextPage = driver.findElementByCssSelector("#lc-content > div > div.css-1ezka95-TableContainer.ermji1u1 > div > section > div > div.css-d8889y-antdPaginationOverride-layer1-dropdown-layer1-hoverOverlayBg-layer1-card-layer1-layer0 > div > div > div > ul > li.ant-pagination-next");
        nextPage.click();
//        val problemList = driver.findElementsByCssSelector("#lc-content > div > div.css-1ezka95-TableContainer.ermji1u1 > div > section > div > div.css-d8889y-antdPaginationOverride-layer1-dropdown-layer1-hoverOverlayBg-layer1-card-layer1-layer0 > div > div > div > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > div");
//        log.info("problemList={}", problemList);
//        problemList.forEach(problem -> {
//            problem.click();
//        });
        log.info("args = {}", args);
    }
}
