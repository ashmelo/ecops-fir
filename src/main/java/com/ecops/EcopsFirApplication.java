package com.ecops;

import com.ecops.parameter.ApplicationParameter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(ApplicationParameter.class)
@EnableSwagger2
@EnableAsync
@ServletComponentScan
public class EcopsFirApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcopsFirApplication.class, args);
	}

}
