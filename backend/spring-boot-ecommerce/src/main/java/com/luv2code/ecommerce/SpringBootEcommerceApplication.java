package com.luv2code.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@EntityScan("com.luv2code.ecommerce.entity")
@EnableJpaRepositories("com.luv2code.ecommerce")
@SpringBootApplication
public class SpringBootEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}

}
