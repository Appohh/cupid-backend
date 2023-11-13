package cupid.main.business.impl;

import cupid.main.business.service.VerifyService;
import cupid.main.domain.Entity.VerifyToken;
import cupid.main.domain.adapter.VerifyAdapter;
import cupid.main.domain.other.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerifyServiceImpl implements VerifyService {
    MailService mailService;
    VerifyAdapter verifyAdapter;

    @Override
    public VerifyToken CreateToken() {
        return verifyAdapter.CreateToken();
    }

    @Override
    public boolean MailToken(String email, String url, VerifyToken token) {
        return mailService.SendMail(email, "", "Verify your email", "Here is your url: ...");
    }

    @Override
    public boolean TokenValid(String token) {
        return verifyAdapter.TokenValid(token);
    }

    @Override
    public boolean VerifyToken(String token) {
        return verifyAdapter.VerifyToken(token);
    }
}
