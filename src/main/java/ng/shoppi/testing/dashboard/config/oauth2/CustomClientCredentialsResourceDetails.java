package ng.shoppi.testing.dashboard.config.oauth2;

import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author diji
 */
@Component
public class CustomClientCredentialsResourceDetails extends ClientCredentialsResourceDetails {

    private String clientId;

    private String clientSecret;

    private String accessTokenUri;

    private String tokenInfoUri;

    @Override
    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public String getTokenInfoUri() {
        return tokenInfoUri;
    }

    @Override
    public String getGrantType() {
        return "client_credentials";
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
