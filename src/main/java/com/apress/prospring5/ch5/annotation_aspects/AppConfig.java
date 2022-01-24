package com.apress.prospring5.ch5.annotation_aspects;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch5.annotation_aspects"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
