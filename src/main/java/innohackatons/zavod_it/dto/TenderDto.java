package innohackatons.zavod_it.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import innohackatons.zavod_it.dto.tenderpro.TenderproTenderDto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.jetbrains.annotations.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TenderDto(@NotBlank String id,
                        @NotBlank String title,
                        @NotBlank String type,
                        @NotNull LocalDate openDate,
                        @NotNull LocalDate closeDate,
                        String deliveryAddress,
                        String currencyName) {
    public TenderDto(@NotBlank TenderproTenderDto dto) {
        this(
            dto.id(),
            dto.title(),
            dto.type(),
            dto.openDate(),
            dto.closeDate(),
            dto.deliveryAddress(),
            dto.currencyName()
        );
    }
}
