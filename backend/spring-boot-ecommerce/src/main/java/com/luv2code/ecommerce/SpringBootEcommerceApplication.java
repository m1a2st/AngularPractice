package com.luv2code.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* Not a managed type: interface...
 * 也就是我們沒有按照SpringBoot的約定,默認掃描(application.java 入口類相對的兄弟包及其子包)
 * @ComponentScan("com.boot.demo.xxx.*.*")
 * 用於掃描@Controller @Service
 * @EnableJpaRepositories(basePackages = "com.boot.demo.xxx.dao")
 * 用於掃描Dao @Repository
 * @EntityScan("com.boot.demo.xxx.entity")
 * 用於掃描JPA實體類 @Entity
 */
@ComponentScan(basePackages = {"com.luv2code.ecommerce.config","com.luv2code.ecommerce.service","com.luv2code.ecommerce.controller"})
@EntityScan("com.luv2code.ecommerce.entity")
@EnableJpaRepositories("com.luv2code.ecommerce.dao")
@SpringBootApplication
public class SpringBootEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEcommerceApplication.class, args);
    }

}
