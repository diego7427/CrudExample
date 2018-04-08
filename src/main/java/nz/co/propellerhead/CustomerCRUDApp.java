package nz.co.propellerhead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import nz.co.propellerhead.springboot.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "nz.co.propellerhead.springboot" }) // same as @Configuration
                                                                                // @EnableAutoConfiguration
                                                                                // @ComponentScan
public class CustomerCRUDApp {

    public static void main(final String[] args) {
        SpringApplication.run(CustomerCRUDApp.class, args);
    }
}
