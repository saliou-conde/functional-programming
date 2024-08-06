/*
 * Copyright © Schweizerische Bundesbahnen SBB, 2024.
 */

package de.arag.functinal.programming.telemetry;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
@ConfigurationProperties(prefix = "alea-server.telemetry")
@Getter
@Setter
public class TelemetryConfig {
    private String exportPath;

    @Bean
    public DefaultDelayedWriter defaultDelayedWriter() {
        return new DefaultDelayedWriter(Paths.get(exportPath));
    }

}
