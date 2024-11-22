package store.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import org.junit.jupiter.api.Test;

class OrderParserTest {

    @Test
    void parse() {

        String orders = "[콜라-2]";

        Map<String, Integer> parse = OrderParser.parse(orders);

        assertNotNull(parse);
    }

}