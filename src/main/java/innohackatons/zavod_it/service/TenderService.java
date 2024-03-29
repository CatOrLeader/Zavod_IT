package innohackatons.zavod_it.service;

import innohackatons.zavod_it.dto.TenderDto;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface TenderService {
    @NotNull Mono<List<TenderDto>> findAllTenders();
}
