package innohackatons.zavod_it.db.repository.jpa;

import innohackatons.zavod_it.dto.TenderDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceJpaTenderRepository extends JpaRepository<TenderDto, String> {
}
