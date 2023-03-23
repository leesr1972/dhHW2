package org.example.impl;

import org.example.FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Разделяем файл на List строк.
 */
public class FileReaderImpl implements FileReader {
    @Override
    public synchronized List<String> readLines(File file) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
        return bufferedReader.lines().collect(Collectors.toList());
    }
}
