package ru.golivkin_sergei.simbirsoft.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadServiceImpl implements DownloadService {

    // Реализуем метод для скачивания страницы
    @Override
    public void download(String addressPage, String path) {
        try {
            URL url = new URL(addressPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String string;
            File page = new File(path);
            FileWriter writer = new FileWriter(page, false);
            while ((string = bufferedReader.readLine()) != null) {
                writer.write(string);
            }
            writer.close();
            bufferedReader.close();
            connection.disconnect();
            System.out.println("Page save.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
