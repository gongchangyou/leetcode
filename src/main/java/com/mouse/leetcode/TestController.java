package com.mouse.leetcode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/12/16 5:43 下午
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("")
    String getDslUrlByName() {
        return "xxx";
    }
}
