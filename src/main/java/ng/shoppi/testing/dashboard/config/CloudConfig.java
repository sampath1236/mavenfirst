package ng.shoppi.testing.dashboard.config;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static ng.shoppi.testing.dashboard.config.Constants.SPRING_PROFILE_CLOUD;

/**
 * Created by tycoon on 4/6/16.
 */
@Configuration
@Profile(SPRING_PROFILE_CLOUD)
public class CloudConfig {

    /**
     * Defines eureka instance config bean for amazon.
     *
     * @return eureka instance config bean
     */
    @Bean
    @Autowired
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean configBean = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        configBean.setDataCenterInfo(info);
        return configBean;
    }
}
