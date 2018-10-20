package ng.shoppi.testing.dashboard.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

/**
 *
 * @author diji
 */
public class ActionRunner {

    private static Logger log = LoggerFactory.getLogger(ActionRunner.class);

    public static void runAction(Action action, Model model) {
        runAction(action, model, "Completed", "Action couldn't be completed. Please try again");
    }

    public static void runAction(Action action, Model model, String successMessage, String errorMessage) {
        try {
            action.run();
            model.addAttribute("message", successMessage);
        } catch (Throwable exception) {
            log.error(errorMessage, exception);
            model.addAttribute("error", errorMessage);
        }
    }
}
