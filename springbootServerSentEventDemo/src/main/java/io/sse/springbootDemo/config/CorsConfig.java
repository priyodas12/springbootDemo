package io.sse.springbootDemo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class CorsConfig implements WebMvcConfigurer {

  @Bean
  public WebMvcConfigurer corsConfiguration() {

    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {

        log.info("Creating CORS setting Bean");

        registry.addMapping("/api/v1/events/data")
            .allowedOrigins("localhost:4200")
            .allowedMethods("GET");
      }
    };
  }
  }
