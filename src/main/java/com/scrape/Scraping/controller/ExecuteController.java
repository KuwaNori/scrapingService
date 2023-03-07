package com.scrape.Scraping.controller;

import com.scrape.Scraping.model.ScrapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExecuteController {
    @PostMapping(value = "/execute")
    public String execute(Model model, @RequestParam("url") String url){
        ScrapeService scrapeService = new ScrapeService(url);
        model.addAttribute("yearMonth", scrapeService.getMonth());
        model.addAttribute("result", scrapeService.getAvailableList());
        return "scrapeResult";
    }
}
