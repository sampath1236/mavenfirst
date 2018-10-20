package ng.shoppi.testing.dashboard.controller;

import static ng.shoppi.testing.dashboard.controller.util.Common.LAYOUT;
import ng.shoppi.testing.dashboard.domain.Deal;
import ng.shoppi.testing.dashboard.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author babafemi
 */
@Controller
@RequestMapping("/deals")
public class DealController {

    private Logger log = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @GetMapping
    public String getDeals(Model model, @ModelAttribute Deal deals) {
        Deal[] allDeals = dealService.findAll();
        model.addAttribute("deals", allDeals);
        model.addAttribute("page", "deals");
        return LAYOUT;
    }
}
