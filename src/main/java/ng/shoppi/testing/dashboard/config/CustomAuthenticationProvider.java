package ng.shoppi.testing.dashboard.config;

import java.util.ArrayList;
import java.util.logging.Logger;
import ng.shoppi.testing.dashboard.domain.Login;
import ng.shoppi.testing.dashboard.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 *
 * @author babafemi
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class.getName());

    @Autowired
    private LoginService loginService;

    @Autowired
    private Login login;

    @Autowired
    private OAuth2RestTemplate customRestTemplate;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        DefaultOAuth2AccessToken result = loginService.getToken(name, password);

        if (result != null) {
            customRestTemplate.getOAuth2ClientContext().setAccessToken(result);
            logger.info("Successful authentication!");
            logger.info(customRestTemplate.getAccessToken().getValue());
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        }

        logger.info("Login fail!");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
