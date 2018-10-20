package ng.shoppi.testing.dashboard.config;

import io.jmnarloch.spring.request.correlation.api.RequestCorrelationInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tycoon on 4/22/16.
 */
@Configuration
public class RequestCorrelationConfig {
  @Bean
  public RequestCorrelationInterceptor correlationLoggingInterceptor() {
    return new RequestCorrelationInterceptor() {
      @Override
      public void afterCorrelationIdSet(String correlationId) {
        MDC.put("correlationId", correlationId);
      }

      @Override
      public void cleanUp(String correlationId) {
        MDC.put("correlationId", "");
      }
    };
  }
}
