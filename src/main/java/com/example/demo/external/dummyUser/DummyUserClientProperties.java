package com.example.demo.external.dummyUser;

import com.example.demo.external.operation.ExternalProperties;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.dummy-user")
@ToString(callSuper = true)
public class DummyUserClientProperties extends ExternalProperties {
}
