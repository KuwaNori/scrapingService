package com.scrape.Scraping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {
    @GetMapping(value = "/index")
    public String sayHello(Model model) {
        return "index";
    }
}
