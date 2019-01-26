package ru.temnenkov.nandasm;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Outer {
    public void out(String outname, List<Asm> asmdata) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                outname
        ), StandardCharsets.UTF_8))) {
            for(Asm asm : asmdata) {
                bufferedWriter.write(asm.getValue());
                bufferedWriter.newLine();
            }
        }
    }
}
