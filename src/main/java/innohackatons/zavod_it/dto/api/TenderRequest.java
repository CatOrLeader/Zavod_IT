package innohackatons.zavod_it.dto.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TenderRequest(@NotBlank String id,
                            @NotBlank String title,
                            @NotBlank String type,
                            @NotNull LocalDate openDate,
                            @NotNull LocalDate closeDate,
                            String deliveryAddress,
                            String currencyName) {
}
