package com.jdreyes.webapi.prestamos.service.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class AppProperties {
    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @Value("${cypher.enabled}")
    private boolean cypherEnabled;

    @Value("${application.interesrate}")
    private Integer interesrate;
}
