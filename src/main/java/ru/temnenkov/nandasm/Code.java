package ru.temnenkov.nandasm;

public class Code {

    public Asm translate(Cmd cmd) {
        if (cmd instanceof Acmd) {
            return translate((Acmd)cmd);
        }
        return translate((Ccmd)cmd);
    }

    public Asm translate(Acmd acmd) {
        String part = String.format("%15s",
                Integer.toBinaryString(acmd.getValue())).replaceAll(" ", "0");
        return new Asm("0" + part);
    }

    public Asm translate(Ccmd s) {

        String comp = "0000000";
        switch (s.getComp()) {
            case "0":
                comp = "0101010";
                break;
            case "1":
                comp = "0111111";
                break;
            case "-1":
                comp = "0111010";
                break;
            case "D":
                comp = "0001100";
                break;
            case "A":
                comp = "0110000";
                break;
            case "!D":
                comp = "0001101";
                break;
            case "!A":
                comp = "0110001";
                break;
            case "-D":
                comp = "0001111";
                break;
            case "-A":
                comp = "0110011";
                break;
            case "D+1":
                comp = "0011111";
                break;
            case "A+1":
                comp = "0110111";
                break;
            case "D-1":
                comp = "0001110";
                break;
            case "A-1":
                comp = "0110010";
                break;
            case "D+A":
                comp = "0000010";
                break;
            case "D-A":
                comp = "0010011";
                break;
            case "A-D":
                comp = "0000111";
                break;
            case "D&A":
                comp = "0000000";
                break;
            case "D|A":
                comp = "0010101";
                break;
            case "M":
                comp = "1110000";
                break;
            case "!M":
                comp = "1110001";
                break;
            case "-M":
                comp = "1110011";
                break;
            case "M+1":
                comp = "1110111";
                break;
            case "M-1":
                comp = "1110010";
                break;
            case "D+M":
                comp = "1000010";
                break;
            case "D-M":
                comp = "1010011";
                break;
            case "M-D":
                comp = "1000111";
                break;
            case "D&M":
                comp = "1000000";
                break;
            case "D|M":
                comp = "1010101";
                break;
        }

        String jmp = "000";
        if (s.getJump() != null) {
            switch (s.getJump()) {
                case "JGT":
                    jmp = "001";
                    break;
                case "JEQ":
                    jmp = "010";
                    break;
                case "JGE":
                    jmp = "011";
                    break;
                case "JLT":
                    jmp = "100";
                    break;
                case "JNE":
                    jmp = "101";
                    break;
                case "JLE":
                    jmp = "110";
                    break;
                case "JMP":
                    jmp = "111";
                    break;

            }
        }

        String dest = "000";
        if (s.getDest() != null) {

            switch (s.getDest()) {
                case "M":
                    dest = "001";
                    break;
                case "D":
                    dest = "010";
                    break;
                case "MD":
                    dest = "011";
                    break;
                case "A":
                    dest = "100";
                    break;
                case "AM":
                    dest = "101";
                    break;
                case "AD":
                    dest = "110";
                    break;
                case "AMD":
                    dest = "111";
                    break;

            }


        }


        return new Asm("111" + comp + dest + jmp);
    }
}
