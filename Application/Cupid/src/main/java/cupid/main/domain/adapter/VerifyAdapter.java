package cupid.main.domain.adapter;

import cupid.main.domain.Entity.VerifyToken;

public interface VerifyAdapter {
    VerifyToken CreateToken();
    boolean TokenValid(String token);
    boolean VerifyToken(String token);
    int checkVerificationStatus(String token);
}
