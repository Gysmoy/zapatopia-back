package zapatopia.web;

import com.github.ydespreaux.spring.data.jpa.repository.config.EnableJpaCriteriaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import zapatopia.web.services.AuditorAwareImpl;

@SpringBootApplication(scanBasePackages = {"zapatopia.web.jpa", "zapatopia.web.controller", "zapatopia.web.repository", "zapatopia.web.services"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaCriteriaRepositories(basePackages = {"zapatopia.web.repository"})
class JpaConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}