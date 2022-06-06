package by.mmf.krupenko.util;

import by.mmf.krupenko.resource.EncryptorAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The utility is responsible for encrypting
 */
public class Encryptor {
    private static Logger logger = LogManager.getLogger();

    public Encryptor() {}

    public static String encrypt(String value, EncryptorAlgorithm encryptorAlgorithm) {
        String encryptedValue;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(encryptorAlgorithm.algorithm);
            messageDigest.update(value.getBytes(StandardCharsets.UTF_8));
            byte[] bytesEncoded = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, bytesEncoded);
            encryptedValue = bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException in encrypt(" + value + ", " + encryptorAlgorithm.algorithm + ")", e);
            encryptedValue = Integer.toString(value.hashCode());
        }
        return encryptedValue;
    }
}
