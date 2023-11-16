package cupid.main.business.impl;

import cupid.main.business.service.VerifyService;
import cupid.main.domain.Entity.VerifyToken;
import cupid.main.domain.adapter.VerifyAdapter;
import cupid.main.domain.other.MailService;
import lombok.AllArgsConstructor;
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
        String emailMessage = "<html>"
                + "<head><title>" + "Cupid - verify email" + "</title></head>"
                + "<body>"
                + "<p>" + "Hey, thank you for signing up to Cupid!" + "</p>"
                + "<p>" + "To complete the registering we first need to verify your email." + "</p>"
                + "<p><a href=\"" + url + "\">" + "Click here to verify!" + "</a></p>"
                + "<p>" + "Best regards, Cupid." + "</p>"
                + "</body>"
                + "</html>";

        return mailService.SendMail(email, "cupid.verify@gmail.com", "Cupid - Verify email", emailMessage);
    }

    //Check if token is valid
    @Override
    public boolean TokenValid(String token) {
        return verifyAdapter.TokenValid(token);
    }

    //Make token verified
    @Override
    public boolean VerifyToken(String token) {
        return verifyAdapter.VerifyToken(token);
    }

    @Override
    public Integer checkVerificationStatus(String token) {
        return verifyAdapter.checkVerificationStatus(token);
    }
}
