package ru.temnenkov.nandasm;

import lombok.Data;
import lombok.Value;

@Value
public class Ccmd implements Cmd {
    private String comp;
    private String dest;
    private String jump;
}
