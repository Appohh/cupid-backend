package cupid.main.external;

import cupid.main.domain.other.MailService;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {
    @Override
    public boolean SendMail(String to, String from, String subject, String message) {
        return false;
    }
}
