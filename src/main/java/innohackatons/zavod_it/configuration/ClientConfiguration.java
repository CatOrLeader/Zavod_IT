package innohackatons.zavod_it.configuration;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import javax.net.ssl.SSLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

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

    @Bean
    public WebClient tatneftClient() {
        return WebClient.builder()
            .baseUrl(configuration.client().tenderTatneft())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
            .build();
    }

    @Bean
    public WebClient iceTradeClient() throws SSLException {
        SslContext sslContext = SslContextBuilder // CERTIFICATE!
            .forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return WebClient.builder()
            .baseUrl(configuration.client().tenderIceTrade())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}
