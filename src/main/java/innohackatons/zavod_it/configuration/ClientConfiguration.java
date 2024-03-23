package innohackatons.zavod_it.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(proxyBeanMethods = false)
@Log4j2
@RequiredArgsConstructor
public class ClientConfiguration {
    private final ApplicationConfiguration configuration;

    @Bean
    public WebClient tenderproClient() {
        return WebClient.builder()
            .baseUrl(configuration.client().tenderProUrl())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
            .build();
    }
}
