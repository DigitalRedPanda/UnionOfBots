package com.digiunion.unifiedbots.configuration.asynchrony;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
@EnableAsync(proxyTargetClass = true)
// @EnableCaching
public class AsynchronyConfig {

  @Bean(name = "executor")
  public Executor executor() {
    ThreadPoolTaskExecutor execution = new ThreadPoolTaskExecutor();
    execution.setCorePoolSize(Runtime.getRuntime().availableProcessors() - 1);
    execution.setMaxPoolSize(Runtime.getRuntime().availableProcessors() - 1);
    execution.setQueueCapacity(100);
    execution.setThreadNamePrefix("command-");
    execution.initialize();
    return execution;
  }
}