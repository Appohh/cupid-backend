package cupid.main.business;

import cupid.main.config.Security;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityTest {
    /**
     * @verifies hash a password
     * @see Security#hashPassword(String)
     */
    @Test
    public void hashPassword_shouldHashAPassword() throws Exception {
        // Arrange
        String rawPassword = "password123";
        Security security = new Security();

        // Act
        String hashedPassword = security.hashPassword(rawPassword);

        // Assert
        Assertions.assertNotNull(hashedPassword);
        assertTrue(hashedPassword.contains(":"));
    }

    /**
     * @verifies verify a password
     * @see Security#verifyPassword(String, String)
     */
    @Test
    public void verifyPassword_shouldVerifyAPassword() throws Exception {
        // Arrange
        String rawPassword = "password123";
        Security security = new Security();
        String storedPassword = security.hashPassword(rawPassword);

        // Act
        boolean isPasswordValid = security.verifyPassword(rawPassword, storedPassword);

        // Assert
        Assertions.assertTrue(isPasswordValid);
    }
}
