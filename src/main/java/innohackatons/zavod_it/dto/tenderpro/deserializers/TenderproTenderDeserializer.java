package innohackatons.zavod_it.dto.tenderpro.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import innohackatons.zavod_it.dto.tenderpro.TenderproTenderDto;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TenderproTenderDeserializer extends JsonDeserializer<TenderproTenderDto> {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static LocalDate parseDate(String value) {
        return LocalDate.parse(value, LOCAL_DATE_FORMATTER);
    }

    @Override
    public TenderproTenderDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);

        return new TenderproTenderDto(
            json.get("id").asText(),
            json.get("title").asText(),
            json.get("type_name").asText(),
            parseDate(json.get("open_date").asText()),
            parseDate(json.get("close_date").asText()),
            json.get("delivery_address").asText(),
            json.get("currency_name").asText(),
            json.get("company_id").asText(),
            json.get("company_name").asText(),
            null
        );
    }
}
