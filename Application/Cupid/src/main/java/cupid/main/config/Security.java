package cupid.main.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class Security {

    private static final PasswordEncoder passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public Security() {
    }

    public static String hashPassword(String rawPassword) {

        byte[] salt = generateSalt();

        // hash and salt password
        String encodedPassword = passwordEncoder.encode(rawPassword + new String(salt));

        // return the salt and encoded password as a concatenated string
        return Base64.getEncoder().encodeToString(salt) + ":" + encodedPassword;
    }


    public static boolean verifyPassword(String rawPassword, String storedPassword) {

        String[] parts = storedPassword.split(":");
        if (parts.length != 2) {
            return false;  // invalid stored password format
        }

        // extract
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        String encodedPassword = parts[1];

        // verify
        return passwordEncoder.matches(rawPassword + new String(salt), encodedPassword);
    }

    private static byte[] generateSalt() {
        // generate a random 16-byte salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}