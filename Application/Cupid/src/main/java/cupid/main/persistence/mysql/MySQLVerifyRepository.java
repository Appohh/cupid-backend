package cupid.main.persistence.mysql;

import cupid.main.controller.dto.Handler.custom_exceptions.NotFoundException;
import cupid.main.controller.dto.Handler.custom_exceptions.TokenExpiredException;
import cupid.main.domain.Entity.VerifyToken;
import cupid.main.domain.adapter.VerifyAdapter;
import cupid.main.domain.other.MailService;
import cupid.main.persistence.iJpa.iVerifyJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
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
                .verified(0)
                .build();

         return jpa.save(verifyToken);
    }

    @Override
    public boolean TokenValid(String token) {
        Optional<VerifyToken> tokenFound = Optional.ofNullable(jpa.findVerifyTokenByToken(token));

        if(tokenFound.isEmpty()){
            throw new NotFoundException("Token was not found");
        }

        if(tokenFound.get().getExpires().isBefore(LocalDateTime.now())){
            throw new TokenExpiredException("Token exceeded expiration datetime");
        }
        if(tokenFound.get().getVerified() != 0){
            throw new TokenExpiredException("Token already verified or invalid");
        }

        return true;
    }

    @Override
    @Transactional
    public boolean VerifyToken(String token) {
        return jpa.validateToken(token) > 0;
    }

    @Override
    public int checkVerificationStatus(String token) {
        Optional<VerifyToken> foundToken = Optional.ofNullable(jpa.findVerifyTokenByToken(token));
        if (foundToken.isEmpty()){
            throw new NotFoundException("Token was not found");
        }
        return foundToken.get().getVerified();
    }
}
