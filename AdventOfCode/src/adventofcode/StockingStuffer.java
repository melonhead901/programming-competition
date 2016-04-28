package adventofcode;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class StockingStuffer {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            processCase(messageDigest, scanner.nextLine());
        }

    }

    private static void processCase(MessageDigest messageDigest, String str) throws UnsupportedEncodingException {
        int num = 0;
        String result = "";
        do {
            final String strToHash = str + String.valueOf(num);
            byte[] bytes = messageDigest.digest(strToHash.getBytes("UTF-8"));
            result = getHex(bytes);
            if (num % 100000 == 0) {
                System.out.println(strToHash + " hashes to " + result);
            }
            num++;
        } while (!result.startsWith("000000"));
        System.out.println(num - 1);
    }

    private static String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

}
