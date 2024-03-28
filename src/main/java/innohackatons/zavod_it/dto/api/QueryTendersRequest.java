package innohackatons.zavod_it.dto.api;

import jakarta.validation.constraints.Min;
import java.util.List;

public record QueryTendersRequest(List<TenderRequest> tenders, @Min(0) int size) {
}
