package innohackatons.zavod_it.dto.tenderpro;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import innohackatons.zavod_it.dto.tenderpro.deserializers.TenderproTenderDeserializer;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.jetbrains.annotations.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonDeserialize(using = TenderproTenderDeserializer.class)
public record TenderproTenderDto(@NotBlank String id,
                                 @NotBlank String title,
                                 @NotBlank String type,
                                 @NotNull LocalDate openDate,
                                 @NotNull LocalDate closeDate,
                                 String deliveryAddress,
                                 String currencyName,
                                 @NotBlank String companyId,
                                 @NotBlank String companyName,
                                 @NotBlank String url) {
}
