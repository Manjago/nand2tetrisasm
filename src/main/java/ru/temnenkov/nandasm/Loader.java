package ru.temnenkov.nandasm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private final Parser parser;

    public Loader(Parser parser) {
        this.parser = parser;
    }

    public List<Line> load(String input) throws IOException {
        final List<Line> src = new ArrayList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                parse(line, src);
            }
        }

        return src;
    }

    private void parse(String line, List<Line> src) {

        String codeline = parser.code(line);
        if (codeline != null) {
            src.add(new Line(codeline));
        }
    }
}
