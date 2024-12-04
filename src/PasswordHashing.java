import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashing {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] salt= generateSalt();
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        String saltString = Base64.getEncoder().encodeToString(salt);
        System.out.println(saltString.length());
        return Base64.getEncoder().encodeToString(hashedPassword) + saltString;
    }

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        md.update(saltBytes);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword) + salt;
    }

    private static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
