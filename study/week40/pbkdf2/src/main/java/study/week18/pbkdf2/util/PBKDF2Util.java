package study.week18.pbkdf2.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PBKDF2Util {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int ITERATIONS = 300000; // 횟수
    private static final int KEY_LENGTH = 512; // 키 (비트)
    private static final int SALT_LENGTH = 16; // 솔트 (바이트)

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltByte = Base64.getDecoder().decode(salt);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltByte, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    public static String getRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public static boolean verifyPassword(String password, String salt, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        long beforeTime = System.currentTimeMillis();

        byte[] saltByte = Base64.getDecoder().decode(salt);
        byte[] originalHash = Base64.getDecoder().decode(storedHash);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltByte, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] newHash = skf.generateSecret(spec).getEncoded();

        long afterTime = System.currentTimeMillis();

        System.out.println("검증에 걸린 시간 : " + (afterTime - beforeTime) + "ms");

        return Arrays.equals(newHash, originalHash);
    }
}
