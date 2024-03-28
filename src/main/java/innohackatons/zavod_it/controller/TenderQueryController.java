package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.dto.api.QueryTendersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TenderQueryController implements TenderQueryControllerAPI {
    @Override
    public ResponseEntity<Void> addLink(QueryTendersRequest request) {
        return ResponseEntity.ok().build();
    }
}
