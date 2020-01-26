package com.ecops.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("application")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationParameter {

    private String profileUrl;
    private String adminUrl;
}
