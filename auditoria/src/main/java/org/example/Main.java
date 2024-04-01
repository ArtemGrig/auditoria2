package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final int IV_LENGTH_BYTES = 16;
    private static final int AES_KEY_LENGTH_BYTES = 16;
    private static final int GCM_TAG_LENGTH = 128;

    public static void main(String[] args) {
        try {
            byte[] keyFileContent = Files.readAllBytes(Paths.get("/Users/artem/Downloads/aes.key"));
            byte[] encryptedData = Files.readAllBytes(Paths.get("/Users/artem/Downloads/secret_text.enc"));

            byte[] iv = new byte[IV_LENGTH_BYTES];

            System.arraycopy(keyFileContent, 0, iv, 0, IV_LENGTH_BYTES);

            byte[] aesKeyBytes = new byte[AES_KEY_LENGTH_BYTES];

            System.arraycopy(keyFileContent, IV_LENGTH_BYTES, aesKeyBytes, 0, AES_KEY_LENGTH_BYTES);

            SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmParameterSpec);

            byte[] decryptedData = cipher.doFinal(encryptedData);

            System.out.println(new String(decryptedData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}