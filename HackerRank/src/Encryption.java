import com.google.common.base.Joiner;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Encryption {

    // Complete the encryption function below.
    static String encryption(String s) {

        String stripped = s.replaceAll(" ", "");

        int rows = (int) Math.floor(Math.sqrt(stripped.length()));
        int cols = (int) Math.ceil(Math.sqrt(stripped.length()));
        if ((rows * cols) < stripped.length()) {
            rows++;
        }

        List<String> slices = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            StringBuilder builder = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                int pos = row * cols + col;
                if (pos < stripped.length()) {
                    builder.append(stripped.charAt(pos));
                }
            }
            slices.add(builder.toString());
        }
        return String.join(" ", slices);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = encryption(s);
        System.out.println(result);

        scanner.close();
    }
}

