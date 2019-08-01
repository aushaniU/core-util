package com.wso2telco.core.maskservice.PerpoertyMasker;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.GenericDomain;
import com.idealista.fpe.config.GenericTransformations;
import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;
import com.wso2telco.core.maskservice.PropertyMaskable;

public class FPEMasker implements PropertyMaskable {
    @Override
    public String encryptProperty(String property) throws Exception{
        String returnedUserId = property;

        String secretKey = "changethischange";
        String tweakKey = "changethis";

        if (secretKey != null && !secretKey.isEmpty()) {
            returnedUserId = encrypt(returnedUserId, secretKey, tweakKey);
        } else {
//            log.error("Error while getting configuration, MSISDN_ENCRIPTION_KEY is not provided");
        }

        return returnedUserId;
    }

    @Override
    public String decryptProperty(String property) {

        String returnedUserId = property;

        String secretKey = "changethischange";
        String tweakKey = "changethis";

        if (secretKey != null && !secretKey.isEmpty()) {
            returnedUserId = decrypt(returnedUserId, secretKey, tweakKey);
        } else {
//            log.error("Error while getting configuration, MSISDN_ENCRIPTION_KEY is not provided");
        }

        return returnedUserId;
    }

    private static String encrypt(String userId, String secretKey, String tweakKey) {

        BasicNums numAlphabet = new BasicNums();
        TextToIntTransformer textToIntTransformer = new GenericTransformations(numAlphabet.availableCharacters());
        IntToTextTransformer intToTextTransformer = new GenericTransformations(numAlphabet.availableCharacters());

        // with default values
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(new GenericDomain(new BasicNums(), textToIntTransformer, intToTextTransformer))
                .withDefaultPseudoRandomFunction(secretKey.getBytes())
                .withDefaultLengthRange()
                .build();

        return formatPreservingEncryption.encrypt(userId, tweakKey.getBytes());
    }


    private static String decrypt(String maskedUserId, String secretKey, String tweakKey) {
        BasicNums numAlphabet = new BasicNums();
        TextToIntTransformer textToIntTransformer = new GenericTransformations(numAlphabet.availableCharacters());
        IntToTextTransformer intToTextTransformer = new GenericTransformations(numAlphabet.availableCharacters());

        // with default values
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(new GenericDomain(new BasicNums(), textToIntTransformer, intToTextTransformer))
                .withDefaultPseudoRandomFunction(secretKey.getBytes())
                .withDefaultLengthRange()
                .build();

        return formatPreservingEncryption.decrypt(maskedUserId, tweakKey.getBytes());
    }

}

class BasicNums implements Alphabet {

    private static final char[] NUM_CHARS = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9' , '0'};

    @Override
    public char[] availableCharacters() {
        return NUM_CHARS ;
    }

    @Override
    public Integer radix() {
        return NUM_CHARS.length;
    }
}
