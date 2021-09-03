package ru.golivkin_sergei.simbirsoft.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ParseAndCountServiceImpl implements ParseAndCountService {

    // Реализуем метод парсинга страницы и подсчета уникальных слов
    @Override
    public Map<String, Integer> parserAndCounter(String pathFile) {

        Map<String, Integer> wordMap = new HashMap<>();
        try {
            File file = new File(pathFile);
            Document document = Jsoup.parse(file, "UTF-8");
            String string = document.select("a, h1, h2, p, title, div, span").text().toUpperCase();
            string = string.replaceAll("[^a-zA-Zа-яА-Я-]", " ");
            String[] item = string.split("\\s+");
            for (String t : item) {
                if (wordMap.containsKey(t)) {
                    wordMap.put(t, wordMap.get(t) + 1);

                } else {
                    wordMap.put(t, 1);
                }
            }
            return wordMap;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
