package innohackatons.zavod_it.dto;

import innohackatons.zavod_it.dto.api.TenderRequest;
import innohackatons.zavod_it.dto.tenderpro.TenderproTenderDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;
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
    @Column(nullable = false, updatable = false,
            name = "tender_id")
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

    @Override public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        TenderDto dto = (TenderDto) object;

        if (!Objects.equals(id, dto.id)) {
            return false;
        }
        if (!Objects.equals(title, dto.title)) {
            return false;
        }
        if (!Objects.equals(type, dto.type)) {
            return false;
        }
        if (!openDate.equals(dto.openDate)) {
            return false;
        }
        if (!closeDate.equals(dto.closeDate)) {
            return false;
        }
        if (!Objects.equals(deliveryAddress, dto.deliveryAddress)) {
            return false;
        }
        return Objects.equals(currencyName, dto.currencyName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + openDate.hashCode();
        result = 31 * result + closeDate.hashCode();
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (currencyName != null ? currencyName.hashCode() : 0);
        return result;
    }
}
