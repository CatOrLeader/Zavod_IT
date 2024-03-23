package innohackatons.zavod_it.service;

import innohackatons.zavod_it.dto.TenderDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TenderService {
    List<TenderDto> findAllTenders();

    List<TenderDto> searchTenders(String query);

    TenderDto findTenderById(String id);
}
