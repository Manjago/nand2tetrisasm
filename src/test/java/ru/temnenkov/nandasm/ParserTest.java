package ru.temnenkov.nandasm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
        parser.setStable(new SymbolTable());
    }

    @Test
    public void codeNormal() {
         String w = parser.code("   @R0             ");
         assertEquals("@R0", w);
    }

    @Test
    public void codeCommented() {
         String w = parser.code("   D=M              // D = first number");
         assertEquals("D=M", w);
    }

    @Test
    public void comment() {
         String w = parser.code("// Computes R2 = max(R0, R1)  (R0,R1,R2 refer to RAM[0],RAM[1],RAM[2])");
         assertNull(w);
    }

    @Test
    public void empty() {
         String w = parser.code("");
         assertNull(w);
    }

    @Test
    public void tryLabel() {
        String w = parser.label("(OUTPUT_FIRST)");
        assertEquals("OUTPUT_FIRST", w);
    }

    @Test
    public void tryNotLabel() {
        String w = parser.label("D;JGT");
        assertNull(w);
    }

    @Test
    public void acmdgood() {
        Acmd w = parser.acmd("@23");
        assertEquals(new Acmd(23), w);
    }

    @Test
    public void ccmdgood() {
        Ccmd w = parser.ccmd("D=M;JGT");
        assertEquals(new Ccmd("M", "D", "JGT"), w);
    }

    @Test
    public void ccmdgood2() {
        Ccmd w = parser.ccmd("D=M");
        assertEquals(new Ccmd("M", "D", null), w);
    }

    @Test
    public void ccmdgood3() {
        Ccmd w = parser.ccmd("M");
        assertEquals(new Ccmd("M", null, null), w);
    }


    @Test
    public void acmdbad() {
        Acmd w = parser.acmd("D=M;JGT");
        assertNull(w);
    }

    @Test
    public void ccmdbad() {
        Ccmd w = parser.ccmd("@11");
        assertNull(w);
    }
}