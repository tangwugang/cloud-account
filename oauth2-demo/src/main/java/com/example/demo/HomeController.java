package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author twg
 * @since 2020/10/18
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @RequestMapping
    public String home(){
        return "success";
    }
}
