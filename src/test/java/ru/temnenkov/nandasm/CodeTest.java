package ru.temnenkov.nandasm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodeTest {

    private Code code;

    @Before
    public void setUp() throws Exception {
        code = new Code();
    }

    @Test
    public void translate() {
        Asm asm = code.translate(new Acmd(1));
        assertEquals(new Asm("0000000000000001"), asm);
    }
}