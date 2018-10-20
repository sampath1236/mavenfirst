package ng.shoppi.testing.dashboard.client;

import java.util.logging.Logger;
import ng.shoppi.testing.dashboard.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author babafemi
 */
@Service
public class LoginClient implements LoginService {

    private static final Logger logger = Logger.getLogger(LoginClient.class.getName());

    @Value("${shopping.testing.dashboard.base-url}")
    private String baseUrl;

    private String loginUrl;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public DefaultOAuth2AccessToken getToken(String username, String password) {
        try {
            DefaultOAuth2AccessToken result = dologin(username, password);
            return result;
        } catch (HttpClientErrorException ex) {
            logger.info(ex.getMessage());
            return null;
        }
    }

    /**
     * Prepares the necessities for authenticating a user by making a post request to auth/login endpoint via
     * RestTemplate The necessities consist of the request Entities which are the HTTP headers involved and
     * the LoginBean Object
     * @param user The user that wants to perform login action.
     */
    private HttpEntity prepareRequest(String username, String password) {
        //necessities needed for the request of the endpoint to fetch accesstoken
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", username);
        map.add("password", password);

        // specify the get request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    /**
     * @return Returns true for successful Authentication and false for otherwise.
     * @throws HttpClientErrorException An Exception due to 401 unauthorized.
     */
    public DefaultOAuth2AccessToken dologin(String username, String password) throws HttpClientErrorException {
        loginUrl = baseUrl + "/auth/login";

        HttpEntity<MultiValueMap<String, String>> request = this.prepareRequest(username, password);

        logger.info("baseurl:" + baseUrl);
        logger.info("loginUrl:" + loginUrl);
        
        DefaultOAuth2AccessToken token = restTemplate.postForObject(loginUrl, request, DefaultOAuth2AccessToken.class);
        logger.info("token:" + token.getValue());
        return token;
    }
}
