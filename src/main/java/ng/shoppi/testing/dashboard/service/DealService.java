package ng.shoppi.testing.dashboard.service;

import ng.shoppi.testing.dashboard.domain.Deal;

/**
 *
 * @author babafemi
 */
public interface DealService {
    
    public Deal[] findAll();
    
    public Deal findById(long id);
}
