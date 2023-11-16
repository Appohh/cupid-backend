package cupid.main.business.service;

import cupid.main.domain.Entity.VerifyToken;

public interface VerifyService {
    VerifyToken CreateToken();
    boolean MailToken(String email, String url, VerifyToken token);
    boolean TokenValid(String token);
    boolean VerifyToken(String token);
    Integer checkVerificationStatus(String token);
}
