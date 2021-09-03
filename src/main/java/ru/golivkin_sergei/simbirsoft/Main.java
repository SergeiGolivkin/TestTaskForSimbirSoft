package ru.golivkin_sergei.simbirsoft;


import ru.golivkin_sergei.simbirsoft.model.Words;
import ru.golivkin_sergei.simbirsoft.repositories.WordsRepository;
import ru.golivkin_sergei.simbirsoft.service.*;


import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        // Создаем экземпляры классов
        DownloadService downloadService = new DownloadServiceImpl();
        ParseAndCountService parseAndCountService = new ParseAndCountServiceImpl();
        WordsRepository wordsRepositoryJdbc = new WordsRepository();
        Words word = new Words();

        // Создаем сканер для считывание информации с консоли
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите URL-адрес веб-страницы, которую хотите загрузить:" +
                " (например: https://www.simbirsoft.com/ ): ");
        String addressPage = scanner.nextLine(); //Вводим URL-адрес веб-страницы
        System.out.println("Введите путь к каталогу, в котором вы хотите сохранить веб-страницу. " +
                "(Пример ввода: C:\\\\Users\\\\user\\\\Desktop\\\\simbirsoft.html," +
                " где  C:\\\\Users\\\\user\\\\Desktop\\\\ - путь сохранения файла," +
                " simbirsoft - имя файла)");

        String pathFile = scanner.nextLine(); //Вводим путь к сохраняемой странице и ее имя

        downloadService.download(addressPage, pathFile); // Скачиваем и сохраняем страницу по указанному пути

        Map<String, Integer> hashMap = parseAndCountService.parserAndCounter(pathFile); // Парсим страницу

        for (Map.Entry entry : hashMap.entrySet()) {  // Проходим по мап и помещаем в word
            word.setWord(String.valueOf(entry.getKey()));
            word.setWordCount((Integer) entry.getValue());
            //wordsRepositoryJdbc.save(word); // Сохраняем в БД
        }

        Set<String> keys = hashMap.keySet();
        for (String key : keys) {  // Выводим в консоль
            System.out.println(key + " - " + hashMap.get(key));
        }
    }
}
