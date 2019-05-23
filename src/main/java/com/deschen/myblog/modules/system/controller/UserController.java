package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.modules.system.service.ArticleDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ArticleDtoService articleDtoService;

//    @GetMapping
}
