package ng.shoppi.testing.dashboard.config;

import ng.shoppi.testing.dashboard.config.oauth2.CustomClientCredentialsResourceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

/**
 *
 * @author babafemi
 */
@Component
public class ClientOnlyRestTemplate extends OAuth2RestTemplate {

    private final Logger log = LoggerFactory.getLogger(CustomClientCredentialsResourceDetails.class);

    @Autowired
    public ClientOnlyRestTemplate(CustomClientCredentialsResourceDetails details) {
        super(details);
        setMessageConverters(asList(new MappingJackson2HttpMessageConverter(JsonUtil.getCamelCaseMapper())));
    }
    

}
