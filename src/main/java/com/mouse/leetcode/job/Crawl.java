package com.mouse.leetcode.job;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

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
    static final String DIR = "../dp/";
//    static final String DIR = "/Users/gongchangyou/Nutstore Files/.symlinks/坚果云/code/leetcode/dp";
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
        var mainDriver = driver.switchTo().window(mainHandler);

        var i = 10;
        while (i > 0) {
            Thread.sleep(5000L);
            //遍历当前页面并在新标签页打开所有的题目
            var problemList = mainDriver.findElements(By.cssSelector("tr > td:nth-child(2) > div > div > div > a"));
            //            problemList = problemList.subList(0, 3);//test
            for (val problem : problemList) {
                val url = problem.getAttribute("href");
                log.info("url={}", url);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.open(\"" + url + "\");");

                Thread.sleep(5000L);
                //遍历所有的标签页 读取题目内容和题解

                driver.getWindowHandles().forEach(windowHandler -> {
                    if (!windowHandler.equals(mainHandler)) {
                        val driverTmp = driver.switchTo().window(windowHandler);
                        try {
                            //读取题目内容
                            val problemElement = driverTmp.findElement(By.id("question-detail-main-tabs"));
                            val problemText = problemElement.getText();
                            log.info("problemContent text= {}", problemText);
                            //保存 题解
                            driverTmp.findElement(By.cssSelector("#question-detail-main-tabs > div.css-eminw3-TabViewHeader.e16udao1 > div > div:nth-child(3)")).click();
                            try {
                                Thread.sleep(5000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            driverTmp.findElement(By.cssSelector("#question-detail-main-tabs > div.css-fwb2av-layer1.css-12hreja-TabContent.e16udao5 > div > div.css-qcz676-ArticleListWrapper.e1cbkjwn0 > div:nth-child(1) > div > div.css-40k3xe-Header.e19miya84 > h3 > a")).click();
                            try {
                                Thread.sleep(5000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            val solution = driverTmp.findElement(By.cssSelector("#lc-home > div > div.main__1pRE > div.css-wfkj6v-Content.e1aolq224 > div > div.css-1gcn2k5-RightContainer.e1aolq221 > div.css-cqyrzo-Container.e1wapfl0 > div > div.css-11urcqv-PageWrapper.ezv7z181 > div > div:nth-child(1) > div.css-1jiyb8u-ContentContainer.e1ak08xt0")).getText();

                            val urlList = driverTmp.getCurrentUrl().split("/");
                            val filename = urlList[urlList.length - 1];
                            val file = new File(DIR + filename + ".txt");
                            if (!file.exists()) {
                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            FileWriter fw = new FileWriter(file.getAbsoluteFile());
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write("题目: \n" + problemText + "\n");
                            bw.write("答案: \n" + solution + "\n");
                            bw.close();

                        } catch (Exception e) {
                            log.error("url={}", driverTmp.getCurrentUrl(), e);
                        } finally {
                            driverTmp.close();
                        }
                    }
                });
                driver.switchTo().window(mainHandler);
            }

            mainDriver = driver.switchTo().window(mainHandler);
            val nextPage = mainDriver.findElement(By.cssSelector("#lc-content > div > div.css-1ezka95-TableContainer.ermji1u1 > div > section > div > div.css-d8889y-antdPaginationOverride-layer1-dropdown-layer1-hoverOverlayBg-layer1-card-layer1-layer0 > div > div > div > ul > li.ant-pagination-next"));
            nextPage.click();

            i--;
        }
//        val problemList = driver.findElementsByCssSelector("#lc-content > div > div.css-1ezka95-TableContainer.ermji1u1 > div > section > div > div.css-d8889y-antdPaginationOverride-layer1-dropdown-layer1-hoverOverlayBg-layer1-card-layer1-layer0 > div > div > div > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > div");
//        log.info("problemList={}", problemList);
//        problemList.forEach(problem -> {
//            problem.click();
//        });
        log.info("args = {}", args);
    }
}
