package com.timeorchestrator.commonconfig.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;

/**
 * Configuration for context propagation
 */
@AutoConfiguration
@ConditionalOnMissingBean(ContextPropagatingTaskDecorator.class)
public class ContextPropagationConfig {
    @Bean
    ContextPropagatingTaskDecorator contextPropagatingTaskDecorator() {
        return new ContextPropagatingTaskDecorator();
    }
}
