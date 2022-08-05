package com.epam.crpsrv.quoteparser;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {QuoteParserFactoryImpl.class, QuoteValuesParserImpl.class, QuoteParserUnknown.class})
@Import(value = {QuoteParserConfig.class})
class QuoteValuesParserImplTest {

    private static final String BTC_VALUES =
            "  timestamp,   price,symbol   \n" +
                    " 1641009600000 , 46813.21,BTC  \n" +
                    "1641020400000,46979.61,BTC";

    @Autowired
    private QuoteValuesParser quoteValuesParser;

    @Test
    void parse() {
        var actualQuotes = quoteValuesParser.parse(BTC_VALUES.getBytes(StandardCharsets.UTF_8));
        System.out.println(actualQuotes);
    }
}