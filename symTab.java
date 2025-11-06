/*
Problem Statement:
Design suitable data structures and implement pass-I of a two-pass assembler
for pseudo-machine in Java using object-oriented features.
Implementation should consist of a few instructions from each category and a few assembler directives.
*/

import java.io.*;

class symTab {
    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java SymTab input.txt");
            return;
        }

        FileReader FP = new FileReader(args[0]);
        BufferedReader bufferedReader = new BufferedReader(FP);		

        String line = null;
        int line_count = 0, LC = 0, symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;

        // Data Structures
        final int MAX = 100;
        String SymbolTab[][] = new String[MAX][3];
        String OpTab[][] = new String[MAX][3];
        String LitTab[][] = new String[MAX][2];
        int PoolTab[] = new int[MAX];

        System.out.println("___________________________________________________");

        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] tokens = line.split("\t");

            if (line_count == 0) {
                LC = Integer.parseInt(tokens[2]); // set LC to operand of START
                for (String token : tokens) System.out.print(token + "\t");
                System.out.println();
            } else {
                for (String token : tokens) System.out.print(token + "\t");
                System.out.println();

                if (!tokens[0].equals("")) {
                    // Inserting into Symbol Table
                    SymbolTab[symTabLine][0] = tokens[0];
                    SymbolTab[symTabLine][1] = Integer.toString(LC);
                    SymbolTab[symTabLine][2] = "1";
                    symTabLine++;
                } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                    // Entry into symbol table for declarative statements
                    SymbolTab[symTabLine][0] = tokens[0];
                    SymbolTab[symTabLine][1] = Integer.toString(LC);
                    SymbolTab[symTabLine][2] = "1";
                    symTabLine++;
                }

                if (tokens.length == 3 && tokens[2].charAt(0) == '=') {
                    // Entry of literals into literal table
                    LitTab[litTabLine][0] = tokens[2];
                    LitTab[litTabLine][1] = Integer.toString(LC);
                    litTabLine++;
                } else if (tokens[1] != null) {
                    // Entry of Mnemonic in opcode table
                    OpTab[opTabLine][0] = tokens[1];

                    if (tokens[1].equalsIgnoreCase("START") || tokens[1].equalsIgnoreCase("END")
                            || tokens[1].equalsIgnoreCase("ORIGIN") || tokens[1].equalsIgnoreCase("EQU")
                            || tokens[1].equalsIgnoreCase("LTORG")) {
                        OpTab[opTabLine][1] = "AD";
                        OpTab[opTabLine][2] = "R11";
                    } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                        OpTab[opTabLine][1] = "DL";
                        OpTab[opTabLine][2] = "R7";
                    } else {
                        OpTab[opTabLine][1] = "IS";
                        OpTab[opTabLine][2] = "(04,1)";
                    }
                    opTabLine++;
                }
            }
            line_count++;
            LC++;
        }

        System.out.println("___________________________________________________");

        // SYMBOL TABLE
        System.out.println("\n\nSYMBOL TABLE");
        System.out.println("--------------------------");
        System.out.println("SYMBOL\tADDRESS\tLENGTH");
        System.out.println("--------------------------");
        for (int i = 0; i < symTabLine; i++)
            System.out.println(SymbolTab[i][0] + "\t" + SymbolTab[i][1] + "\t" + SymbolTab[i][2]);
        System.out.println("--------------------------");

        // OPCODE TABLE
        System.out.println("\n\nOPCODE TABLE");
        System.out.println("----------------------------");
        System.out.println("MNEMONIC\tCLASS\tINFO");
        System.out.println("----------------------------");
        for (int i = 0; i < opTabLine; i++)
            System.out.println(OpTab[i][0] + "\t\t" + OpTab[i][1] + "\t" + OpTab[i][2]);
        System.out.println("----------------------------");

        // LITERAL TABLE
        System.out.println("\n\nLITERAL TABLE");
        System.out.println("-----------------");
        System.out.println("LITERAL\tADDRESS");
        System.out.println("-----------------");
        for (int i = 0; i < litTabLine; i++)
            System.out.println(LitTab[i][0] + "\t" + LitTab[i][1]);
        System.out.println("------------------");

        // POOL TABLE
        for (int i = 0; i < litTabLine; i++) {
            if (LitTab[i][0] != null && (i + 1 < litTabLine && LitTab[i + 1][0] != null)) {
                if (i == 0) {
                    PoolTab[poolTabLine] = i + 1;
                    poolTabLine++;
                } else if (Integer.parseInt(LitTab[i][1]) < (Integer.parseInt(LitTab[i + 1][1])) - 1) {
                    PoolTab[poolTabLine] = i + 2;
                    poolTabLine++;
                }
            }
        }

        System.out.println("\n\nPOOL TABLE");
        System.out.println("-----------------");
        System.out.println("LITERAL NUMBER");
        System.out.println("-----------------");
        for (int i = 0; i < poolTabLine; i++)
            System.out.println(PoolTab[i]);
        System.out.println("------------------");

        bufferedReader.close();
    }
}
