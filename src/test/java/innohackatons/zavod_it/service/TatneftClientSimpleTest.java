package innohackatons.zavod_it.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
// БЕЗ VPN
@SpringBootTest
class TatneftClientSimpleTest {
//
    @Value("${app.client.tender-tatneft}")
    private String tatneftUrl;

    @Test
    void contextLoads() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get(tatneftUrl);

        WebElement tendersElement = driver.findElement(By.xpath(
            "//div[@class='header_tools__info_link__item for-purchase_panel_view']/span[@class='pseudo']"));
        tendersElement.click();

        Thread.sleep(2000);

        String htmlContent = driver.getPageSource();

        Document doc = Jsoup.parse(htmlContent);


        Elements rows = doc.selectFirst("tbody").select("tr");

        for (Element row : rows) {
            Elements cells = row.select("td");
            for (Element cell : cells) {
                System.out.print(cell.text() + "\t");
            }
            System.out.println();
        }
    }


}
