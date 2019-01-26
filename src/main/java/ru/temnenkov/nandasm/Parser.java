package ru.temnenkov.nandasm;

public class Parser {

    private SymbolTable stable;

    public void setStable(SymbolTable stable) {
        this.stable = stable;
    }

    public String code(String line) {
        String w = line.trim();
        if (w.startsWith("//")) {
            return null;
        }

        int compos = line.indexOf("//");
        if (compos != -1) {
            w = w.substring(0, compos - 3);
        }
        w = w.trim();
        if (!w.isEmpty()) {
            return w;
        }
        return null;
    }

    public String label(String src) {
        if (src.startsWith("(") && src.endsWith(")")) {
            return src.substring(1, src.length() - 1);
        }
        return null;
    }

    public Acmd acmd(String src) {
        if (src.startsWith("@")) {
            return new Acmd(stable.resolve(src.substring(1)));
        }

        return null;
    }

    public Ccmd ccmd(String src) {

        if (src.startsWith("@")) {
            return null;
        }

        String jmp = null;
        String[] tokens = src.split(";");
        if (tokens.length == 2) {
            jmp = tokens[1];
        }


        String dest = null;
        String comp;
        String[] t2 = tokens[0].split("=");
        if (t2.length == 2) {
            dest = t2[0];
            comp = t2[1];
        } else {
            comp = tokens[0];
        }
        return new Ccmd(comp, dest, jmp);

    }
}
