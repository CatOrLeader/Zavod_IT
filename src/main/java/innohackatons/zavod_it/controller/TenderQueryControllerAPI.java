package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.dto.api.QueryTendersRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/api/tenders")
public interface TenderQueryControllerAPI {
    @Operation(summary = "Add tenders in DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Tenders successfully added"),

        @ApiResponse(responseCode = "400",
                     description = "Incorrect request parameters")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addLink(
        @RequestBody @Valid QueryTendersRequest request
    );
}
