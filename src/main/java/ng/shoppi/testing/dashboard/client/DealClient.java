package ng.shoppi.testing.dashboard.client;

import ng.shoppi.testing.dashboard.config.ClientOnlyRestTemplate;
import ng.shoppi.testing.dashboard.domain.Deal;
import ng.shoppi.testing.dashboard.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author babafemi
 */
@Service
public class DealClient implements DealService {

    @Autowired
    private ClientOnlyRestTemplate restTemplate;

    private String baseUrl = "https://kubekwe-onemallbff-dev.shoppi.ng/api/v1";

    @Override
    public Deal[] findAll() {
        return restTemplate.getForObject(baseUrl + "/deals", Deal[].class);
    }

    @Override
    public Deal findById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
