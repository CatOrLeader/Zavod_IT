package innohackatons.zavod_it.dto;

import innohackatons.zavod_it.dto.api.TenderRequest;
import innohackatons.zavod_it.dto.tenderpro.TenderproTenderDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tender")
public class TenderDto {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank private String id;

    @Column(nullable = false)
    @NotBlank private String title;

    @Column(nullable = false)
    @NotBlank private String type;

    @Column(nullable = false)
    @NotNull private LocalDate openDate;

    @Column(nullable = false)
    @NotNull private LocalDate closeDate;

    private String deliveryAddress;
    private String currencyName;

    public TenderDto(@NotNull TenderproTenderDto dto) {
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

    public TenderDto(@NotNull TenderRequest request) {
        this(
            request.id(),
            request.title(),
            request.type(),
            request.openDate(),
            request.closeDate(),
            request.deliveryAddress(),
            request.currencyName()
        );
    }
}
