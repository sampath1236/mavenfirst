package ng.shoppi.testing.dashboard.controller;

import javax.servlet.http.HttpServletRequest;
import static ng.shoppi.testing.dashboard.controller.util.Common.LAYOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author babafemi
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String getLogin(Model model, HttpServletRequest request) {
        log.debug("Visiting login page");
        log.debug("query string is: " + request.getQueryString());
        if ("error".equals(request.getQueryString()))
            model.addAttribute("error", "Invalid username or password");
        model.addAttribute("page", "login");
        return LAYOUT;
    }
}
