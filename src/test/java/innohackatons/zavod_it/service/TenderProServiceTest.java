package innohackatons.zavod_it.service;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.google.gson.Gson;
import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.tenderpro.LocalDateAdapter;
import innohackatons.zavod_it.service.tenderpro.TenderProService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TenderProServiceTest {

    @RegisterExtension
    public static final WireMockExtension WIRE_MOCK_SERVER = WireMockExtension.newInstance()
        .options(WireMockConfiguration.wireMockConfig().dynamicPort())
        .build();

    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("app.client.tender-pro-url", WIRE_MOCK_SERVER::baseUrl);
    }

    @Autowired
    private TenderProService tenderProService;

    @Test
    public void testFindAllTenders() {

        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/_info.tenderlist_by_set.json"))
            .withQueryParam("_key", equalTo("7b56c77b9f70220c3d5d4ce6477674ea"))
            .withQueryParam("set_type_id", equalTo("7"))
            .withQueryParam("set_id", equalTo("1"))
            .withQueryParam("max_rows", equalTo("10"))
            .withQueryParam("types", equalTo("3"))
            .withQueryParam("types", equalTo("8"))
            .withQueryParam("types", equalTo("9"))
            .withQueryParam("types", equalTo("7"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(
                    "{\"result\":{\"data\":[{\"id\":\"1\",\"title\":\"Tender 1\",\"type_name\":\"Type 1\",\"open_date\":\"01.01.2022\",\"close_date\":\"01.02.2022\",\"delivery_address\":\"Address 1\",\"currency_name\":\"USD\",\"company_id\":\"123\",\"company_name\":\"Company 1\"},{\"id\":\"2\",\"title\":\"Tender 2\",\"type_name\":\"Type 2\",\"open_date\":\"01.03.2022\",\"close_date\":\"01.04.2022\",\"delivery_address\":\"Address 2\",\"currency_name\":\"EUR\",\"company_id\":\"456\",\"company_name\":\"Company 2\"}]}}")));

        List<TenderDto> tenders = tenderProService.findAllTenders().blockOptional().orElse(Collections.emptyList());

        Gson gson = LocalDateAdapter.createGson();
        String actualJson = gson.toJson(tenders);

        String expectedJson =
            "[{\"id\":\"1\",\"title\":\"Tender 1\",\"type\":\"Type 1\",\"openDate\":\"2022-01-01\",\"closeDate\":\"2022-02-01\",\"deliveryAddress\":\"Address 1\",\"currencyName\":\"USD\"},{\"id\":\"2\",\"title\":\"Tender 2\",\"type\":\"Type 2\",\"openDate\":\"2022-03-01\",\"closeDate\":\"2022-04-01\",\"deliveryAddress\":\"Address 2\",\"currencyName\":\"EUR\"}]";

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    public void testFailedRequest() {
        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/_info.tenderlist_by_set.json"))
            .willReturn(aResponse()
                .withStatus(500)));

        StepVerifier.create(tenderProService.findAllTenders())
            .expectError(WebClientResponseException.InternalServerError.class)
            .verify();
    }

    @Test
    public void testNullResponse() {
        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/_info.tenderlist_by_set.json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{}")));

        List<TenderDto> tenders = tenderProService.findAllTenders().blockOptional().orElse(Collections.emptyList());

        assertThat(tenders).isEmpty();
    }

    @Test
    public void testNullRequestField() {
        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/_info.tenderlist_by_set.json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"result\":{\"data\":null}}")));

        List<TenderDto> tenders = tenderProService.findAllTenders().blockOptional().orElse(Collections.emptyList());

        assertThat(tenders).isEmpty();
    }

    @Test
    public void testFetchNonexistentTenders() {
        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/_info.tenderlist_by_set.json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"result\":{\"data\":[]}}")));

        List<TenderDto> tenders = tenderProService.findAllTenders().blockOptional().orElse(Collections.emptyList());

        assertThat(tenders).isEmpty();
    }
}
