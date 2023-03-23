package org.example;

import org.example.impl.AsyncFileLetterCounter;
import org.example.impl.FileReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        FileReader st = new FileReaderImpl();
        File file = new File("C:/Users/NbDell/IdeaProjects/DigitalHabits/HW2/dhHW2/src/main/resources/myTest.txt");
       // st.readLines(file).forEach(System.out::println);
        AsyncFileLetterCounter counter = new AsyncFileLetterCounter();
        Map<Character, Long> result = counter.count(file);
        System.out.println(result);
    }
}