package cupid.main.domain.other;

public interface MailService {
    boolean SendMail(String to, String from, String subject, String message);
}
