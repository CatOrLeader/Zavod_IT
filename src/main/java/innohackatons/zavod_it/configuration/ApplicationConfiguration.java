package innohackatons.zavod_it.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfiguration(
    @NotNull
    Client client
) {
    @Validated
    public record Client(@NotNull String tenderProUrl) {
    }
}