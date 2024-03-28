package innohackatons.zavod_it.db.repository.jpa;

import innohackatons.zavod_it.db.repository.TenderRepository;
import innohackatons.zavod_it.dto.TenderDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class JpaTenderRepository implements TenderRepository {
    private final InterfaceJpaTenderRepository jpaTenderRepository;

    @Transactional
    public Optional<TenderDto> add(@NotNull TenderDto tenderDto) {
        if (jpaTenderRepository.findById(tenderDto.getId()).isEmpty()) {
            return Optional.of(jpaTenderRepository.saveAndFlush(tenderDto));
        }

        return Optional.empty();
    }

    @Transactional
    public void add(@NotNull List<TenderDto> tenders) {
        tenders.forEach(this::add);
    }

    @Transactional
    public Optional<TenderDto> findById(@NotBlank String id) {
        return jpaTenderRepository.findById(id);
    }

    @Transactional
    public List<TenderDto> findAll() {
        return jpaTenderRepository.findAll();
    }

    @Transactional
    public boolean update(@NotNull TenderDto newTender) {
        var maybeOldTender = jpaTenderRepository.findById(newTender.getId());

        if (maybeOldTender.isEmpty()) {
            return false;
        }

        var oldTender = maybeOldTender.get();
        oldTender = newTender;
        jpaTenderRepository.saveAndFlush(oldTender);
        return true;
    }
}
