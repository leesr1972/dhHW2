package org.example.impl;

import org.example.FileLetterCounter;
import org.example.FileReader;
import org.example.LetterCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AsyncFileLetterCounter implements FileLetterCounter {
    @Override
    public Map<Character, Long> count(File input) throws FileNotFoundException, InterruptedException {
        FileReader fileReader = new FileReaderImpl();
        LetterCounter letterCounter = new LetterCounterImpl();

        //Разделяем файл на List строк.
        List<String> strings = fileReader.readLines(input);

        //List с HashMap, в которой посчитаны символы для каждой строки.
        List<HashMap<Character, Long>> list = new ArrayList<>();
        Thread stringCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String string : strings) {
                    //Добавляем результат счета символов для всех строк.
                    list.add(letterCounter.countCharsInString(string));
                }
            }
        });
        stringCounter.start();
        try {
            stringCounter.join(); //Ждем, чтобы посчитались все строки.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Результирущий счетчик.
        Map<Character, Long> totalCounter = new HashMap<>();
        Thread counter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (HashMap<Character, Long> characterLongHashMap : list) {
                    for (Map.Entry<Character, Long> entry : characterLongHashMap.entrySet()) {
                        if (totalCounter.containsKey(entry.getKey())) {
                            //Если в результирующем счетчике уже есть символ, то складываем значение от текущей строки.
                            totalCounter.put(entry.getKey(), totalCounter.get(entry.getKey()) + entry.getValue());
                        } else {
                            //Если в результирующем счетчике нет символа, то создаем элемент с этим символом.
                            totalCounter.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        });
        counter.start();
        counter.join(); //Ждем, чтобы результирующий счетчик завершил свою работу.
        return totalCounter;
    }
}
