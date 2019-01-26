package ru.temnenkov.nandasm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private final Parser parser = new Parser();
    private final SymbolTable stable = new SymbolTable();
    private final  Code code = new Code();




    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run(args[0]);
    }

    public Main() {
        parser.setStable(stable);
    }

    public void run(String input) throws IOException {

        final Loader loader = new Loader(parser);
        List<Line> src = loader.load(input);
        List<Line> stripped = firstpass(src);
        List<Cmd> translated = secondpass(stripped);
        List<Asm> asm = doasm(translated);

        final Outer outer = new Outer();
        FileParts fileParts = FileParts.parse(input);
        outer.out(fileParts.getName() + ".hack", asm);
    }

    private List<Asm> doasm(List<Cmd> translated) {

        List<Asm> res = new ArrayList<>();

        for(Cmd cmd : translated) {
            res.add(code.translate(cmd));
        }

        return res;
    }

    private List<Cmd> secondpass(List<Line> src) {

        List<Cmd> res = new ArrayList<>();

        for(Line line : src) {
            Acmd acmd = parser.acmd(line.getRaw());
            if (acmd != null) {
                res.add(acmd);
            } else {
                Ccmd ccmd = parser.ccmd(line.getRaw());
                res.add(ccmd);
            }
        }

        return res;
    }

    private List<Line> firstpass(List<Line> src) {
        List<Line> res = new ArrayList<>();
        for(Line line : src) {
            String label = parser.label(line.getRaw());
            if (label != null) {
                stable.put(label, res.size());
            } else {
                res.add(line);
            }
        }
        return res;
    }



}
