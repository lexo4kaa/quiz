package by.mmf.krupenko.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The utility is responsible for encrypting passwords
 */
public class PasswordEncryptor {
    private static Logger logger = LogManager.getLogger();
    private static final String ENCRYPTOR_ALGORITHM = "SHA-1";

    public PasswordEncryptor() {}

    public String encryptPassword(String password) {
        String encryptedPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTOR_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytesEncoded = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, bytesEncoded);
            encryptedPassword = bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException in encryptPassword(" + password + ")", e);
            encryptedPassword = Integer.toString(password.hashCode());
        }
        return encryptedPassword;
    }
}
