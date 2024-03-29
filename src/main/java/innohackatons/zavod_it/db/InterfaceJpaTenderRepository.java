package innohackatons.zavod_it.db;

import innohackatons.zavod_it.dto.TenderDto;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterfaceJpaTenderRepository extends JpaRepository<TenderDto, String> {
    @Query(value = "SELECT * FROM tender as t WHERE LOWER(t.title) LIKE " +
                   "LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<TenderDto> findByQuery(@NotBlank String query);

    int removeById(@NotBlank String id);
}
