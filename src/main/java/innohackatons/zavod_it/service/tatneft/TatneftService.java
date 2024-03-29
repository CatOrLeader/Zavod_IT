package innohackatons.zavod_it.service.tatneft;

import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class TatneftService implements TenderService {
    @Value("${app.client.tender-tatneft}")
    private String tatneftUrl;

    @Override
    public Mono<List<TenderDto>> findAllTenders() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        WebDriverManager.chromedriver().setup();

        driver.get(tatneftUrl);

        WebElement tendersElement = driver.findElement(By.xpath(
            "//div[@class='header_tools__info_link__item for-purchase_panel_view']/span[@class='pseudo']"));
        tendersElement.click();

        try {
            Thread.sleep(2000);
            String htmlContent = driver.getPageSource();

            Document doc = Jsoup.parse(htmlContent);

            Element table = doc.selectFirst("tbody");
            Elements rows = table.select("tr");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            Optional<List<TenderDto>> listTenders = Optional.of(rows.stream()
                .map(row -> row.select("td"))
                .map(cells -> new TenderDto(
                    cells.getFirst().text(),
                    cells.get(4).text(),
                    cells.get(3).text(),
                    LocalDate.parse(cells.get(1).text(), formatter),
                    LocalDate.parse(cells.get(2).text(), formatter),
                    null,
                    null,
                    tatneftUrl
                ))
                .collect(Collectors.toList()));

            driver.quit();

            return Mono.justOrEmpty(listTenders.orElse(null));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return Mono.empty();
        }
    }

}
