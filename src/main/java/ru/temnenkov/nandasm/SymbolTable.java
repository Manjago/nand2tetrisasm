package ru.temnenkov.nandasm;


import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private int n = 16;
    private final Map<String, Integer> stable = new HashMap<>();

    public SymbolTable() {
        stable.put("SP", 0);
        stable.put("LCL", 1);
        stable.put("ARG", 2);
        stable.put("THIS", 3);
        stable.put("THAT", 4);
        stable.put("R0", 0);
        stable.put("R1", 1);
        stable.put("R2", 2);
        stable.put("R3", 3);
        stable.put("R4", 4);
        stable.put("R5", 5);
        stable.put("R6", 6);
        stable.put("R7", 7);
        stable.put("R8", 8);
        stable.put("R9", 9);
        stable.put("R10", 10);
        stable.put("R11", 11);
        stable.put("R12", 12);
        stable.put("R13", 13);
        stable.put("R14", 14);
        stable.put("R15", 15);
        stable.put("SCREEN", 16384);
        stable.put("KBD", 24576);
    }

    public void put(String s, int value) {
        stable.put(s, value);
    }

    public int resolve(String s) {

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
            if (!stable.containsKey(s)) {
                stable.put(s, n++);
            }
            return stable.get(s);
        }

    }

}
