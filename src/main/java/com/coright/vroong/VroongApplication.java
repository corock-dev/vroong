package com.coright.vroong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 스프링 부트 애플리케이션을 실행하는 진입 지점(entry point) 역할을 하는 메인 클래스다.
 * <p>
 * {@link SpringBootApplication} == {@link org.springframework.boot.SpringBootConfiguration}
 *                                + {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration}
 *                                + {@link org.springframework.context.annotation.ComponentScan}
 * <p>
 * {@link SpringBootApplication} 은 스프링 부트 프로젝트에서 제공하는 애너테이션이다.
 * 내부에서는 스프링 프레임워크에서 제공하는 {@link org.springframework.context.annotation.Configuration} 을 포함하고 있다.
 * {@link org.springframework.context.annotation.Configuration} 애너테이션이 정의된 클래스는 자바 설정 클래스(Java config)라고 한다.
 * 자바 설정 클래스는 스프링 부트 애플리케이션을 설정할 수 있으며 별도의 스프링 빈을 정의할 수 있다.
 */
@Slf4j
@SpringBootApplication
public class VroongApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(VroongApplication.class, args);

        Environment env = ctx.getBean(Environment.class);
        String portValue = env.getProperty("server.port");
        log.info("Customized port: {}", portValue);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(name -> log.info("bean name: {}", name));
    }

}
