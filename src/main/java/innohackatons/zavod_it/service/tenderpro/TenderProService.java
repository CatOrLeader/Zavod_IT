package innohackatons.zavod_it.service.tenderpro;

import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.dto.tenderpro.Response;
import innohackatons.zavod_it.service.TenderService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class TenderProService implements TenderService {
    private final WebClient tenderproClient;

    @Override
    public Optional<List<TenderDto>> findAllTenders() {
        return tenderproClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .path("/_info.tenderlist_by_set.json")
                .queryParam("_key", "7b56c77b9f70220c3d5d4ce6477674ea")
                .queryParam("set_type_id", "7")
                .queryParam("set_id", "1")
                .queryParam("max_rows", "10")
                .queryParam("types", "3", "8", "9", "7")
                .build()
            )
            .retrieve()
            .toEntity(Response.class)
            .flatMap(entity -> {
                if (!entity.getStatusCode().is2xxSuccessful()) {
                    log.warn("The request is unsatisfied on tenderpro: " + entity.getBody());
                    return Mono.empty();
                }

                var body = entity.getBody();
                if (body == null) {
                    log.warn("Body of the request is null");
                    return Mono.empty();
                }

                var request = body.result();
                if (request == null) {
                    log.warn("'request' field of the request is null");
                    return Mono.empty();
                }

                return Mono.justOrEmpty(entity.getBody().result().data().stream()
                    .map(TenderDto::new).collect(Collectors.toList()));
            })
            .blockOptional();
    }

    @Override
    public Optional<List<TenderDto>> searchTenders(String query) {
        return Optional.empty();
    }

    @Override
    public Optional<TenderDto> findTenderById(String id) {
        return Optional.empty();
    }
}
