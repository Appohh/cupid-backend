package cupid.main.config.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
