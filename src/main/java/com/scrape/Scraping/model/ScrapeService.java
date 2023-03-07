package com.scrape.Scraping.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ScrapeService {
    private static String baseUrl;
    private static String urlWithParam;
    private static String month;
    private static ArrayList<String> availableList = new ArrayList<>();
    public ScrapeService(String url) {
        baseUrl = url;
        urlWithParam = url + "?TDT=202305";
        Elements elements = parseElements();
        createAvailableList(elements);
    }

    public static Elements parseElements() {
        Document content;
        try {
            content = initContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = content.select("td");
        return elements;
    }
    private static Document initContent() throws IOException {
        Document contentDocument = Jsoup.connect(urlWithParam).get();
        return contentDocument;
    }
    public static void createAvailableList(Elements elements) {
        for (Element element: elements) {
            String value = element.text().trim();
            if (value.contains("年")) {
                month = value;
                continue;
            }
            if (value.contains("月")) {
                continue;
            }
            if (!value.isBlank()) {
                availableList.add(value);
            } else {
                availableList.add("-");
            }
        }
    }

    public static ArrayList<String> getAvailableList() {
        return availableList;
    }

    public static String getMonth() {
        return month;
    }
}
