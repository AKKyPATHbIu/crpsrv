package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_TIMESTAMP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

        var gregorianCalendar = new GregorianCalendar(2022, Calendar.JANUARY, 1, 6, 0, 0);

        var expectedDate = gregorianCalendar.getTime();
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