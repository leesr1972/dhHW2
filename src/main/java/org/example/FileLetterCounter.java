package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public interface FileLetterCounter {
    Map<Character, Long> count(File input) throws FileNotFoundException, InterruptedException;
}
