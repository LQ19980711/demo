package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注意：@controller不是@RestController，使用@RestController会返回“/index”字符串
 */
@Controller
    public class HelloWord {

    @RequestMapping(value="/login.html")
    public String HelloWorld() {
        return "/pdfHtml";
    }
}
