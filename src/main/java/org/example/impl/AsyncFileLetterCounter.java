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
        List<String> strings = fileReader.readLines(input);

        List<HashMap<Character, Long>> list = new ArrayList<>();
        Thread stringCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String string : strings) {
                    list.add(letterCounter.countCharsInString(string));
                }
            }
        });
        stringCounter.start();
        try {
            stringCounter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<Character, Long> totalCounter = new HashMap<>();
        Thread counter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (HashMap<Character, Long> characterLongHashMap : list) {
                    for (Map.Entry<Character, Long> entry : characterLongHashMap.entrySet()) {
                        if (totalCounter.containsKey(entry.getKey())) {
                            totalCounter.put(entry.getKey(), totalCounter.get(entry.getKey()) + entry.getValue());
                        } else {
                            totalCounter.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        });
        counter.start();
        counter.join();
        return totalCounter;
    }
}
