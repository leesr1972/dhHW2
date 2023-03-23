package org.example;

import org.example.impl.AsyncFileLetterCounter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

public class E2ETests {
    @Test
    void async_file_letter_counting_should_return_predicted_count() throws FileNotFoundException, InterruptedException {
        var file = getFile("test.txt");
        var counter = new AsyncFileLetterCounter();

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
    }

    private File getFile(String name) {
        return new File(getResource(name).getPath());
    }
}
