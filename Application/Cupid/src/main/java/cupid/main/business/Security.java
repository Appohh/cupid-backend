package cupid.main.business;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class Security {

    private final PasswordEncoder passwordEncoder;

    public Security() {
        this.passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    public String hashPassword(String rawPassword) {
        // Generate a random salt
        byte[] salt = generateSalt();

        // Hash and salt the password
        String encodedPassword = passwordEncoder.encode(rawPassword + new String(salt));

        // Return the salt and encoded password as a concatenated string
        return Base64.getEncoder().encodeToString(salt) + ":" + encodedPassword;
    }

    public boolean verifyPassword(String rawPassword, String storedPassword) {
        // Split the stored password into salt and encoded password
        String[] parts = storedPassword.split(":");
        if (parts.length != 2) {
            return false;  // Invalid stored password format
        }

        // Extract salt and encoded password
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        String encodedPassword = parts[1];

        // Verify the password
        return passwordEncoder.matches(rawPassword + new String(salt), encodedPassword);
    }

    private byte[] generateSalt() {
        // Generate a random 16-byte salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}