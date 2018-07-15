package com.contguard.track.main;

import com.contguard.track.config.Config;
import com.contguard.track.dao.TelemetryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackageClasses = Config.class)
@EnableAutoConfiguration
public class Main implements CommandLineRunner {

    @Autowired
    Runner runner;


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        ApplicationContext ctx = SpringApplication.run(Main.class, args);
 //       Runner runner = ctx.getBean(Runner.class);
        runner.run(args[0], args[1], args[2]);
        System.exit(0);
    }
}
