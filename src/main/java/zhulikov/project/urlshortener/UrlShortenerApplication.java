package zhulikov.project.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "zhulikov.project.urlshortener")
@EnableJpaRepositories(basePackages = "zhulikov.project.urlshortener.repository")
@EnableScheduling
public class UrlShortenerApplication {
    static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}

