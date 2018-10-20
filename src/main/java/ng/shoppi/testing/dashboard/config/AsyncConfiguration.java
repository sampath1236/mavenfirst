package ng.shoppi.testing.dashboard.config;

import ng.shoppi.testing.dashboard.async.ExceptionHandlingAsyncTaskExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by tycoon on 3/22/16.
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {
  private final Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

  @Autowired
  private testingagainDashboardProperties properties;

  @Override
  @Bean(name = "taskExecutor")
  public Executor getAsyncExecutor() {
    log.debug("Creating Async Task Executor");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(properties.getAsync().getCorePoolSize());
    executor.setMaxPoolSize(properties.getAsync().getMaxPoolSize());
    executor.setQueueCapacity(properties.getAsync().getQueueCapacity());
    executor.setThreadNamePrefix("testing-dashboard-executor-");
    return new ExceptionHandlingAsyncTaskExecutor(executor);
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new SimpleAsyncUncaughtExceptionHandler();
  }

}
