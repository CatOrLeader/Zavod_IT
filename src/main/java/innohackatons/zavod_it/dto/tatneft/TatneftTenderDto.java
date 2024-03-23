package innohackatons.zavod_it.dto.tatneft;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TatneftTenderDto(
    @NotBlank String code,
    @NotNull LocalDate openDate,
    @NotNull LocalDate closeDate,
    @NotBlank String title,
    @NotBlank String type,
    @NotBlank String companyName
) {
}
