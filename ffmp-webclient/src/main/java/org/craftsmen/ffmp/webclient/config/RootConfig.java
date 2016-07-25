package org.craftsmen.ffmp.webclient.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jrtech.templates.services","org.craftsmen.ffmp.webclient.listener"})
public class RootConfig {
}
