package innohackatons.zavod_it.service;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import innohackatons.zavod_it.db.JpaTenderRepository;
import innohackatons.zavod_it.db.TenderRepository;
import innohackatons.zavod_it.service.icetrade.IcetradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBeans(value = {
    @MockBean(classes = JpaTenderRepository.class),
    @MockBean(classes = TenderRepository.class)
})
class IceTradeClientTest {

    @RegisterExtension
    public static final WireMockExtension WIRE_MOCK_SERVER = WireMockExtension.newInstance()
        .options(WireMockConfiguration.wireMockConfig().dynamicPort())
        .build();

    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("app.client.tender-ice-trade", WIRE_MOCK_SERVER::baseUrl);
        registry.add("spring.autoconfigure.exclude", () ->
            "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
            " org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration," +
            " org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration");
    }

    @Autowired
    private IcetradeService icetradeService;

    @Test
    public void testFailedRequest() {
        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/search/foreign_uno"))
            .willReturn(aResponse()
                .withStatus(500)));

        StepVerifier.create(icetradeService.findAllTenders())
            .expectError(WebClientResponseException.InternalServerError.class)
            .verify();
    }

//    @Test
//    public void testFindAllTenders() {
//        WIRE_MOCK_SERVER.stubFor(get(urlPathEqualTo("/search/foreign_uno"))
//            .willReturn(aResponse()
//                .withStatus(200)
//                .withHeader("Content-Type", "text/html")
//                .withBody("<table class=\"auctions\">" +
//                    "<tr class=\"row1\"><td><a>Subject 1</a></td><td>RUS</td><td>Region 1</td><td></td><td>12345</td><td>01.01.2022</td></tr>" +
//                    "<tr class=\"row2\"><td><a>Subject 2</a></td><td>RUS</td><td>Region 2</td><td></td><td>67890</td><td>02.02.2022</td></tr>" +
//                    "</table>")));
//
//        List<TenderDto> tenders = icetradeService.findAllTenders().blockOptional().orElse(Collections.emptyList());
//        System.out.println(tenders);
//        assertThat(tenders).hasSize(2);
//        assertThat(tenders.get(0).getTitle()).isEqualTo("Subject 1");
//        assertThat(tenders.get(1).getTitle()).isEqualTo("Subject 2");
//    }
}
