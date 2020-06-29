package com.schooldevops.springcloud.kube.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "education")
public class PropertiesConfig {

    private String message = "Default Message";

}
