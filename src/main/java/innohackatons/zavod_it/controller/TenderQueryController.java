package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.db.TenderRepository;
import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.dto.api.QueryTendersRequest;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TenderQueryController implements TenderQueryControllerAPI {
    private final TenderRepository repository;

    @Override
    public ResponseEntity<Void> addLink(QueryTendersRequest request) {
        repository.add(request.tenders().stream().map(TenderDto::new).collect(Collectors.toList()));
        return ResponseEntity.ok().build();
    }
}
