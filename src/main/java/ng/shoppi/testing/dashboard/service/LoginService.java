package ng.shoppi.testing.dashboard.service;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

/**
 *
 * @author babafemi
 */
public interface LoginService {
    
    DefaultOAuth2AccessToken getToken(String username, String password);
}
