//package innohackatons.zavod_it.service.tenderpro;
//
//import innohackatons.zavod_it.dto.tenderpro.TenderproTenderDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class TenderProService {
//    private final WebClient tenderproClient;
//
//    public Optional<List<TenderproTenderDto>> getAllTenders() {
//        return tenderproClient
//            .get()
//            .uri(uriBuilder -> uriBuilder
//                .path("_info.tenderlist_by_set.json")
//                .queryParam("_key", "7b56c77b9f70220c3d5d4ce6477674ea")
//                .queryParam("set_type_id", "7")
//                .queryParam("set_id", "1")
//                .queryParam("max_rows", "100")
//                .queryParam("")
//            )
//    }
//}
