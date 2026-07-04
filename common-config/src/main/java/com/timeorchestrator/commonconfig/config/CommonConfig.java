package com.timeorchestrator.commonconfig.config;

import com.timeorchestrator.commonconfig.bean.InstallOpenTelemetryAppender;
import com.timeorchestrator.commonconfig.filter.TraceIdFilter;
import io.micrometer.tracing.Tracer;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Common configurations
 */
@AutoConfiguration
public class CommonConfig {
    @Bean
    @ConditionalOnMissingBean
    public InstallOpenTelemetryAppender installOpenTelemetryAppender(OpenTelemetry openTelemetry) {
        return new InstallOpenTelemetryAppender(openTelemetry);
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceIdFilter traceIdFilter(Tracer tracer) {
        return new TraceIdFilter(tracer);
    }
}
