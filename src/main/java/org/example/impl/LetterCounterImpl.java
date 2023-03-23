package org.example.impl;

import org.example.LetterCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterCounterImpl implements LetterCounter {

    /**
     * Считаем символы в строке.
     */
    @Override
    public synchronized HashMap<Character, Long> countCharsInString(String input) {
        List<Character> listOfChars = input.chars().mapToObj(e -> (char) e).toList();
        HashMap<Character, Long> counterMap = new HashMap<>();
        for (Character listOfChar : listOfChars) {
            if (counterMap.containsKey(listOfChar)) {
                counterMap.put(listOfChar, counterMap.get(listOfChar) + 1);
            } else {
                counterMap.put(listOfChar, 1L);
            }
        }
        return counterMap;
    }
}
