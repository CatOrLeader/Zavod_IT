package innohackatons.zavod_it.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenderDto {
    private Long id;
    private String description;
}
