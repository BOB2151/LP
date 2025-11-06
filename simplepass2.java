import java.io.*;
import java.util.*;

public class simplepass2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br1 = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("symtab.txt"));
        BufferedReader br3 = new BufferedReader(new FileReader("littab.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("pass2_output.txt"));

        // --- Symbol Table ---
        HashMap<Integer, String> symtab = new HashMap<>();
        String line;
        while ((line = br2.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2)
                symtab.put(Integer.parseInt(parts[0]), parts[1]);
        }

        // --- Literal Table ---
        HashMap<Integer, String> littab = new HashMap<>();
        while ((line = br3.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2)
                littab.put(Integer.parseInt(parts[0]), parts[1]);
        }

        // --- Process Intermediate Code ---
        while ((line = br1.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // DC
            if (line.contains("DL,02")) {
                try {
                    String val = line.substring(line.indexOf("(C,") + 3, line.indexOf(")"));
                    out.write("+ 00 0 " + String.format("%03d", Integer.parseInt(val)) + "\n");
                } catch (Exception e) {
                    out.write("+ 00 0 000\n");
                }
                continue;
            }

            // DS
            if (line.contains("DL,01")) {
                out.write("+ 00 0 000\n");
                continue;
            }

            // IS (Imperative Statements)
            if (line.contains("IS")) {
                try {
                    int start = line.indexOf("IS,") + 3;
                    int end = line.indexOf(")", start);
                    if (end == -1) end = line.length();
                    String opcode = line.substring(start, end);

                    String reg = "0";
                    if (line.contains(")(")) {
                        int pos = line.indexOf(")(");
                        if (pos > 0 && pos - 1 < line.length())
                            reg = line.charAt(pos - 1) + "";
                    }

                    String addr = "000";
                    if (line.contains("(S,")) {
                        int no = Integer.parseInt(line.substring(line.indexOf("(S,") + 3, line.indexOf(")", line.indexOf("(S,"))));
                        addr = symtab.getOrDefault(no, "000");
                    } else if (line.contains("(L,")) {
                        int no = Integer.parseInt(line.substring(line.indexOf("(L,") + 3, line.indexOf(")", line.indexOf("(L,"))));
                        addr = littab.getOrDefault(no, "000");
                    }

                    out.write("+ " + opcode + " " + reg + " " + addr + "\n");
                } catch (Exception e) {
                    out.write("+ 00 0 000\n");
                }
            }
        }

        br1.close();
        br2.close();
        br3.close();
        out.close();

        System.out.println("✅ Pass-II completed successfully!");
        System.out.println("👉 Output written to pass2_output.txt");
    }
}
