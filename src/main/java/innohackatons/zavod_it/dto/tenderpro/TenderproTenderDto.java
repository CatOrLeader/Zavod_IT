package innohackatons.zavod_it.dto.tenderpro;

import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;

public record TenderproTenderDto(@NotBlank String id,
                                 @NotBlank String title,
                                 @NotBlank String type,
                                 @NotNull LocalDate openDate,
                                 @NotNull LocalDate closeDate,
                                 String deliveryAddress,
                                 String currencyName,
                                 @NotBlank String companyId,
                                 @NotBlank String companyName) {
}
