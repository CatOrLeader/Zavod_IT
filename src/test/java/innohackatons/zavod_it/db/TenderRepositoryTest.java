package innohackatons.zavod_it.db;

import innohackatons.zavod_it.IntegrationTest;
import innohackatons.zavod_it.dto.TenderDto;
import java.time.LocalDate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenderRepositoryTest extends IntegrationTest {
    private static final TenderDto TENDER_SINGLE = new TenderDto(
        "1", "tender", "type", LocalDate.of(2022, 2, 2), LocalDate.of(2022, 2, 2),
        "severstal", "rub", "https://www.tender.pro/api/1/view_public"
    );

    @Autowired
    private TenderRepository repository;

    @Test
    @Order(1)
    @Rollback(false)
    @Transactional
    void givenEmptyDb_whenFindAnything_thenEmpty() {
        assertThat(repository.findAll()).isEmpty();
        assertThat(repository.findById("1")).isEmpty();
        assertThat(repository.findByQuery("none")).isEmpty();
    }

    @Test
    @Order(2)
    @Rollback(false)
    @Transactional
    void givenTender_whenInsert_thenInsertedCorrectly() {
        repository.add(TENDER_SINGLE);

        assertThat(repository.findAll()).containsExactly(TENDER_SINGLE);
        assertThat(repository.findById("1")).contains(TENDER_SINGLE);
        assertThat(repository.findByQuery("end")).contains(TENDER_SINGLE);
    }

    @Test
    @Order(3)
    @Rollback(false)
    @Transactional
    void givenSameTender_whenAddingAgain_thenEmptyRetrieved() {
        assertThat(repository.add(TENDER_SINGLE)).isEmpty();
    }

    @Test
    @Order(4)
    @Rollback(false)
    @Transactional
    void givenNewTender_whenUpdatingOldTender_thenCorrectlyUpdated() {
        TenderDto newTender = new TenderDto(
            "1", "new tender", "type", LocalDate.of(2022, 2, 2), LocalDate.of(2022, 2, 2),
            "severstal", "rub", "https://www.tender.pro/api/1/view_public"
        );

        boolean isUpdated = repository.update(newTender);

        assertThat(isUpdated).isTrue();
        assertThat(repository.findAll()).containsExactly(newTender);
        assertThat(repository.findById("1")).contains(newTender);
        assertThat(repository.findByQuery("new tender")).contains(newTender);
    }

    @Test
    @Order(5)
    @Rollback(false)
    @Transactional
    void whenRemoveAllTenders_thenDbIsEmpty() {
        repository.removeById("1");

        assertThat(repository.findAll()).isEmpty();
        assertThat(repository.findById("1")).isEmpty();
        assertThat(repository.findByQuery("end")).isEmpty();
    }
}
