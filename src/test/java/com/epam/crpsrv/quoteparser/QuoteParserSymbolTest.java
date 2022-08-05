package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_SYMBOL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuoteParserSymbolTest {

    @InjectMocks
    QuoteParserSymbol quoteParserSymbol;

    @Test
    void parse() {
        var symbol = "BTC";

        var expectedQuote = QuoteDto.builder()
                .symbol(symbol)
                .build();

        var quoteBuilder = QuoteDto.builder();

        var actualBuilder = quoteParserSymbol.parse(symbol, quoteBuilder);
        var actualQuote = actualBuilder.build();

        assertThat(actualQuote).isEqualTo(expectedQuote);
    }

    @Test
    void getColumnName() {
        Assertions.assertThat(quoteParserSymbol.getColumnName()).isEqualTo(COLUMN_SYMBOL);
    }
}