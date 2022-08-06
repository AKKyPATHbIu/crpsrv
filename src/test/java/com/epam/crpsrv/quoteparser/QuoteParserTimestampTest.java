package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_TIMESTAMP;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuoteParserTimestampTest {

    @InjectMocks
    QuoteParserTimestamp quoteParserTimestamp;

    @Test
    void parse() {
        var timestamp = "1641009600000";

        var expectedDate = LocalDateTime.of(2022, 1, 1, 6, 0);
        var expectedQuote = QuoteDto.builder()
                .timestamp(expectedDate)
                .build();

        var quoteBuilder = QuoteDto.builder();

        var actualBuilder = quoteParserTimestamp.parse(timestamp, quoteBuilder);

        var actualQuote = actualBuilder.build();

        assertThat(actualQuote).isEqualTo(expectedQuote);
    }

    @Test
    void getColumnName() {
        assertThat(quoteParserTimestamp.getColumnName()).isEqualTo(COLUMN_TIMESTAMP);
    }
}