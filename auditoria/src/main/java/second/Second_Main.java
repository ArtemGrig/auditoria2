package second;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Second_Main {
    public static void main(String[] args) {
        String targetHash = "2a2375e1171723a0e04a3c49adccb4ec6db86b2f7527db45e0bb84d8d76a9b9d3536d39e01b92d303fc966b36aa73475f9aea541d63f5ad894a50dda63b68a1c";
        String password = crackPassword(targetHash);

        if (password != null) {
            System.out.println("Исходный пароль: " + password);
        } else {
            System.out.println("Исходный пароль не найден");
        }
    }

    public static String crackPassword(String targetHash) {
        for (char c1 = 'a'; c1 <= 'e'; c1++) {
            for (char c2 = 'a'; c2 <= 'e'; c2++) {
                for (char c3 = 'a'; c3 <= 'e'; c3++) {
                    for (char c4 = 'a'; c4 <= 'e'; c4++) {
                        for (char c5 = 'a'; c5 <= 'e'; c5++) {
                            String password = "" + c1 + c2 + c3 + c4 + c5;
                            String hashedPassword = hashPassword(password);

                            if (hashedPassword.equals(targetHash)) {
                                return password;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
