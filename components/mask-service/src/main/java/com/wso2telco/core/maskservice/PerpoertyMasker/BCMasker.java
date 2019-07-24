package com.wso2telco.core.maskservice.PerpoertyMasker;

import com.wso2telco.core.maskservice.PropertyMaskable;



import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class BCMasker implements PropertyMaskable {
    @Override
    public String encryptProperty(String property) throws Exception {

        String maskedId = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec("1111"));
            maskedId = Base64.getEncoder().encodeToString(cipher.doFinal(property.getBytes()));
            System.out.println("BCMasker maskedId "+ maskedId);

           
        } catch (Exception e) {
           // log.error("Error while encrypting.");
            throw e;
        }
        return maskedId;
    }

    private static SecretKeySpec getSecretKeySpec(String secretKey) throws Exception {
        MessageDigest sha = null;
        SecretKeySpec secretKeySpec = null;
        try {
            byte[] key = secretKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            //log.error("Error while getting  SecretKeySpec.");
            throw e;
        } catch (UnsupportedEncodingException e) {
           // log.error("Error while getting SecretKeySpec.");
            throw e;
        }
        return secretKeySpec;
    }

    @Override
    public String decryptProperty(String property) throws Exception {
        String userId = null;
       // String maskedUserId;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec("1111"));
            userId =new String(cipher.doFinal(Base64.getDecoder().decode(property)));
            System.out.println("encrypted UserId "+userId);
        } catch (Exception e) {
            /*log.warn("Error while decrypting User ID : " +  property + "Possible reasons may be incorrect " +
                    "user mask configuration, configuration changed without proper migration or already un-masked user id");*/
            throw e;
        }
        return userId;
    }
}
