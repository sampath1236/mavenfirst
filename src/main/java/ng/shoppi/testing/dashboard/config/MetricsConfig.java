package ng.shoppi.testing.dashboard.config;

import static ng.shoppi.testing.dashboard.config.Constants.SPRING_PROFILE_CLOUD;

import com.codahale.metrics.MetricRegistry;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
import com.blacklocus.metrics.CloudWatchReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

/**
 * Created by tycoon on 4/26/16.
 */
@Configuration
@EnableMetrics(proxyTargetClass = true)
@Profile(SPRING_PROFILE_CLOUD)
public class MetricsConfig extends MetricsConfigurerAdapter {
  private final Logger log = LoggerFactory.getLogger(MetricsConfig.class);

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.application.environment:test}")
  private String environment;

  @Value("${aws.cloudwatch.region:eu-west-1}")
  private String awsCloudWatchRegion;

  @Value("${aws.cloudwatch.reporter.sync-interval:1}")
  private long awsCloudWatchReporterSyncInterval;

  public MetricsConfig() {
  }

  @Override
  public void configureReporters(MetricRegistry metricRegistry) {
    log.info("Registering cloud watch metrics reporter");
    AmazonCloudWatchAsync amazonCloudWatchAsync = new AmazonCloudWatchAsyncClient();
    amazonCloudWatchAsync.setRegion(Region.getRegion(Regions.fromName(awsCloudWatchRegion)));
    amazonCloudWatchAsync.setEndpoint("monitoring."+awsCloudWatchRegion+".amazonaws.com");

    String nameSpace = environment + "." + applicationName;
    CloudWatchReporter cloudWatchReporter =
        new CloudWatchReporter(metricRegistry, nameSpace, amazonCloudWatchAsync);

    registerReporter(cloudWatchReporter).start(awsCloudWatchReporterSyncInterval, TimeUnit.MINUTES);
    log.info("Registered cloud watch metrics reporter");
  }
}
