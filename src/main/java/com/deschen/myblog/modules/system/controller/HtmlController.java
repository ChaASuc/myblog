package com.deschen.myblog.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author deschen
 * @Create 2019/7/23
 * @Description
 * @Since 1.0.0
 */
@Controller
@Slf4j
public class HtmlController {

    @RequestMapping("/user")
    public String getHtml() {
        log.info("你好");
        return "index.html";
    }
}
