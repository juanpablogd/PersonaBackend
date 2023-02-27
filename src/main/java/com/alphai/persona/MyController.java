package com.alphai.persona;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/holaa")
    public String home() {

        return "Home page";
    }
}
