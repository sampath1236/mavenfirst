package ng.shoppi.testing.dashboard;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import ng.shoppi.testing.dashboard.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Created by tycoon on 3/15/16.
 */
@ComponentScan
@SpringBootApplication
public class testingagainDashboardApplication {

    private static final Logger log = LoggerFactory.getLogger(testingagainDashboardApplication.class);

    @Inject
    private Environment env;

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(testingagainDashboardApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t"
                + "Local: \t\thttp://127.0.0.1:{}\n\t"
                + "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));

    }

    /**
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app,
            SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")
                && System.getProperty("spring.profiles.active") == null
                && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE"))

            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
    }

    /**
     * Initializes delivery-service.
     * <p/>
     * Spring profiles can be configured with a program arguments
     * --spring.profiles.active=your-active-profile
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0)
            log.warn("No Spring profile configured, running with default configuration");
        else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
            Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT)
                    && activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD))
                log.error("You have misconfigured your application! "
                        + "It should not run with both the 'dev' and 'prod' profiles at the same time.");
        }
    }
}
