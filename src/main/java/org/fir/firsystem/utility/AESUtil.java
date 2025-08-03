package org.fir.firsystem.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Component
public class AESUtil {
    private static final String  Algorithm = "AES";

    private final String secretKey ;
    public AESUtil(@Value("${ENCRYPTION_KEY}") String secretKey) {
        this.secretKey = secretKey;
    }
    public   String encryptPlainText(String plainText)throws Exception{
        Cipher cipher = Cipher.getInstance(Algorithm);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }
//    Cipher.getInstance("AES"): Initializes a Cipher object for AES.
//            SecretKeySpec(...): Wraps your secret key bytes into a key object.
//
//            cipher.init(...): Prepares cipher for encryption using the key.
//
//            cipher.doFinal(...): Encrypts the plain text.
//
//            Base64.getEncoder()...: Converts encrypted binary data to a readable Base64 string.
//
//📝 Return: Base64-encoded encrypted string.
public String decryptPlainText(String plainText)throws Exception{
        Cipher cipher = Cipher.getInstance(Algorithm);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(plainText));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
//    Same steps as encryption but DECRYPT_MODE.
//
//    Takes the encrypted text, Base64-decodes it, then decrypts it.
//
//    Converts the resulting bytes back to a normal readable string.
//
//            📝 Return: The original plain text.
}
