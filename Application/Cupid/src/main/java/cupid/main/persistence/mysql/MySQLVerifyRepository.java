package cupid.main.persistence.mysql;

import cupid.main.controller.dto.Handler.custom_exceptions.TokenExpiredException;
import cupid.main.domain.Entity.VerifyToken;
import cupid.main.domain.adapter.VerifyAdapter;
import cupid.main.domain.other.MailService;
import cupid.main.persistence.iJpa.iVerifyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@Profile("mysql")
public class MySQLVerifyRepository implements VerifyAdapter {
    private iVerifyJpa jpa;
    @Autowired
    public MySQLVerifyRepository(iVerifyJpa iVerifyJpa){this.jpa = iVerifyJpa;}
    @Override
    public VerifyToken CreateToken() {
        String token = UUID.randomUUID().toString();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Add 10 minutes
        LocalDateTime expires = currentDateTime.plusMinutes(10);

         VerifyToken verifyToken = VerifyToken.builder()
                .token(token)
                .expires(expires)
                .build();

         return jpa.save(verifyToken);
    }

    @Override
    public boolean TokenValid(String token) {
        VerifyToken tokenFound = jpa.findVerifyTokenByToken(token);
        if(tokenFound.getExpires().isAfter(LocalDateTime.now())){
            throw new TokenExpiredException("Token exceeded expiration datetime");
        }
        if(tokenFound.getVerified() != 0){
            throw new IllegalArgumentException("Token already verified or invalid");
        }

        if(jpa.validateToken(tokenFound.getToken()) < 1) {
            throw new RuntimeException("Token could not be validated");
        }

        return true;
    }

    @Override
    public boolean VerifyToken(String token) {
        if(jpa.findVerifyTokenByToken(token).getVerified() == 1) {
            throw new IllegalArgumentException("Token already verified or invalid");
        }
        return jpa.validateToken(token) > 0;
    }
}
