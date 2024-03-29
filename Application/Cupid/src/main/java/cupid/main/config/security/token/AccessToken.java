package cupid.main.config.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Integer getUserId();

    Set<Integer> getRoles();
}
