package innohackatons.zavod_it.service.implementation;

import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {
    List<TenderDto> tenderDtos = new ArrayList<>();

    @Override
    public List<TenderDto> findAllTenders() {
        tenderDtos.add(
            new TenderDto(
                "12-03-2004-001",
                "Тендер даунов",
                "открытый",
                LocalDate.of(2023, 3, 3),
                LocalDate.of(2024, 3, 3),
                "Москва",
                "Рубли"
                )
        );
        tenderDtos.add(
            new TenderDto(
                "12-03-2004-002",
                "Тендер железяк",
                "открытый",
                LocalDate.of(2023, 3, 3),
                LocalDate.of(2024, 3, 3),
                "Москва",
                "Рубли"
            )
        );
        tenderDtos.add(
            new TenderDto(
                "12-03-2004-003",
                "Тендер умников",
                "открытый",
                LocalDate.of(2023, 3, 3),
                LocalDate.of(2024, 3, 3),
                "Москва",
                "Рубли"
            )
        );
        return tenderDtos;
    }

    @Override
    public List<TenderDto> searchTenders(String query) {
        return this.findAllTenders();
    }

    @Override
    public TenderDto findTenderById(String id) {
        findAllTenders();
        for (int i = 0; i < tenderDtos.size(); i++) {
            if (tenderDtos.get(i).id().equals(id)) {
               return tenderDtos.get(i);
            }
        }
        return null;
    }
}
