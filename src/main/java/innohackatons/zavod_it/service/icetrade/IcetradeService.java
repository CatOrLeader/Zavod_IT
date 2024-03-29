package innohackatons.zavod_it.service.icetrade;

import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class IcetradeService implements TenderService {
    private final WebClient iceTradeClient;

    @Override
    public Mono<List<TenderDto>> findAllTenders() {
//        https://icetrade.by/search/foreign_uno?search_text=&auc_num=&company_title=&countries%5B%5D=RUS&industries=&period=&created_from=&created_to=&request_end_from=&request_end_to=&sort=num%3Adesc&sbm=1&onPage=20
        return iceTradeClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .path("/search/foreign_uno")
                .queryParam("search_text", "")
                .queryParam("auc_num", "")
                .queryParam("company_title", "")
                .queryParam("countries[]", "RUS")
                .queryParam("industries", "")
                .queryParam("period", "")
                .queryParam("created_from", "")
                .queryParam("created_to", "")
                .queryParam("request_end_from", "")
                .queryParam("request_end_to", "")
                .queryParam("sort", "num:desc")
                .queryParam("sbm", "1")
                .queryParam("onPage", "20")
                .build()
            )
            .retrieve()
            .bodyToMono(String.class)
            .flatMap(htmlContent -> Mono.fromCallable(() -> parseTendersFromHtml(htmlContent)));
    }

    private List<TenderDto> parseTendersFromHtml(String htmlContent) {
        System.out.println(htmlContent);
        List<TenderDto> tenderList = new ArrayList<>();
        Document doc = Jsoup.parse(htmlContent);
        Elements rows = doc.select("table.auctions tr[class^=rw]");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (Element row : rows) {
            Elements cells = row.select("td");

            String subject = cells.get(0).select("a").text();
            String country = cells.get(1).text();
            String region = cells.get(2).text();
            String number = cells.get(4).text();
            LocalDate offersDueDate = LocalDate.parse(cells.get(5).text(), formatter);

            TenderDto tenderDto =
                new TenderDto(number, subject, "Открытый", LocalDate.of(2000, 1, 1), offersDueDate, region, "RUB");
            tenderList.add(tenderDto);
        }

        return tenderList;
    }

    @Override
    public Optional<List<TenderDto>> searchTenders(String query) {
        return Optional.empty();
    }

    @Override
    public Optional<TenderDto> findTenderById(String id) {
        return Optional.empty();
    }
}
