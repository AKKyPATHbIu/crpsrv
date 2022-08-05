package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuoteParserPriceTest {

    @InjectMocks
    QuoteParserPrice quoteParserPrice;

    @Test
    void parse() {
        var price = "125.20";

        var expectedQuote = QuoteDto.builder()
                .price(new BigDecimal(price))
                .build();

        var quoteBuilder = QuoteDto.builder();

        var actualBuilder = quoteParserPrice.parse(price, quoteBuilder);

        var actualQuote = actualBuilder.build();

        assertThat(actualQuote).isEqualTo(expectedQuote);
    }

    @Test
    void getColumnName() {
        assertThat(quoteParserPrice.getColumnName()).isEqualTo(COLUMN_PRICE);
    }
}