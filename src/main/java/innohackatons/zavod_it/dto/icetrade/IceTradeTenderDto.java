package innohackatons.zavod_it.dto.icetrade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record IceTradeTenderDto(
    @NotBlank String code,
    @NotNull LocalDate openDate,
    @NotNull LocalDate closeDate,
    @NotBlank String title,
    @NotBlank String type,
    @NotBlank String companyName
) {
}
