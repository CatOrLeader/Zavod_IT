package innohackatons.zavod_it;

import innohackatons.zavod_it.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class ZavodItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZavodItApplication.class, args);
    }

}
