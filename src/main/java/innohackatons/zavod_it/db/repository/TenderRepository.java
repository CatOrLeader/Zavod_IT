package innohackatons.zavod_it.db.repository;

import innohackatons.zavod_it.dto.TenderDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface TenderRepository {
    Optional<TenderDto> add(@NotNull TenderDto tenderDto);

    void add(@NotNull List<TenderDto> tenders);

    Optional<TenderDto> findById(@NotBlank String id);

    List<TenderDto> findAll();

    boolean update(@NotNull TenderDto newTender);
}
