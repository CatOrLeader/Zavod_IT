package innohackatons.zavod_it.service;

import innohackatons.zavod_it.dto.TenderDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface TenderService {
    @NotNull Mono<List<TenderDto>> findAllTenders();

    @NotNull Optional<List<TenderDto>> searchTenders(@NotBlank String query);

    @NotNull Optional<TenderDto> findTenderById(@NotBlank String id);
}
