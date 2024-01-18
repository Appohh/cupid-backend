package cupid.main.business.impl;

import cupid.main.business.service.VerifyService;
import cupid.main.domain.Entity.VerifyToken;
import cupid.main.domain.adapter.VerifyAdapter;
import cupid.main.domain.other.MailService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VerifyServiceImplTest {

    @Test
    void testCreateToken() {
        // Arrange
        VerifyAdapter verifyAdapterMock = mock(VerifyAdapter.class);
        VerifyService verifyService = new VerifyServiceImpl(null, verifyAdapterMock);

        // Stubbing for CreateToken method
        when(verifyAdapterMock.CreateToken()).thenReturn(new VerifyToken(1, "actualToken123", LocalDateTime.now().plusHours(1), 0));

        // Act
        VerifyToken result = verifyService.CreateToken();

        // Assert
        assertNotNull(result);
        assertEquals("actualToken123", result.getToken());
    }

    @Test
    void testMailToken() {
        // Arrange
        MailService mailServiceMock = mock(MailService.class);
        VerifyAdapter verifyAdapterMock = mock(VerifyAdapter.class);
        VerifyService verifyService = new VerifyServiceImpl(mailServiceMock, verifyAdapterMock);

        // Stubbing for MailToken method
        when(verifyAdapterMock.CreateToken()).thenReturn(new VerifyToken(1, "actualToken123", LocalDateTime.now().plusHours(1), 0));
        when(mailServiceMock.SendMail(anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean result = verifyService.MailToken("test@example.com", "http://example.com/verify", new VerifyToken(1, "actualToken123", LocalDateTime.now().plusHours(1), 0));

        // Assert
        assertTrue(result);
    }

    @Test
    void testTokenValid() {
        // Arrange
        VerifyAdapter verifyAdapterMock = mock(VerifyAdapter.class);
        VerifyService verifyService = new VerifyServiceImpl(null, verifyAdapterMock);

        // Stubbing for TokenValid method
        when(verifyAdapterMock.TokenValid("validToken")).thenReturn(true);

        // Act
        boolean result = verifyService.TokenValid("validToken");

        // Assert
        assertTrue(result);
    }

    @Test
    void testVerifyToken() {
        // Arrange
        VerifyAdapter verifyAdapterMock = mock(VerifyAdapter.class);
        VerifyService verifyService = new VerifyServiceImpl(null, verifyAdapterMock);

        // Stubbing for VerifyToken method
        when(verifyAdapterMock.VerifyToken("verifiedToken")).thenReturn(true);

        // Act
        boolean result = verifyService.VerifyToken("verifiedToken");

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckVerificationStatus() {
        // Arrange
        VerifyAdapter verifyAdapterMock = mock(VerifyAdapter.class);
        VerifyService verifyService = new VerifyServiceImpl(null, verifyAdapterMock);

        // Stubbing for checkVerificationStatus method
        when(verifyAdapterMock.checkVerificationStatus("testToken")).thenReturn(1);

        // Act
        Integer result = verifyService.checkVerificationStatus("testToken");

        // Assert
        assertNotNull(result);
        assertEquals(1, result);
    }
}