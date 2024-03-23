package innohackatons.zavod_it.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import java.net.URI;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TenderDto(@NotBlank String id,
                        @NotBlank String title,
                        @NotBlank String type,
                        @NotNull LocalDate openDate,
                        @NotNull LocalDate closeDate,
                        String deliveryAddress,
                        String currencyName) {
}
