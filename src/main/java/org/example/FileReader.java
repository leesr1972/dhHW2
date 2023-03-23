package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileReader {
    List<String> readLines(File file) throws FileNotFoundException;
}
