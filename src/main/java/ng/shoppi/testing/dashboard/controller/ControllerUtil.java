package ng.shoppi.testing.dashboard.controller;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author diji
 */
public class ControllerUtil {

    public static void signout(HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
    }
}
