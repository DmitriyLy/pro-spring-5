package com.apress.prospring5.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class MessageDigesterConfigDemo {

    @Configuration
    static class MessageDigesterConfig {

        @Bean
        public MessageDigestFactoryBean shaDigest() {
            MessageDigestFactoryBean factoryBean = new MessageDigestFactoryBean();
            factoryBean.setAlgorithmName("SHA1");
            return factoryBean;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest() {
            return new MessageDigestFactoryBean();
        }

        @Bean
        public MessageDigester digester() throws Exception {
            MessageDigester digester = new MessageDigester();
            digester.setDigest1(shaDigest().getObject());
            digester.setDigest2(defaultDigest().getObject());
            return digester;
        }

    }

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);
        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");
        context.close();
    }

}
